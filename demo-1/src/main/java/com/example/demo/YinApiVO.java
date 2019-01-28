package com.example.demo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import lombok.Data;

@Data
public class YinApiVO implements Serializable{

	private String appId;
	
	private  String appPwd;
	
	private Map<String, Object> data;
	
	
	
	public  String main(String[] args) {
		
		
		YinApiVO yinApiVO = new YinApiVO();
		yinApiVO.setAppId("从网站上取  ");
		yinApiVO.setAppPwd(" 从网站 上取  ");
		HashMap<String, Object> data = new HashMap<>();
		data.put("tradeType", "0601");// 查询类型    6要素
		data.put("cardNo", " 你自己 从前端 取到后，进行RSA加密  ");// 卡号
		data.put("customerNm", " 你自己 从前端 取  ");// 姓名
		data.put("phoneNo", " 你自己 从前端 取  ");// 手机号
		data.put("certifId", " 你自己 从前端 取到后，进行RSA加密  ");// 证件号
		data.put("certifTp", " 你自己 从前端 取  ");// 证件类型
		data.put("CVN2", " 你自己 从前端 取到后，进行RSA加密  ");// 银行卡CVN2
		data.put("expired", " 你自己 从前端 取到后，进行RSA加密   ");// 银行卡有效期
		
		Random rand = new Random();
		DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		//生成4位随机数
		String random = new StringBuilder().append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).append(rand.nextInt(10)).toString();
		//把当前时间格式化
		String dateStr = dateTimeFormat.format(LocalDateTime.now());
		data.put("orderId", dateStr + random);// 订单号  ，yyyyMMddHHmmss+四位随机数
		// 交易场景字典：01 直销银行； 02 消费金融； 03 银行二三类账户开户； 04	征信； 05 保险； 06 基金； 07 证券； 08 租赁； 09 海关申报； 99 其他
		data.put("transCode", "你自己从上面选择一个。  ");//交易场景编码，
		
		String url = "https://auth.95516.com/authonl/onlineAuth/rest/verify/bankcard"; 
		String body = JSON.toJSONString(yinApiVO);
		String result = HttpUtil.post(url, body);
		System.out.println(result);
		//根据result  来判断  接口调用成功没有。
		return null;
	}
}
