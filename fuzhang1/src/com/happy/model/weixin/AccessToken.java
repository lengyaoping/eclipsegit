package com.happy.model.weixin;

public class AccessToken {
	private String access_token;// 网页获取用户信息凭证
	private String expires_in;// 有效时间

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public String getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(String expires_in) {
		this.expires_in = expires_in;
	}

}
