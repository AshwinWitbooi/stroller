package za.co.ashtech.stroller.controller.entities;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "firstname", "lastname", "email", "comment", "comment_timestamp" })
public class StrollUserMessageRequest {

	@NotNull
	@JsonProperty("firstname")
	private String firstname;
	@NotNull
	@JsonProperty("email")
	private String email;
	@NotNull
	@JsonProperty("message")
	private String message;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

		
	public StrollUserMessageRequest(@NotNull String firstname,  @NotNull String email,
			@NotNull String comment) {
		super();
		this.firstname = firstname;
		this.email = email;
		this.message = comment;
	}

	@JsonProperty("firstname")
	public String getFirstname() {
		return firstname;
	}

	@JsonProperty("firstname")
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("message")
	public String getMessage() {
		return message;
	}

	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
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
