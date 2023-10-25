package org.launchcode.techjobs.persistent.models;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Skill extends AbstractEntity {

    private String description;

    @NotBlank(message = "Skill is required")
    @Size(min = 3, max = 50, message = "Skill must be between 3 and 50 characters")
    public Skill() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
