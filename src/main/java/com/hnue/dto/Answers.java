package com.hnue.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Answers {
    @JsonProperty("Holland")
    public List<HollandResponse> hollandAnswer;
    @JsonProperty("Mbti")
    public List<MBTIResponse> mbtiAnswer;
}
