package com.lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LineDTO {
    private double x0;
    private double y0;
    private double z0;

    private double l;
    private double m;
    private double n;

}
