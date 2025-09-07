package za.co.ashtech.stroller.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingInterceptor implements ClientHttpRequestInterceptor {

	@Override
	public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
			throws IOException {
		
        logRequestDetails(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        logResponseDetails(response,request.getURI().getHost());
        	
        return response;
	}
	
	  private void logRequestDetails(HttpRequest request, byte[] body) throws IOException {
	        log.info("===== HTTP Request =====");
	        log.info("URI         : {}", request.getURI());
	        log.info("Method      : {}", request.getMethod());
	        log.info("Headers     : {}", request.getHeaders());
	        
	        if(request.getMethod().toString().equalsIgnoreCase("POST")) {
	        	 log.info("Request Body: {}", new String(body, StandardCharsets.UTF_8));
	        }	       
	    }

	    private void logResponseDetails(ClientHttpResponse response, String requestHost) throws IOException {
	        String body = new BufferedReader(new InputStreamReader(response.getBody(), StandardCharsets.UTF_8))
	                .lines()
	                .collect(Collectors.joining("\n"));

	        log.info("===== HTTP Response =====");
	        log.info("Status Code  : {}", response.getStatusCode());
	        log.info("Status Text  : {}", response.getStatusText());
	        log.info("Headers      : {}", response.getHeaders());
	        log.info("Response Body: {}", body);
	    }

}
