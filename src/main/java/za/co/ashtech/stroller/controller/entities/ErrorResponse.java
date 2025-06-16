package za.co.ashtech.stroller.controller.entities;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "status", "message", "timestamp" })
public class ErrorResponse {

	@JsonProperty("status")
	private Integer status;
	@JsonProperty("message")
	private String message;
	@JsonProperty("timestamp")
	private String timestamp;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();
	
	

	public ErrorResponse(HttpStatus status, String message, LocalDateTime timestamp) {
		super();
		this.status = status.value();
		this.message = message;
		this.timestamp = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")
				            .withZone(ZoneId.systemDefault())
				            .format(timestamp);;
	}

	@JsonProperty("status")
	public Integer getStatus() {
		return status;
	}

	@JsonProperty("status")
	public void setStatus(Integer status) {
		this.status = status;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonProperty("timestamp")
	public String getTimestamp() {
		return timestamp;
	}

	@JsonProperty("timestamp")
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
