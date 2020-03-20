package labs.lab2;

import labs.lab1.Garmonic1_2;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Garmonic2 extends Garmonic1_2 {

    public Garmonic2(int countOfGarmonics, int limitFrequency, int countOfDescreteCalls) {
        super(countOfGarmonics, limitFrequency, countOfDescreteCalls);
    }

    public double[] calculateDFT(double[] sygnalsOfResultingGarmonic){//TODO Lab2.1

        int N = sygnalsOfResultingGarmonic.length;

        Map<Integer, Double> coefficients = getCoefficientsMap(N);

        double[] dft_real = new double[N];
        double[] dft_image = new double[N];
        double[] dft_final = new double[N];

        for (int p = 0; p < N; p++) {
            for (int k = 0; k < N; k++) {
                dft_real[p] += sygnalsOfResultingGarmonic[k] * Math.cos(coefficients.get((p*k) % N));
                dft_image[p] += sygnalsOfResultingGarmonic[k] * Math.sin(coefficients.get((p*k) % N));  //Math.sin(Wn[p][k]);
            }
            dft_final[p] = Math.sqrt(Math.pow(dft_real[p],2) +
                    Math.pow(dft_image[p],2));
        }
        return dft_final;
    }

    public Map<Integer,Double> getCoefficientsMap(int N){
        Map<Integer, Double> coefficients = new HashMap<>();
        for (int p = 0; p < N; p++) {
            for (int k = 0; k < N; k++) {
                coefficients.putIfAbsent((p*k) % N, 2 * Math.PI * p * k / N);
            }
        }
        return  coefficients;
    }

    public double[] calculateDFT(){
        return calculateDFT(getSygnalsForResultingGarmonic());
    }

    private double WReal(int p, int k, int N){
        return Math.cos(2*Math.PI*p*k/N);
    }

    private double WImage(int p, int k, int N){
        return Math.sin(2*Math.PI*p*k/N);
    }
}
