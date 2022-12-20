package com.lab4.services;

import com.lab4.dto.LineDTO;
import com.lab4.dto.CalculatedEntity;
import com.lab4.dto.SurfaceDTO;
import com.lab4.util.Translator;
import org.springframework.stereotype.Service;

import java.util.Locale;


@Service
public class CalculationService {
    public CalculatedEntity calculate(LineDTO line, SurfaceDTO surface, Locale locale){
        double[] surfaceVector = getSurfaceVector(surface);
        double[] lineVector = getLineVector(line);
        if(check(lineVector)){
            double angle = findAngle(surfaceVector, lineVector);
            return new CalculatedEntity(line,surface, Translator.writeResult(angle,locale,calculateScalMult(surfaceVector,lineVector)));
        }
        else {
            return new CalculatedEntity(line,surface,"Bad input");
        }

    }

    private boolean check(double[] lineVector) {
        for (double temp:
             lineVector) {
            if(temp == 0){
                return false;
            }
        }
        return true;
    }

    private double findAngle(double[] surfaceVector, double[] lineVector) {
        double surfVectorModule = calculateModule(surfaceVector);
        double lineVectorModule = calculateModule(lineVector);
        double scalMult = calculateScalMult(surfaceVector, lineVector);
        if(surfVectorModule*lineVectorModule != 0) {
            double temp = Math.ceil(surfVectorModule * lineVectorModule);
            double result = Math.asin(scalMult / temp);
            return result*(180/Math.PI);
        }
        else {
            return -100;
        }
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
