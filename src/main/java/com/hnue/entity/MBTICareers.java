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
@Table(name = "mbticareers")
public class MBTICareers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    public Integer id;
    @JsonProperty("Title")
    public String title;
    @JsonProperty("Group")
    public String group;
    @JsonProperty("Description")
    public String description;
}
