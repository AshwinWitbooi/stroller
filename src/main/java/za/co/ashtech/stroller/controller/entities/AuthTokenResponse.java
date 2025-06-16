package za.co.ashtech.stroller.controller.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "access_token", "expire", "token_type" })
public class AuthTokenResponse {

	@JsonProperty("access_token")
	private final String accessToken;
	@JsonProperty("expire")
	private final Integer expire;
	@JsonProperty("access_type")
	private final String tokenType;
	
	public AuthTokenResponse(String accessToken, Integer expire, String tokenType) {
		super();
		this.accessToken = accessToken;
		this.expire = expire;
		this.tokenType = tokenType;
	}

	@JsonProperty("access_token")
	public String getAccessToken() {
		return accessToken;
	}

	@JsonProperty("expire")
	public Integer getExpire() {
		return expire;
	}
	
	@JsonProperty("access_type")
	public String getTokenType() {
		return tokenType;
	}

}
