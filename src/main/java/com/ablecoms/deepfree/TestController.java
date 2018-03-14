package com.ablecoms.deepfree;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping(produces= {"application/json"})
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	public Map<String, Object> dummyBody = new HashMap<String, Object>();

//	public String dummyBody = "{\"canRetry\":false,\"verboseMessage\":null,\"errorCode\":\"requestId.previousAttempts.invalid\",\"message\":\"requestId.previousAttempts.invalid\",\"statusReasonPhrase\":\"Bad\r\n" +
//			"Request\",\"timestamp\":\"2017-12-14T13:10:41.338+0900\",\"status\":400}";


	public TestController() {
		super();
		dummyBody.put("canRetry", false);
		dummyBody.put("verboseMessage", null);
		dummyBody.put("errorCode", "requestId.previousAttempts.invalid");
		dummyBody.put("message", "requestId.previousAttempts.invalid");
		dummyBody.put("statusReasonPhrase", "Bad Request");
		dummyBody.put("timestamp", "2017-11-17T17:50:22.351+0900");
		dummyBody.put("status", 400);
	}


	@Autowired ObjectMapper objectMapper;

	private void setResponseHeader(HttpServletResponse response) {
		response.addHeader("Connection", "Keep-Alive");
		response.addHeader("Keep-Alive", "timeout=5, max=100");
		response.addHeader("Set-Cookie", "JSESSIONID=418120EBDDF55937C157AC82E3FABD5B; Path=/; Secure; HttpOnly");
		response.addHeader("X-Content-Type-Options", "nosniff");
		response.addHeader("X-XSS-Protection", "1; mode=block");
		response.addHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Expires", "0");
		response.addHeader("X-Frame-Options", "SAMEORIGIN");
		response.addHeader("Set-Cookie", "SCOUTER=x6u24mcvcrsfp4; Max-Age=2147483647; Expires=Tue, 01-Jan-2086 07:24:48 GMT");
		response.addHeader("X-Application-Context", "centralapi:oracle,db-oracle-dev,db-mongo-dev,db-redis-dev,development:8082");
		response.addHeader("Cache-Control", "no-store");
		response.addHeader("Content-Language", "ko-KR");
	}

	@RequestMapping("/test200")
	@ResponseStatus(code=HttpStatus.OK)
	@ResponseBody
	public Object get200(HttpServletResponse response) {
		int oldBufferSize = response.getBufferSize();
		response.setBufferSize(8*1024); //8K
		int newBufferSize = response.getBufferSize();
		logger.warn("response.bufferSize: {} => {}", oldBufferSize, newBufferSize);
		return dummyBody;
	}

	@RequestMapping("/test201")
	@ResponseStatus(code=HttpStatus.CREATED)
	@ResponseBody
	public Object get201(HttpServletResponse response) {
		return dummyBody;
	}

	@RequestMapping("/test400")
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object get400(HttpServletResponse response) {
		return dummyBody;
	}

	@RequestMapping("/test401")
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public Object get401(HttpServletResponse response) {
		return dummyBody;
	}

	@RequestMapping("/test403")
	@ResponseBody
	@ResponseStatus(code=HttpStatus.FORBIDDEN)
	public Object get403(HttpServletResponse response) {
		return dummyBody;
	}

	@RequestMapping("/test404")
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ResponseBody
	public Object get404(HttpServletResponse response) {
		return dummyBody;
	}

	@RequestMapping("/test409")
	@ResponseStatus(code=HttpStatus.CONFLICT)
	@ResponseBody
	public Object get409(HttpServletResponse response) {
		return dummyBody;
	}

	@RequestMapping("/test500")
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Object get500(HttpServletResponse response) {
		return dummyBody;
	}

	@RequestMapping("/test503")
	@ResponseStatus(code=HttpStatus.SERVICE_UNAVAILABLE)
	@ResponseBody
	public Object get503(HttpServletResponse response) {
		return dummyBody;
	}

	@RequestMapping("/close200")
	@ResponseStatus(code=HttpStatus.OK)
	@ResponseBody
	public Object close200(HttpServletResponse response) {
		response.addHeader(HttpHeaders.CONNECTION, "close");
		return dummyBody;
	}

	@RequestMapping("/close200Direct")
	@ResponseStatus(code=HttpStatus.OK)
	public void close200Direct(HttpServletResponse response) throws IOException {
		response.addHeader(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		response.addHeader(HttpHeaders.CONNECTION, "close");
		String data = objectMapper.writeValueAsString(dummyBody);
		PrintWriter writer = response.getWriter();
		writer.append(data).flush();
	}

	@RequestMapping("/close400")
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object close400(HttpServletResponse response) {
		response.addHeader(HttpHeaders.CONNECTION, "close");
		return dummyBody;
	}

	@RequestMapping("/direct400KeepAliveCacheControl")
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object direct400KeepAliveCacheControl(HttpServletResponse response) throws IOException {
		response.addHeader("Connection", "Keep-Alive");
		response.addHeader("Keep-Alive", "timeout=5, max=100");
		response.addHeader("Set-Cookie", "JSESSIONID=418120EBDDF55937C157AC82E3FABD5B; Path=/; Secure; HttpOnly");
		response.addHeader("X-Content-Type-Options", "nosniff");
		response.addHeader("X-XSS-Protection", "1; mode=block");
		response.addHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Expires", "0");
		response.addHeader("X-Frame-Options", "SAMEORIGIN");
		response.addHeader("Set-Cookie", "SCOUTER=x6u24mcvcrsfp4; Max-Age=2147483647; Expires=Tue, 01-Jan-2086 07:24:48 GMT");
		response.addHeader("X-Application-Context", "centralapi:oracle,db-oracle-dev,db-mongo-dev,db-redis-dev,development:8082");
		response.addHeader("Cache-Control", "no-store");
		response.addHeader("Content-Language", "ko-KR");
		return dummyBody;
	}

	@RequestMapping("/direct400KeepAlive")
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object direct400KeepAlive(HttpServletResponse response) throws IOException {
		response.addHeader("Connection", "Keep-Alive");
		response.addHeader("Keep-Alive", "timeout=5, max=100");
		response.addHeader("Set-Cookie", "JSESSIONID=418120EBDDF55937C157AC82E3FABD5B; Path=/; Secure; HttpOnly");
		response.addHeader("X-Content-Type-Options", "nosniff");
		response.addHeader("X-XSS-Protection", "1; mode=block");
		response.addHeader("X-Frame-Options", "SAMEORIGIN");
		response.addHeader("Set-Cookie", "SCOUTER=x6u24mcvcrsfp4; Max-Age=2147483647; Expires=Tue, 01-Jan-2086 07:24:48 GMT");
		response.addHeader("X-Application-Context", "centralapi:oracle,db-oracle-dev,db-mongo-dev,db-redis-dev,development:8082");
		response.addHeader("Content-Language", "ko-KR");
		return dummyBody;
	}

	@RequestMapping("/direct400CacheControl")
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object direct400CacheControl(HttpServletResponse response) throws IOException {
		response.addHeader("Set-Cookie", "JSESSIONID=418120EBDDF55937C157AC82E3FABD5B; Path=/; Secure; HttpOnly");
		response.addHeader("X-Content-Type-Options", "nosniff");
		response.addHeader("X-XSS-Protection", "1; mode=block");
		response.addHeader("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Expires", "0");
		response.addHeader("X-Frame-Options", "SAMEORIGIN");
		response.addHeader("Set-Cookie", "SCOUTER=x6u24mcvcrsfp4; Max-Age=2147483647; Expires=Tue, 01-Jan-2086 07:24:48 GMT");
		response.addHeader("X-Application-Context", "centralapi:oracle,db-oracle-dev,db-mongo-dev,db-redis-dev,development:8082");
		response.addHeader("Cache-Control", "no-store");
		response.addHeader("Content-Language", "ko-KR");
		return dummyBody;
	}

	@RequestMapping("/direct400")
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object direct400(HttpServletResponse response) throws IOException {
		response.addHeader("Set-Cookie", "JSESSIONID=418120EBDDF55937C157AC82E3FABD5B; Path=/; Secure; HttpOnly");
		response.addHeader("X-Content-Type-Options", "nosniff");
		response.addHeader("X-XSS-Protection", "1; mode=block");
		response.addHeader("X-Frame-Options", "SAMEORIGIN");
		response.addHeader("Set-Cookie", "SCOUTER=x6u24mcvcrsfp4; Max-Age=2147483647; Expires=Tue, 01-Jan-2086 07:24:48 GMT");
		response.addHeader("X-Application-Context", "centralapi:oracle,db-oracle-dev,db-mongo-dev,db-redis-dev,development:8082");
		response.addHeader("Content-Language", "ko-KR");
		return dummyBody;
	}


}


