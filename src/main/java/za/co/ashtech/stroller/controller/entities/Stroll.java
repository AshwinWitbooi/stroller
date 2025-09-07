package za.co.ashtech.stroller.controller.entities;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "name","description", "location", "longitude", "latitude", "image" })
@Getter
@Setter
@ToString
public class Stroll {
	
	@NotNull
	@JsonProperty("id")
	private Long id;
	@NotNull
	@JsonProperty("name")
	private String name;
	@NotNull
	@JsonProperty("description")
	private String description;
	@NotNull
	@JsonProperty("location")
	private String location;
	@NotNull
	@JsonProperty("longitude")
	private String longitude;
	@NotNull
	@JsonProperty("latitude")
	private String latitude;
	@JsonProperty("image")
	private String image;
	
	
	public Stroll(String name, String description,String location, String longitude, String latitude) {
		super();
		this.name = name;
		this.description = description;
		this.location = location;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	

	
}
