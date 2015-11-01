package com.fileServer.qiniu;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class FileServerQiNiuTest {
	
	@Autowired
	private FileServerQiNiu qiNiu;

	@Test
	public void uploadFileTest() throws IOException{
		String filePath = "F:\\Photo\\111\\DSC_0516.JPG";
		Resource fres = new FileSystemResource(filePath);
		
		byte[] bytResource = IOUtils.toByteArray(fres.getInputStream());
		String newUrl = qiNiu.upload(bytResource, fres.getFilename());
		System.out.println(newUrl);
	}
	
	@Test
	public void fetchFileTest() {
//		String url = "http://mmbiz.qpic.cn/mmbiz/u8OujVdroaNsEzX7nNbicz8xoEGzxicUicGzExWYrv4m7JO9eVh0ArupGHKqBOqCamH4HmPcBmibHtpkxIfLOzYQyw/0";
		String urlVocie = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=zvs16vFB1_kLMAi8aW1txRzvbAgTKR4hnmbHob56NYUs-SGRnbF1LeUL2DQ0B9eCG7ZKhuWPTpw1iAah2rrj4V0pjYh-ErXyJWU83MyJxRA&media_id=us3sR1oFDtt4rvT4GKmz9avNWpeH7MQ0hBRj8G53EAyjQoCe4tdXqeJLZVOdLewh";
		String newUrl = qiNiu.fetch(urlVocie);
		System.out.println(newUrl);
	}
	
	@Test
	public void amr2map3() {
		String key = "Fv3vRr1cUuzlKGDbozP3_Isx35js";
		String id = qiNiu.amr2map3(key);
		System.out.println(id);
	}
}
