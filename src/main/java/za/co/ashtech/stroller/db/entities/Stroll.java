package za.co.ashtech.stroller.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "STROLL")
public class Stroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "stroll_id", nullable = false)
    private Integer strollId;

    @Column(name="stroll_name", nullable = false)
    private String strollName;
    
    private String description;

    private String location;

    private Double latitude;

    private Double longitude;
    
    private String image;

    // Constructors
    public Stroll() {
    }
    
    public Stroll(Integer strollId, String strollName, String description, String location, Double latitude,
			Double longitude, String image) {
		super();
		this.strollId = strollId;
		this.strollName = strollName;
		this.description = description;
		this.location = location;
		this.latitude = latitude;
		this.longitude = longitude;
		this.image = image;
	}





	// Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
		this.id = id;
	}

	public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStrollId() {
		return strollId;
	}



	public void setStrollId(Integer strollId) {
		this.strollId = strollId;
	}



	public String getStrollName() {
		return strollName;
	}



	public void setStrollName(String strollName) {
		this.strollName = strollName;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Stroll [id=" + id + ", strollId=" + strollId + ", strollName=" + strollName + ", description="
				+ description + ", location=" + location + ", latitude=" + latitude + ", longitude=" + longitude + ", image=" + image + "]";
	}
}