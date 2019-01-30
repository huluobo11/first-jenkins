package com.example.demo.exception;

import com.example.demo.entity.BaimoResultEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserException extends RuntimeException{                                    	

    private Integer code;

    private String data;

    private String flag;

    public UserException(BaimoResultEnum baimoResultEnum){
        super(baimoResultEnum.getData());
        this.data = baimoResultEnum.getData();
        this.code = baimoResultEnum.getCode();
        this.flag = baimoResultEnum.getFlag();
    }

   /* public UserException(VipSmsResultEnum vipResultEnum){
        super(vipResultEnum.getData());
        this.data = vipResultEnum.getData();
        this.code = vipResultEnum.getCode();
        this.flag = vipResultEnum.getFlag();
    }*/

    public UserException(){

    }
    public UserException(BaimoResultEnum baimoResultEnum, String error){
        super(baimoResultEnum.getData());
        this.code = baimoResultEnum.getCode();
        this.flag = baimoResultEnum.getFlag();
        this.data = error;
    }
}
