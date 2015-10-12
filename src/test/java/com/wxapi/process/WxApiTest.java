package com.wxapi.process;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
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
		
//		String resStr = httpsRequest(url, "GET", null);
//		String resStr = getAccess_token(url);
//		System.out.println(resStr);
		
		RestTemplate restTemp = new RestTemplate();
//		restTemp.setRequestFactory(new HttpComponentsClientHttpRequestFactory(createHttpClient()));
		List<HttpMessageConverter<?>> converters = new ArrayList<>(5);
    	converters.add(new StringHttpMessageConverter());
        converters.add(new MappingJackson2HttpMessageConverter());
        converters.add(new ByteArrayHttpMessageConverter());
        restTemp.setMessageConverters(converters);
//		restTemp.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
//		String reStr = restTemp.getForObject(url, String.class);
//		System.out.println(reStr);
		WxAccessTokenVo tokenVo = restTemp.getForObject(url, WxAccessTokenVo.class);
		System.out.println(tokenVo);
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
	
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
//		JSONObject jsonObject = null;
		try {
			TrustManager[] tm = { new JEEWeiXinX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			
			URL url = new URL(requestUrl);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);

			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod(requestMethod);
			if (null != outputStr) {
				OutputStream outputStream = conn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			InputStream inputStream = conn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			conn.disconnect();
			return buffer.toString();
//			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	static class JEEWeiXinX509TrustManager implements X509TrustManager {
		public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
		public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
		}
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}
	
	static class HiveTrustAllStrategy implements TrustStrategy {

	    public boolean isTrusted(
	            final X509Certificate[] chain, final String authType) throws CertificateException {
	        return true;
	    }

	}
	
	
	public  String getAccess_token(String url) {
	       String accessToken = null;

	       try {

	           URL urlGet = new URL(url);

	           HttpURLConnection http = (HttpURLConnection) urlGet

	                   .openConnection();

	           http.setRequestMethod("GET"); // 必须是get方式请求

	           http.setRequestProperty("Content-Type",

	                   "application/x-www-form-urlencoded");

	           http.setDoOutput(true);

	           http.setDoInput(true);

	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒

	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

	           http.connect();

	           InputStream is = http.getInputStream();

	           int size = is.available();

	           byte[] jsonBytes = new byte[size];

	           is.read(jsonBytes);

	           String message = new String(jsonBytes, "UTF-8");

	           accessToken = message;

	           System.out.println(accessToken);

	           is.close();

	       } catch (Exception e) {

	           e.printStackTrace();

	       }

	       return accessToken;

	   }
}
