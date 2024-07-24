package com.hnue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MBTIResult {
    public String _id;
    public Long maxTotal;
    public String val;
}
