package com.Bestanome.Model.Objets.Plan;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Point {

    @JsonProperty
    private Long id;

    @JsonProperty
    private double latitude;

    @JsonProperty
    private double longitude;

    @JsonProperty
    private TypePoint type;

    // Constructors, getters, and setters
    public Point(Long id, double latitude, double longitude, TypePoint type) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public TypePoint getType() {
        return type;
    }

    public void setType(TypePoint type) {
        this.type = type;
    }
}
