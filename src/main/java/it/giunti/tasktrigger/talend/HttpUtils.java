package it.giunti.tasktrigger.talend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.params.BasicHttpParams;

public class HttpUtils {

	public static String executeRequest(String url, String token) throws IOException {
		StringBuilder result = new StringBuilder();
		
		CloseableHttpClient httpClient = getCloseableHttpClient();
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("Accept", "application/json");
		getRequest.addHeader("Authorization", "Bearer "+token);
		CloseableHttpResponse closeableResponse = httpClient.execute(getRequest);
		BufferedReader rd = new BufferedReader(new InputStreamReader(closeableResponse.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		return result.toString();	
	}
	
	public static CloseableHttpClient getCloseableHttpClient() throws IOException {
		final SSLConnectionSocketFactory sslsf;
		try {
			sslsf = new SSLConnectionSocketFactory(SSLContext.getDefault(), NoopHostnameVerifier.INSTANCE);
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}

		final Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
				.register("http", new PlainConnectionSocketFactory()).register("https", sslsf).build();

		final PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
		cm.setMaxTotal(100);
		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).setConnectionManager(cm)
				.build();
		
		return httpclient;
//
//		String BU_API_URL = "https://xxxx:300yyy/springController/v2.0/getUnits?team=team1";
//		BufferedReader rd = null;
//		long startTime = System.nanoTime();
//		long endTime = 0;
//		String teamCode = "team1";
//
//		// Invoke Rest API
//
//		try {
//			HttpGet getRequest = new HttpGet(BU_API_URL);
//			getRequest.addHeader("content-type", "application/json");
//			getRequest.addHeader("Username", "yyy@gmail.com");
//			getRequest.addHeader("Apikey", "40XXXXXXa");
//			BasicHttpParams params = new BasicHttpParams();
//			params.setParameter("team", teamCode);
//			getRequest.setParams(params);
//			CloseableHttpResponse closeableResponse = httpclient.execute(getRequest);
//			endTime = System.nanoTime();
//			int status = closeableResponse.getStatusLine().getStatusCode();
//			System.out.println("Response Code : " + status);
//			rd = new BufferedReader(new InputStreamReader(closeableResponse.getEntity().getContent()));
//			StringBuilder result = new StringBuilder();
//			String line = "";
//			while ((line = rd.readLine()) != null) {
//				result.append(line);
//			}
//			System.out.println("Response result : " + result.toString());
//		} catch (IOException ioe) {
//			ioe.printStackTrace();
//		} finally {
//			if (rd != null)
//				rd.close();
//		}
	}
}
