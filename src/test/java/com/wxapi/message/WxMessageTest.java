package com.wxapi.message;

import org.junit.Before;
import org.junit.Test;

import com.core.util.JsonUtil;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

public class WxMessageTest {
	
	private WxMessageText textMsg;
	
	@Before
	public void init(){
		textMsg = new WxMessageText();
		textMsg.setToUserName("zhang");
		textMsg.setFromUserName("yang");
		textMsg.setContent("This is a test message");
		textMsg.setMsgType(WxMsgType.text);
		textMsg.setMsgId(1234567L);
		textMsg.setCreateTime(System.currentTimeMillis());
	}

	@Test
	public void messageTextTest(){		
		System.out.println(JsonUtil.toJsonString(textMsg));
	}
	
	@Test
	public void messageToJson() {
		String source = "{\"MsgType\" : \"text\", \"ToUserName\" : \"zhang\", \"FromUserName\" : \"yang\", \"CreateTime\" : 1444826739460, \"msgType\" : \"text\", \"MsgId\" : 1234567, \"Content\" : \"This is a test message\"}";
		WxMessageBase msgObj = JsonUtil.toObject(source, WxMessageBase.class);
		System.out.println(msgObj);
	}
	
	@Test
	public void messageToXml() {
		String xmlStr = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName> <CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[this is a test]]></Content><MsgId>1234567890123456</MsgId></xml>";
		XMLSerializer xmlTool = new XMLSerializer();
		JSON json = xmlTool.read(xmlStr);
		System.out.println(json.toString(1));
		
		System.out.println(JsonUtil.xmlToObject(xmlStr, WxMessageBase.class));
	}
	
	@Test
	public void messageObjToXml(){
		System.out.println(JsonUtil.toXmlString(textMsg, true));
	}
	
	@Test
	public void eventStringToObject(){
		String eventStr = "<xml><ToUserName><![CDATA[]]></ToUserName><FromUserName><![CDATA[FromUser]]></FromUserName><CreateTime>123456789</CreateTime><MsgType><![CDATA[event]]></MsgType><Event><![CDATA[CLICK]]></Event><EventKey><![CDATA[RELEASE_CHINESE]]></EventKey></xml>";
//		String jsonStr = JsonUtil.xmlToJsonString(eventStr);
//		System.out.println(jsonStr);
		WxMessageBase msgObj = JsonUtil.xmlToObject(eventStr, WxMessageBase.class);
		System.out.println(msgObj);
		msgObj.setMsgId(1111l);
		
		System.out.println(JsonUtil.toXmlString(msgObj, true));
		System.out.println(JsonUtil.toJsonString(msgObj));
	}
}
