package com.lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CalculatedEntity {
    private LineDTO line;
    private SurfaceDTO surface;
    private String result;

}
