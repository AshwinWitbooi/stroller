package za.co.ashtech.stroller.config;

import java.io.IOException;
import java.util.Collections;

import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoggingFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper((HttpServletRequest) request);
		ContentCachingResponseWrapper wrappedResponse = new ContentCachingResponseWrapper(
				(HttpServletResponse) response);
		
			
		 Collections.list(wrappedRequest.getHeaderNames())
		 .forEach(header -> log.info("HEADER {} : {}",header,wrappedRequest.getHeader(header)));

		chain.doFilter(wrappedRequest, wrappedResponse);
		
		
		if(wrappedRequest.getMethod().equalsIgnoreCase("POST")) {
			// Log request body
			String requestBody = new String(wrappedRequest.getContentAsByteArray());
			log.info("REQUEST BODY {}: ",requestBody.replaceAll("\\s", "").replaceAll("\\r\\n", ""));
			
		}

		// Log response body
		String responseBody = new String(wrappedResponse.getContentAsByteArray());
		log.info("RESPONSE BODY {}: ",responseBody.replaceAll("\\s", "").replaceAll("\\r\\n", ""));
		
        // VERY IMPORTANT: copy body content back to the response output stream
		wrappedResponse.copyBodyToResponse();

	}

}
