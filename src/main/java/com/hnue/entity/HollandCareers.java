package com.hnue.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "hollandcareers")
public class HollandCareers {
    @Id
    @JsonProperty("ID")
    public String id;
    @JsonProperty("Title")
    public String title;
    @JsonProperty("Holland_Code")
    public String hollandCode;
}
