package com.ablecoms.deepfree;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.ImmutableMap;

@RestController
@RequestMapping(produces= {"application/json"})
public class TestController {

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
	
	@RequestMapping("/test200")
	@ResponseStatus(code=HttpStatus.OK)
	public Object get200() {
		return dummyBody;
	}

	@RequestMapping("/test201")
	@ResponseStatus(code=HttpStatus.CREATED)
	public Object get201() {
		return dummyBody;
	}
	
	@RequestMapping("/test400")
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public Object get400() {
		return dummyBody;
	}
	
	@RequestMapping("/test401")
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	public Object get401() {
		return dummyBody;
	}
	
	@RequestMapping("/test403")
	@ResponseStatus(code=HttpStatus.FORBIDDEN)
	public Object get403() {
		return dummyBody;
	}
	
	@RequestMapping("/test404")
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	public Object get404() {
		return dummyBody;
	}

	@RequestMapping("/test409")
	@ResponseStatus(code=HttpStatus.CONFLICT)
	public Object get409() {
		return dummyBody;
	}
	
	@RequestMapping("/test500")
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	public Object get500() {
		return dummyBody;
	}

	@RequestMapping("/test503")
	@ResponseStatus(code=HttpStatus.SERVICE_UNAVAILABLE)
	public Object get503() {
		return dummyBody;
	}
	
	
}
