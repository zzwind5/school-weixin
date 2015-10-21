package com.wxcms.control;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wxcms.vo.WxMsgNews;

/**
 * 微信消息查看
 * 
 */
@Controller
@RequestMapping("/wxweb")
public class WxWebCtrl {

//	@Autowired
//	private MsgNewsService msgNewsService;
	
	@RequestMapping(value ="/msg/newsread", method = RequestMethod.GET)
	public ModelAndView newsread(){
		WxMsgNews news = new WxMsgNews();
		
		news.setCreatetime(new Date());
		news.setShowpic(1);
		news.setTitle("中国计量学院");
		news.setDescription("改名前的照片");
		news.setPicpath("http://img.pic123456.com/tp/2015/08/doihnwu3rfr.jpg");
		
		ModelAndView mv = new ModelAndView("wxweb/mobileNewsRead");
		mv.addObject("news",news);
		return mv;
	}
	
//	@RequestMapping(value = "/msg/newsList")
//	public ModelAndView pageWebNewsList(HttpServletRequest request,MsgNews searchEntity,Pagination<MsgNews> page){
//		ModelAndView mv = new ModelAndView("wxweb/mobileNewsList");
//		List<MsgNewsVO> pageList = msgNewsService.pageWebNewsList(searchEntity,page);
//		mv.addObject("pageList", pageList);
//		return mv;
//	}
	
	
}
