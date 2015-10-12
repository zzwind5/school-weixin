package com.wxapi.process;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.wxapi.model.WxOwner;
import com.wxapi.repositories.WxOwnerRepository;
import com.wxapi.vo.WxAccessTokenVo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class WxApiTest {
	
	private static final String TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
	
	@Autowired
	private WxOwnerRepository wxOwnerRep;

	@Test
	public void accessTokenTest(){
		WxOwner wxOwner = wxOwnerRep.findOne(1l);
		String url = String.format(TOKEN_URL, wxOwner.getAppId(), wxOwner.getAppSecret());
		System.out.println(url);
		
		RestTemplate restTemp = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
		restTemp.setRequestFactory(new HttpComponentsClientHttpRequestFactory(createHttpClient()));
		restTemp.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		WxAccessTokenVo tokenVo = restTemp.getForObject(url, WxAccessTokenVo.class);
		System.out.println(tokenVo.getAccessToken());
		System.out.println(tokenVo.getExpiresIn());
	}
	
	private HttpClient createHttpClient() {
        try {
//            SSLContext sslContext =
//                    SSLContexts.custom()
//                               .loadTrustMaterial(KeyStore.getInstance(KeyStore.getDefaultType()), new HiveTrustAllStrategy())
//                               .build();
        	TrustManager[] tm = { new JEEWeiXinX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());

            return HttpClientBuilder.create()
                                    .setSslcontext(sslContext)
                                    .setHostnameVerifier(new AllowAllHostnameVerifier())
                                    .build();
        }
        catch (Exception e) {
            return HttpClientBuilder.create().build();
        }
    }
	
	class JEEWeiXinX509TrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}
}
