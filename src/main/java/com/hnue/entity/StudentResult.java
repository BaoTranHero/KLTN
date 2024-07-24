package com.hnue.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "studentresult")
public class StudentResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("Id")
    public int id;
    @JsonProperty("Realistic")
    public int realistic;
    @JsonProperty("Investigate")
    public int investigate;
    @JsonProperty("Artistic")
    public int artistic;
    @JsonProperty("Social")
    public int social;
    @JsonProperty("Enterprising")
    public int enterprising;
    @JsonProperty("Conventional")
    public int conventional;
    @JsonProperty("Extrovert")
    public int extrovert;
    @JsonProperty("Introvert")
    public int introvert;
    @JsonProperty("Thinking")
    public int thinking;
    @JsonProperty("Feeling")
    public int feeling;
    @JsonProperty("Sensing")
    public int sensing;
    @JsonProperty("Intuition")
    public int intuition;
    @JsonProperty("Judging")
    public int judging;
    @JsonProperty("Perceiving")
    public int perceiving;
    @JsonProperty("HollandCode")
    public String hollandCode;
    @JsonProperty("MBTI")
    public String mBTI;
    public double math;
    public double literature;
    public double english;
}
