package za.co.ashtech.stroller.db.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "STROLLER")
public class StrollerDEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String location;

    private Double latitude;

    private Double longitude;

    // Constructors
    public StrollerDEntity() {
    }

    public StrollerDEntity(String name, String location, Double latitude, Double longitude) {
        this.name = name;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

	@Override
	public String toString() {
		return "StrollerDEntity [id=" + id + ", name=" + name + ", location=" + location + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}
}