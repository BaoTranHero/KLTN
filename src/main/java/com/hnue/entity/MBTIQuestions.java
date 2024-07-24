package com.hnue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mbtiquestions")
public class MBTIQuestions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String ques;
    public String id_C;
    public String id_V;
    public String ans1;
    public String ans2;
    public String val1;
    public String val2;
    public String group;
}
