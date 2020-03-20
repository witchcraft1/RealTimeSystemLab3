package labs.lab1;

import labs.Garmonic;

import java.util.Random;

public class Garmonic1_1 extends Garmonic {
    public Garmonic1_1(int countOfGarmonics, int limitFrequency, int countOfDescreteCalls) {
        super(countOfGarmonics, limitFrequency, countOfDescreteCalls);
    }

    public double calculateRGBNumberForChart(){
        double sum = 0;
        final double rgbLimit = 255;
        for (double v : getSygnalsForResultingGarmonic()) {
            sum+=v;
        }
        return sum * (rgbLimit/(1.*getCountOfDescreteCalls()/1));
    }
    public double[][] calculateSygnalsForAllGarmonics(){
        Random r = new Random();
        double A = r.nextDouble();
        double fi = r.nextDouble()*Math.PI;
        for (int i = 0; i < getCountOfGarmonics(); i++) {
            for (int j = 0; j < getCountOfDescreteCalls(); j++) {
                getSygnalsForAllGarmonics()[i][j] = A*Math.sin(1.0*getLimitFrequency()*(i+1)/getCountOfGarmonics()*j + fi);
            }
        }
        return getSygnalsForAllGarmonics();
    }

    public double[] calculateSygnalsForResultingGarmonic(){
        Random r = new Random();
        double A = r.nextDouble();
        double fi = r.nextDouble()*Math.PI;
//        System.out.println("A: "+A);

        for (int i = 0; i < getCountOfGarmonics(); i++) {
            for (int j = 0; j < getCountOfDescreteCalls(); j++) {
                getSygnalsForResultingGarmonic()[j] += A * Math.sin(1.*getLimitFrequency()*(i+1)/getCountOfGarmonics()*j + fi) ;//TODO delete A
            }
        }
       /* System.out.println();
        for (double v : getSygnalsForResultingGarmonic()) {
            System.out.print(v+" ");
        }
        System.out.println();*/
        return getSygnalsForResultingGarmonic();
    }

    public double calculateMathExpectation(){
        double sum = 0 ;
        for (double sygnal : getSygnalsForResultingGarmonic()) {
            sum+=sygnal;
        }
        return sum/getSygnalsForResultingGarmonic().length;
    }

    public double calculateDispersion(){
        double sum = 0;
        double mathExpectation = calculateMathExpectation();
        for (double sygnal : getSygnalsForResultingGarmonic()) {
            sum += Math.pow(sygnal - mathExpectation, 2);
        }
        return sum/(getSygnalsForResultingGarmonic().length - 1);
    }
}
