package jwc.debug_pro.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Created by chenjinhang on 2017/4/18.
 */
public class WxHttpUtil {
	private static CloseableHttpClient httpClient;
	static{
		PoolingHttpClientConnectionManager clientConnectionManager = new PoolingHttpClientConnectionManager();
		clientConnectionManager.setMaxTotal(1000);
		clientConnectionManager.setDefaultMaxPerRoute(1000);

		RequestConfig config = RequestConfig.custom().setConnectTimeout(8 * 1000)
				.setConnectionRequestTimeout(8 * 1000)				
				.setSocketTimeout(8 * 1000).build();
		httpClient = HttpClients.custom()
				.setDefaultRequestConfig(config)
				.setConnectionManager(clientConnectionManager).build();
	}
	private static final Logger logger = LoggerFactory.getLogger(WxHttpUtil.class);


	public static String get(String url) {
		return get(url,null);
	}

	public static String get(String url, Map<String, String> params) {
		return get(url,params,null);
	}

	public static String post(String url, Map<String, String> params, HttpEntity entity) {
		return post(url, params, entity, httpClient);
	}
	//特殊业务，需要自定义自己的httpClient
	public static String post(String url, Map<String, String> params, HttpEntity entity,CloseableHttpClient paramHttpClient) {
		paramHttpClient=paramHttpClient==null?WxHttpUtil.httpClient:paramHttpClient;
		String result = null;
		HttpPost httpPost = null;
		try {
			StringBuilder stringBuilder = new StringBuilder(url);
			if (params != null) {
				stringBuilder.append("?");
				for (String key : params.keySet()) {
					//这里有可能value为null
					String value = params.get(key);
					if(value==null) {
						continue;
					}
					stringBuilder.append(key).append("=");
					stringBuilder.append(URLEncoder.encode(value, "utf-8"));
				}
			}
			httpPost = new HttpPost(stringBuilder.toString());
			httpPost.addHeader("Content-Type","application/x-www-form-urlencoded");
			httpPost.setEntity(entity);
			CloseableHttpResponse response = paramHttpClient.execute(httpPost);
			try {
				HttpEntity resultEntity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(resultEntity, Charset.forName("UTF-8"));
					EntityUtils.consume(resultEntity);
				}
			} finally {
				response.close();
			}
		} catch (IOException e) {
			logger.error(String.format("IOException when post url=%s params=%s",url,params),e);
		}
		return result;
	}

	public static String get(String url, Map<String, String> params,String cookieName){
		String result = null;
		try {
			StringBuilder stringBuilder = new StringBuilder(url);
			if (params != null) {
				stringBuilder.append("?");
				for (String key : params.keySet()) {
					//这里有可能value为null
					String value = params.get(key);
					if(value==null) {
						continue;
					}
					stringBuilder.append(key).append("=");
					stringBuilder.append(URLEncoder.encode(value, "utf-8"));
					stringBuilder.append("&");
				}
				stringBuilder.deleteCharAt(stringBuilder.length()-1);
			}
			HttpGet httpGet = new HttpGet(stringBuilder.toString());
			if(!StringUtils.isEmpty(cookieName)){
				httpGet.addHeader("Cookie",cookieName);
			}
			CloseableHttpResponse response = httpClient.execute(httpGet);
			try {
				HttpEntity entity = response.getEntity();
				result = EntityUtils.toString(entity, Charset.forName("UTF-8"));
				if (entity != null) {
					EntityUtils.consume(entity);
				}
			} finally {
				response.close();
			}
		} catch (IOException e) {
			logger.error(String.format("IOException when get url=%s params=%s",url,params),e);
		} catch (Exception e){
			logger.error(String.format("IOException when get url=%s params=%s",url,params),e);
		}
		return result;
	}
	
	public static String postJson(String url, String jsonContent, Map<String, String> headers) {
		String result = null;
		HttpPost httpPost = null;
		HttpEntity entity = null;
		try {
			StringBuilder stringBuilder = new StringBuilder(url);
			
			httpPost = new HttpPost(stringBuilder.toString());
			if (jsonContent != null) {
				entity = new StringEntity(jsonContent, ContentType.APPLICATION_JSON);
				httpPost.setEntity(entity);
			}
			httpPost.setEntity(entity);
			if(headers != null) {
				for (Entry<String, String> entry : headers.entrySet()) {
					httpPost.setHeader(entry.getKey(), entry.getValue());
				}
			}
			
			CloseableHttpResponse response = httpClient.execute(httpPost);
			try {
				HttpEntity resultEntity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(resultEntity, Charset.forName("UTF-8"));
					EntityUtils.consume(resultEntity);
				}
			} finally {
				response.close();
			}
		} catch (IOException e) {
			logger.error(String.format("IOException when post url=%s params=%s",url,jsonContent),e);
		}
		return result;
	}
}
