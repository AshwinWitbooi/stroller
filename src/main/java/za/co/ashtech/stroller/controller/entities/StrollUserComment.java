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
public class StrollUserComment {

	@NotNull
	@JsonProperty("firstname")
	private String firstname;
	@NotNull
	@JsonProperty("lastname")
	private String lastname;
	@NotNull
	@JsonProperty("email")
	private String email;
	@NotNull
	@JsonProperty("comment")
	private String comment;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

		
	public StrollUserComment(@NotNull String firstname, @NotNull String lastname, @NotNull String email,
			@NotNull String comment) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.comment = comment;
	}

	@JsonProperty("firstname")
	public String getFirstname() {
		return firstname;
	}

	@JsonProperty("firstname")
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@JsonProperty("lastname")
	public String getLastname() {
		return lastname;
	}

	@JsonProperty("lastname")
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@JsonProperty("email")
	public String getEmail() {
		return email;
	}

	@JsonProperty("email")
	public void setEmail(String email) {
		this.email = email;
	}

	@JsonProperty("comment")
	public String getComment() {
		return comment;
	}

	@JsonProperty("comment")
	public void setComment(String comment) {
		this.comment = comment;
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
