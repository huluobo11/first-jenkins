package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestQQ {
	
	@RequestMapping("/login")
	public Map<String, Object> login(HttpServletRequest request, HttpSession session) {
		Map<String, Object> result = new HashMap<>();
		//  1.用户在你的网页上 点了qq登录后，进入这个接口，
		try {
			String authorizeURL = new Oauth().getAuthorizeURL(request);
			//  2,你把这个url返回给前端 ，让他跳转到这个url 去，然后会弹出qq登录 的页面，他选择qq用户或者用qq账号密码登录 ，并且授权，
			result.put("url", authorizeURL);
			result.put("code", 200);
			
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
		return result; 
	}
	
	@RequestMapping("/qqlogin")
	public void qqLogin(HttpServletRequest request, HttpSession session) {
		//  3。用户的qq登录 好，并且授权后，会自动进入这个接口，然后你取到了登录 的用户的信息，然后放数据库里 ， 或者 其他地方。
		AccessToken accessTokenObj = new AccessToken();
		try {
			accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
		} catch (QQConnectException e) {
			e.printStackTrace();
		}
		String accessToken = null, openID = null;
		long tokenExpireIn = 0L;
		if (accessTokenObj.getAccessToken().equals("")) {
//              我们的网站被CSRF攻击了或者用户取消了授权
//              做一些数据统计工作
			System.out.print("没有获取到响应参数");
		} else {
			accessToken = accessTokenObj.getAccessToken();
			tokenExpireIn = accessTokenObj.getExpireIn();

			session.setAttribute("demo_access_token", accessToken);
			session.setAttribute("demo_token_expirein", String.valueOf(tokenExpireIn));

			// 利用获取到的accessToken 去获取当前用的openid -------- start
			OpenID openIDObj = new OpenID(accessToken);
			try {
				openID = openIDObj.getUserOpenID();
			} catch (QQConnectException e) {
				e.printStackTrace();
			}

			log.info("欢迎你，代号为 " + openID + " 的用户!");
			session.setAttribute("demo_openid", openID);
			// 利用获取到的accessToken 去获取当前用户的openid --------- end
			log.info(
					"<p> start -----------------------------------利用获取到的accessToken,openid 去获取用户在Qzone的昵称等信息 ---------------------------- start </p>");
			UserInfo qzoneUserInfo = new UserInfo(accessToken, openID);
			UserInfoBean userInfoBean = null;
			try {
				userInfoBean = qzoneUserInfo.getUserInfo();
			} catch (QQConnectException e) {
				e.printStackTrace();
			}
			log.info("<br/>");
			if (userInfoBean.getRet() == 0) {
				log.info(userInfoBean.getNickname() + "<br/>");
				log.info(userInfoBean.getGender() + "<br/>");
			} else {
				log.info("很抱歉，我们没能正确获取到您的信息，原因是： " + userInfoBean.getMsg());
			}
		}
		
		
	}

}
