package za.co.ashtech.stroller.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
import za.co.ashtech.stroller.controller.entities.AuthTokenResponse;
import za.co.ashtech.stroller.controller.entities.AuthTokenResquest;
import za.co.ashtech.stroller.util.LoggingInterceptor;
import za.co.ashtech.stroller.util.StrollerServiceException;

@Slf4j
@RestController
@RequestMapping("/oauth")
public class OAuthAccessTokenController {
	
    @Value("${oauth.token.url}")
	private String tokenUrl;
    @Value("${oauth.clientId}")
	private String clientId;
    @Value("${oauth.clientSecret}")
	private String clientSecret;
    @Value("${oauth.audience}")
	private String audience;
    @Value("${oauth.grantType}")
	private String grantType;
	
    @GetMapping("access/token")
    public ResponseEntity<AuthTokenResponse> getAllCustomers() throws StrollerServiceException{
    	RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
    	AuthTokenResponse authTokenResponse = null;
    	
    	try {
    		restTemplate.setInterceptors(Collections.singletonList(new LoggingInterceptor()));
			authTokenResponse = restTemplate.postForObject(tokenUrl, new AuthTokenResquest(clientId, clientSecret, audience, grantType),AuthTokenResponse.class);
		} catch (RestClientException e) {
			new StrollerServiceException(e);
		}
    	
    	return ResponseEntity.ok(authTokenResponse);
    }

}