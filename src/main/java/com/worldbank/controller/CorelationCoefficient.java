package com.worldbank.controller;

import java.util.ArrayList;
import java.util.List;

/*
            Correlation Co-efficient Formula :
            Correlation(r) = NΣXY - (ΣX)(ΣY) / Sqrt([NΣX2 - (ΣX)2][NΣY2 - (ΣY)2])
            Where,
            N = Number of values or elements
            X = First Score
            Y = Second Score
            ΣXY = Sum of the product of first and Second Scores
            ΣX = Sum of First Scores
            ΣY = Sum of Second Scores
            ΣX2 = Sum of square First Scores
            ΣY2 = Sum of square Second Scores
*/

public class CorelationCoefficient {

    List<Double> nonNullAdultLiteracyRate;
    List<Double> nonNullInternetUser;

    public CorelationCoefficient() {

        nonNullAdultLiteracyRate = new ArrayList<>();
        nonNullInternetUser = new ArrayList<>();
    }

    public double computeCorelationCoefficient(List<Double> adultLiteracyRate, List<Double> internetUsers) {

        int count = adultLiteracyRate.size();

        // Compute the non Null List of Adult Literate Rate and Internet Users in List
        for (int i = 0; i < count; i++) {
            if ((adultLiteracyRate.get(i) == null) || (internetUsers.get(i) == null)) { } else {
                nonNullAdultLiteracyRate.add(adultLiteracyRate.get(i));
                nonNullInternetUser.add(internetUsers.get(i));
            }
        }

        // Compute Square of Adult Literate Rate and Internet User in nonNull List;

        int nonNullSize = nonNullAdultLiteracyRate.size();
        System.out.printf("No of Values to Compute Correlation Coefficient : %d%n",nonNullSize);
        double[] x2 = new double[nonNullSize]; // nonNullAdultLiteracyRate
        x2 = computeSquare(nonNullSize,nonNullAdultLiteracyRate);
        double[] y2 = new double[nonNullSize]; // nonNullInternetUser
        y2 = computeSquare(nonNullSize,nonNullInternetUser);



        // Compute ΣX,ΣY,ΣXY,ΣX2,ΣY2
        double sumX =0;
        double sumY = 0;
        double sumXY = 0;
        double sumX2 = 0;
        double sumY2 = 0;

        for (int i = 0; i < nonNullSize; i++) {
            double x = (double)nonNullAdultLiteracyRate.get(i);
            double y = (double)nonNullInternetUser.get(i);
            sumX = sumX + x;
            sumY = sumY + y;
            sumXY = sumXY + (x * y);
            sumX2 = sumX2 + x2[i];
            sumY2 = sumY2 + y2[i];
        }
        // Compute Numerator = (NΣXY - (ΣX)(ΣY))
        double numerator = ((nonNullSize * sumXY)- (sumX * sumY));

        // Compute Denominator = Sqrt([NΣX2 - (ΣX)2][NΣY2 - (ΣY)2])
        double denominator1 = ((nonNullSize*sumX2)-(Math.pow(sumX,2)));
        double denominator2 = ((nonNullSize*sumY2)-(Math.pow(sumY,2)));
        double denominator3 = denominator1*denominator2;
        double denominator = Math.sqrt(denominator3);

        // Compute Coefficient Corelation r
        double r = numerator/denominator;
        return r;
    }

    // Method to Compute Square of Array of Doubles
    private double[] computeSquare(int nonNullSize, List<Double> a) {
        double[] x = new double[nonNullSize];
        for (int i = 0; i < nonNullSize; i++) {
           x[i] = a.get(i) * a.get(i);
        }
        return x;
    }

}
