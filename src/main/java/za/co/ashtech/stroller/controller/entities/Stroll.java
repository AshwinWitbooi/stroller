package za.co.ashtech.stroller.controller.entities;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name", "location", "longitude", "latitude" })
@Getter
@Setter
@ToString
public class Stroll {

	@NotNull
	@JsonProperty("name")
	private String name;
	@NotNull
	@JsonProperty("location")
	private String location;
	@NotNull
	@JsonProperty("longitude")
	private String longitude;
	@NotNull
	@JsonProperty("latitude")
	private String latitude;
	
	
	public Stroll(String name, String location, String longitude, String latitude) {
		super();
		this.name = name;
		this.location = location;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	

	
}
