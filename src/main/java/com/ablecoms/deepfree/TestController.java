package com.ablecoms.deepfree;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.google.common.collect.ImmutableMap;

@Controller
@RequestMapping(produces= {"application/json"})
public class TestController {
	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	public Map<String, String> dummyBody = ImmutableMap.<String, String>builder()
			.put("a1", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
			.put("a2", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
			.put("a3", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
			.put("a4", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
			.put("a5", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
			.put("a6", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
			.put("a7", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
			.put("a8", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
			.put("a9", "aaaaaaaaaaaaaaaaaaaaaaaaaaaa")
			.build();

	@Autowired ObjectMapper objectMapper;

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
	public Object get201() {
		return dummyBody;
	}

	@RequestMapping("/test400")
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Object get400() {
		return dummyBody;
	}

	@RequestMapping("/test401")
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	@ResponseBody
	public Object get401() {
		return dummyBody;
	}

	@RequestMapping("/test403")
	@ResponseBody
	@ResponseStatus(code=HttpStatus.FORBIDDEN)
	public Object get403() {
		return dummyBody;
	}

	@RequestMapping("/test404")
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ResponseBody
	public Object get404() {
		return dummyBody;
	}

	@RequestMapping("/test409")
	@ResponseStatus(code=HttpStatus.CONFLICT)
	@ResponseBody
	public Object get409() {
		return dummyBody;
	}

	@RequestMapping("/test500")
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Object get500() {
		return dummyBody;
	}

	@RequestMapping("/test503")
	@ResponseStatus(code=HttpStatus.SERVICE_UNAVAILABLE)
	@ResponseBody
	public Object get503() {
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


}
