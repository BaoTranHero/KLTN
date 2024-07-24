package com.hnue.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CareersResponse {
    @JsonIgnore
    public String id;
    public String ngheNghiep;
    public String moTa;
    public List<String> yeuCau;
    public List<String> daoTao;
    public List<String> lienQuan;
}
