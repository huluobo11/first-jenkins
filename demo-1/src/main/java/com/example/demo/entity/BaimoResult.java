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
		this.data = baimoResultEnum.getMsg();
	}
	
	public BaimoResult(BaimoResultEnum baimoResultEnum, Object data) {
		this.code = baimoResultEnum.getCode();
		this.flag = baimoResultEnum.getFlag();
		this.data = data;
	}
}
