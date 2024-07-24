package com.hnue.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hollandcareersdescription")
public class HollandCareersDescription {
    @Id
    @JsonProperty("ID")
    public String id;
    @JsonProperty("Title")
    public String title;
    @JsonProperty("Description")
    public String description;
}
