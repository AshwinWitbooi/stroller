package za.co.ashtech.stroller.controller.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "client_id", "client_secret", "audience", "grant_type" })
public class AuthTokenResquest {

	@JsonProperty("client_id")
	private final String clientId;
	@JsonProperty("client_secret")
	private final String clientSecret;
	@JsonProperty("audience")
	private final String audience;
	@JsonProperty("grant_type")
	private final String grantType;	
	
	public AuthTokenResquest(String clientId, String clientSecret, String audience, String grantType) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.audience = audience;
		this.grantType = grantType;
	}

	@JsonProperty("client_id")
	public String getClientId() {
		return clientId;
	}

	@JsonProperty("client_secret")
	public String getClientSecret() {
		return clientSecret;
	}

	@JsonProperty("audience")
	public String getAudience() {
		return audience;
	}

	@JsonProperty("grant_type")
	public String getGrantType() {
		return grantType;
	}
}
