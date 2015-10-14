package WxMessageLink;

import net.sf.json.JSON;
import net.sf.json.xml.XMLSerializer;

import org.junit.Before;
import org.junit.Test;

import com.core.util.JsonUtil;
import com.wxapi.message.MsgType;
import com.wxapi.message.WxMessageBase;
import com.wxapi.message.WxMessageText;

public class WxMessageTest {
	
	private WxMessageText textMsg;
	
	@Before
	public void init(){
		textMsg = new WxMessageText();
		textMsg.setToUserName("zhang");
		textMsg.setFromUserName("yang");
		textMsg.setContent("This is a test message");
		textMsg.setMsgType(MsgType.text);
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
}
