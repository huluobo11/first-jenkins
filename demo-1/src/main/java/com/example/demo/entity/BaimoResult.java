package com.example.demo.entity;

import lombok.Data;

@Data
public class BaimoResult {

	private Integer code;
	
	private String flag;
	
	private Object data;
	
	public BaimoResult(BaimoResultEnum baimoResultEnum) {
		this.code = baimoResultEnum.getCode();
		this.flag = baimoResultEnum.getFlag();
		this.data = baimoResultEnum.getData();
	}
	
	public BaimoResult(BaimoResultEnum baimoResultEnum, Object data) {
		this.code = baimoResultEnum.getCode();
		this.flag = baimoResultEnum.getFlag();
		this.data = data;
	}

	public BaimoResult(Integer code, String flag, String data) {
		this.code = code;
		this.flag = flag;
		this.data = data;
	}
}
