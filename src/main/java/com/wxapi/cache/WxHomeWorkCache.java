package com.wxapi.cache;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.core.util.WxMessageUtil;
import com.wxapi.message.WxEventOperation;
import com.wxapi.message.WxEventType;
import com.wxapi.message.WxMessageBase;
import com.wxapi.message.WxMessageEvent;
import com.wxapi.message.response.WxMessageNewsItem;
import com.wxapi.message.response.WxMessageResponseImage;
import com.wxapi.message.response.WxMessageResponseNews;
import com.wxapi.message.response.WxMessageResponseVideo;
import com.wxapi.message.response.WxMessageResponseVoice;
import com.wxapi.model.WxSchoolMessage;
import com.wxapi.model.WxSchoolMessageImage;
import com.wxapi.model.WxSchoolMessageText;
import com.wxapi.model.WxSchoolMessageVideo;
import com.wxapi.model.WxSchoolMessageVoice;
import com.wxapi.repositories.WxSchoolMessageRepository;

@Component
public class WxHomeWorkCache {
	
	@Autowired
	private WxSchoolMessageRepository schoolMessageRep;

	private HashMap<String, WxSchoolMessage> homeWorkCache = new HashMap<>();
	
	@PostConstruct
	public void init(){
		Calendar todayStart = Calendar.getInstance();
		todayStart.set(Calendar.HOUR, 0);
        todayStart.set(Calendar.MINUTE, 0);
        todayStart.set(Calendar.SECOND, 0);
        todayStart.set(Calendar.MILLISECOND, 0);
        
		Timestamp tStart = new Timestamp(todayStart.getTime().getTime());
		List<Long> maxIds = schoolMessageRep.findMaxIdByOperation(tStart);
		if (maxIds.isEmpty()) {
			return;
		}
		
		List<WxSchoolMessage> allMessage = schoolMessageRep.findByIdIn(maxIds);
		for (WxSchoolMessage msgObj : allMessage) {
			cache(msgObj);
		}
	}
	
	public void cache(WxSchoolMessage schoolMsg) {
		String key = generateKey(schoolMsg.getWxMenuKey());
		homeWorkCache.put(key, schoolMsg);
	}
	
	public WxMessageBase queryHomeWork(WxMessageEvent eventMsg) {
		if (eventMsg.getEvent() != WxEventType.CLICK) {
			return null;
		}
		
		switch(eventMsg.getEventKey()) {
		case QUERY_CHINESE:
		case QUERY_ENGLISH:
		case QUERY_MATH:
		case QUERY_MESSAGE:
			return queryHomeWork(eventMsg.getEventKey());
		case QUERY_ALL:
			return queryAllHomeWork(eventMsg);
		default:
			return null;
		}
	}
	
	private WxMessageBase queryHomeWork(WxEventOperation eventOpt){
		String key = generateKey(eventOpt);
		WxSchoolMessage schoolMsg = homeWorkCache.get(key);
		if (schoolMsg == null) {
			return null;
		}
		
		WxMessageBase resWxMessage = null;
		switch(schoolMsg.getWxMsgType()) {
		case image:
			WxSchoolMessageImage imageBo = (WxSchoolMessageImage)schoolMsg;
			resWxMessage = new WxMessageResponseImage();
			((WxMessageResponseImage)resWxMessage).getImage().setMediaId(imageBo.getMediaId());
			break;
		case text:
			WxSchoolMessageText textBo = (WxSchoolMessageText)schoolMsg;
			resWxMessage = new WxMessageResponseNews();
			((WxMessageResponseNews)resWxMessage).setArticleCount(1);
			
			WxMessageNewsItem newsItem = new WxMessageNewsItem();
			newsItem.setTitle(WxMessageUtil.getMessage("wxapi.homwwork.title"));
			String content = WxMessageUtil.getMessage("wxapi.homwwork.content", 
					getHomeWorkTitle(eventOpt), textBo.getContent());
			newsItem.setDescription(content);
			((WxMessageResponseNews)resWxMessage).getArticles().add(newsItem);
			break;
		case voice:
			WxSchoolMessageVoice voiceBo = (WxSchoolMessageVoice)schoolMsg;
			resWxMessage = new WxMessageResponseVoice();
			((WxMessageResponseVoice)resWxMessage).getVoice().setMediaId(voiceBo.getMediaId());
			break;
		case video:
			WxSchoolMessageVideo videoBo = (WxSchoolMessageVideo)schoolMsg;
			resWxMessage = new WxMessageResponseVideo();
			((WxMessageResponseVideo)resWxMessage).getVideo().setMediaId(videoBo.getMediaId());
			break;
		default:
			break;
		}
		
		if (resWxMessage != null) {
			resWxMessage.setFromUserName(schoolMsg.getToUserName());
			resWxMessage.setToUserName(schoolMsg.getFromUserName());
			resWxMessage.setCreateTime(System.currentTimeMillis());
		}
		
		return resWxMessage;
	}
	
	private WxMessageBase queryAllHomeWork(WxMessageEvent eventMsg) {
		WxMessageResponseNews msgNews = new WxMessageResponseNews();
		msgNews.setFromUserName(eventMsg.getToUserName());
		msgNews.setToUserName(eventMsg.getFromUserName());
		msgNews.setCreateTime(System.currentTimeMillis());
		msgNews.setArticleCount(1);
		
		WxMessageNewsItem newsItem = new WxMessageNewsItem();
		msgNews.getArticles().add(newsItem);
		newsItem.setTitle(WxMessageUtil.getMessage("wxapi.homwwork.title"));
		
		List<WxMessageBase> homeWorkList = new ArrayList<>();
		WxEventOperation[] operations = {WxEventOperation.QUERY_CHINESE, 
				WxEventOperation.QUERY_MATH, WxEventOperation.QUERY_ENGLISH, WxEventOperation.QUERY_MESSAGE};
		for (WxEventOperation opt : operations) {
			homeWorkList.add(queryHomeWork(opt));
		}
		
		StringBuilder contentSB = new StringBuilder();
		for (int index = 0; index < homeWorkList.size(); index++) {
			WxMessageBase msgHomeWork = homeWorkList.get(index);
			if (msgHomeWork == null) {
				continue;
			}
			
			if (contentSB.length() > 0) {
				contentSB.append("\n\n");
			}
			
			String itemContent = null;
			String type = getHomeWorkTitle(operations[index]);
			if (msgHomeWork instanceof WxMessageResponseNews) {
				itemContent = ((WxMessageResponseNews)msgHomeWork).getArticles().get(0).getDescription();
			} else if (msgHomeWork instanceof WxMessageResponseImage) {
				itemContent = WxMessageUtil.getMessage("wxapi.homwwork.type.all.image", type);
			} else if (msgHomeWork instanceof WxMessageResponseVoice) {
				itemContent = WxMessageUtil.getMessage("wxapi.homwwork.type.all.voice", type);
			} else if (msgHomeWork instanceof WxMessageResponseVideo) {
				itemContent = WxMessageUtil.getMessage("wxapi.homwwork.type.all.video", type);
			}
			
			contentSB.append(itemContent);
		}
		newsItem.setDescription(contentSB.toString());
		
		return msgNews;
	}
	
	private String getHomeWorkTitle(WxEventOperation eventOpt) {
		String type = generateKey(eventOpt);
		String msgKey = "wxapi.homwwork.type." + type;
		return WxMessageUtil.getMessage(msgKey);
	}
	
	private String generateKey(WxEventOperation eventOpt) {
		return eventOpt.name().substring(eventOpt.name().indexOf("_") + 1).toLowerCase();
	}
}