package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Employer extends AbstractEntity {
    @NotBlank(message = "Location is required")
    @Size(min = 3, max = 50, message = "Location must be between 3 and 50 characters")
    private String location;

//    public Employer(String location) {
//        super();
//        this.location = location;
//    }

    public Employer() {}

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
