package com.lab4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {
    private LineDTO line;
    private SurfaceDTO surface;
    private String result;
    private int id;



    public ResponseDTO calculate(LineDTO line, SurfaceDTO surface){
        double[] surfaceVector = getSurfaceVector(surface);
        double[] lineVector = getLineVector(line);
        double angle = findAngle(surfaceVector, lineVector);
        result = "";
        ResponseDTO response = new ResponseDTO(line,surface,result,id);
        return response;
    }

    private double findAngle(double[] surfaceVector, double[] lineVector) {
        double surfVectorModule = calculateModule(surfaceVector);
        double lineVectorModule = calculateModule(lineVector);
        double scalMult = calculateScalMult(surfaceVector, lineVector);
        double angle = 0;
        return angle;
    }

    private double calculateScalMult(double[] surfaceVector, double[] lineVector) {
        double sum = surfaceVector[0]*lineVector[0] + surfaceVector[1]*lineVector[1] + surfaceVector[2]*lineVector[2];
        return Math.abs(sum);
    }

    private double calculateModule(double[] vector) {
        double sumSquare = vector[0]*vector[0] + vector[1]*vector[1] + vector[2]*vector[2];
        return Math.sqrt(sumSquare);
    }

    private double[] getLineVector(LineDTO line) {
        double[] vector = new double[3];
        vector[0] = line.getL();
        vector[1] = line.getM();
        vector[2] = line.getN();

        return vector;
    }

    private double[] getSurfaceVector(SurfaceDTO surface) {
        double[] vector = new double[3];
        vector[0] = surface.getA();
        vector[1] = surface.getB();
        vector[2] = surface.getC();

        return vector;
    }
}
