package com.wxapi.db;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wxapi.model.WxSchoolMessageText;
import com.wxapi.repositories.WxSchoolMessageRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class WxSchoolMessageRepositoryTest {

	@Autowired
	private WxSchoolMessageRepository schoolMsgRep;
	
	@Test
	public void saveMessageText(){
		WxSchoolMessageText msgText = new WxSchoolMessageText();
		msgText.setContent("Hello World!");
		msgText.setFromUserName("Jie");
		msgText.setMsgId(123L);
		msgText.setOwnerId(1l);
		
		schoolMsgRep.saveAndFlush(msgText);
	}
	
}
