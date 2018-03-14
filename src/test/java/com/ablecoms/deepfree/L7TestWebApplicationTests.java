package com.ablecoms.deepfree;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.google.common.collect.ImmutableSet;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class L7TestWebApplicationTests {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@BeforeClass
    public static void setUpClass() {
		System.setProperty("logback.configurationFile", "logback-junit.xml");
	}

	private ClientHttpRequestFactory getClientHttpRequestFactory() {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		return new HttpComponentsClientHttpRequestFactory(client);
	}

	@Test
	public void testApiList() {
		ImmutableSet<String> apis = ImmutableSet.of(
				//"test200",
				//"test201",
				"test400",
				//"test401",
				//"test403",
				//"test404",
				//"test409",
				//"test500",
				//"test503",
				//"close200",
				//"close200Direct",
				"direct400KeepAliveCacheControl",
				"direct400KeepAlive",
				"direct400CacheControl",
				"direct400");
		apis.forEach(api->callApi(api));
	}

	private void callApi(String api) {
		try {
			logger.debug("[{} TEST]=================================================", api);
			RestTemplate rest = new RestTemplate(getClientHttpRequestFactory());
			//String response = rest.getForObject("http://localhost:8082/"+api, String.class);
			String response = rest.getForObject("http://tapi.prepaidcard.co.kr/"+api, String.class);
			logger.trace(response);
		} catch (Exception e) {
			logger.warn("{}: {}", e.getClass().getCanonicalName(), e.getMessage());
		}
		logger.debug("");
	}

}
