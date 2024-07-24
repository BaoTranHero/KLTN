package com.hnue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "hollandquestions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HollandQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String ques;
    public String group2;
    public String group;
    public Integer val1;
    public Integer val2;
    public Integer val3;
    public Integer val4;
    public Integer val5;
    public String id_C;
    public String id_V;
}
