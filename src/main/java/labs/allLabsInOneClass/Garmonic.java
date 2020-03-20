package labs.allLabsInOneClass;

import labs.lab1.Garmonic1_2;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Garmonic {
    private int countOfGarmonics;
    private int limitFrequency;
    private int countOfDescreteCalls;
    private double[][] sygnalsForAllGarmonics;
    private double[] sygnalsForResultingGarmonic;

    public Garmonic(int countOfGarmonics, int limitFrequency, int countOfDescreteCalls) {
        this.countOfGarmonics = countOfGarmonics;
        this.limitFrequency = limitFrequency;
        this.countOfDescreteCalls = countOfDescreteCalls;
        this.sygnalsForAllGarmonics = new double[countOfGarmonics][countOfDescreteCalls];
        this.sygnalsForResultingGarmonic = new double[countOfDescreteCalls];
    }
    public Garmonic(){}


    //Перша лабораторна робота

    /**
     *
     * @return числове значення в діапазоні 0-255 для зміни кольору графіка
     */
    public double calculateRGBNumberForChart(){
        double sum = 0;
        final double rgbLimit = 255;
        for (double v : getSygnalsForResultingGarmonic()) {
            sum+=v;
        }
        return sum * (rgbLimit/(1.*getCountOfDescreteCalls()/1));
    }

    /**
     * даний метод був суто тренувальним, в коді далі він не використовується
     * @return двовимірний масив сигналів для кожної гармоніки окремо
     */
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

    /**
     *
     * @return масив сигналів (числових значень) результуючої гармоніки
     */
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

    /**
     *
     * @return значення математичного очікування
     */
    public double calculateMathExpectation(){
        double sum = 0 ;
        for (double sygnal : getSygnalsForResultingGarmonic()) {
            sum+=sygnal;
        }
        return sum/getSygnalsForResultingGarmonic().length;
    }

    /**
     *
     * @return значення дисперсії
     */
    public double calculateDispersion(){
        double sum = 0;
        double mathExpectation = calculateMathExpectation();
        for (double sygnal : getSygnalsForResultingGarmonic()) {
            sum += Math.pow(sygnal - mathExpectation, 2);
        }
        return sum/(getSygnalsForResultingGarmonic().length - 1);
    }


    //2 лабораторна (1.2)

    public double[] calculateCorrelationWithOtherFunc(Garmonic otherGarmonic){ ////TODO Lab1.2
        double[] correlation_arr = new double[getCountOfDescreteCalls()/2];
        double mathExp = calculateMathExpectation();
        double mathExp2 = otherGarmonic.calculateMathExpectation();

        for (int tau = 0; tau < getCountOfDescreteCalls()/2; tau++){
            double correlation = 0;
            for (int t = 0; t < getCountOfDescreteCalls()/2; t++){
                correlation += (getSygnalsForResultingGarmonic()[t] - mathExp)*(otherGarmonic.getSygnalsForResultingGarmonic()[t+tau] - mathExp2);
            }
            correlation_arr[tau] = correlation/(getCountOfDescreteCalls()-1);
        }
        return correlation_arr;
    }

    public double[] calculateCorrelation(){ //TODO Lab1.2
        return calculateCorrelationWithOtherFunc(this);
    }

    public long calculateExecutionTimeRxx(){
        long startTime = System.nanoTime();
        calculateCorrelation();
        return System.nanoTime() - startTime;
    }
    public long calculateExecutionTimeRxy(Garmonic harmonic){
        long startTime = System.nanoTime();
        calculateCorrelationWithOtherFunc(harmonic);
        return System.nanoTime() - startTime;
    }

    //3 лаба (2.1)
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

    /**
     * перевантажена функція, створена для того, щоб зручно викликати її від імені об*єкта гармоніки, для якого
     * одразу ж обрахується результат (замість того, щоб в calculateDFT(double[] arr) ще додатково передавати
     * масив сигналів
     * @return
     */
    public double[] calculateDFT(){
        return calculateDFT(getSygnalsForResultingGarmonic());
    }

    private double WReal(int p, int k, int N){
        return Math.cos(2*Math.PI*p*k/N);
    }

    private double WImage(int p, int k, int N){
        return Math.sin(2*Math.PI*p*k/N);
    }

    //4 лаба


    public int getCountOfGarmonics() {
        return countOfGarmonics;
    }

    public void setCountOfGarmonics(int countOfGarmonics) {
        this.countOfGarmonics = countOfGarmonics;
    }

    public int getLimitFrequency() {
        return limitFrequency;
    }

    public void setLimitFrequency(int limitFrequency) {
        this.limitFrequency = limitFrequency;
    }

    public int getCountOfDescreteCalls() {
        return countOfDescreteCalls;
    }

    public void setCountOfDescreteCalls(int countOfDescreteCalls) {
        this.countOfDescreteCalls = countOfDescreteCalls;
    }

    public double[][] getSygnalsForAllGarmonics() {
        return sygnalsForAllGarmonics;
    }

    public void setSygnalsForAllGarmonics(double[][] sygnalsForAllGarmonics) {
        this.sygnalsForAllGarmonics = sygnalsForAllGarmonics;
    }

    public double[] getSygnalsForResultingGarmonic() {
        return sygnalsForResultingGarmonic;
    }

    public void setSygnalsForResultingGarmonic(double[] sygnalsForResultingGarmonic) {
        this.sygnalsForResultingGarmonic = sygnalsForResultingGarmonic;
    }

}

class Main{
    public static void main(String[] args) {
        System.out.println("Lab 2.1");

        Garmonic garmonic = new Garmonic(12, 1100,64);
        double[] i = new double[garmonic.getCountOfDescreteCalls()];
        for (int i1 = 0; i1 < i.length; i1++) {
            i[i1] = i1;
        }
        XYChart chart = new XYChartBuilder()
                .width(600)
                .height(400)
                .title("x(t)")
                .xAxisTitle("t")
                .yAxisTitle("x")
                .build();


        garmonic.calculateSygnalsForResultingGarmonic();

        chart.addSeries("Fourier Function", i, garmonic.calculateDFT());

        new SwingWrapper<>(chart).displayChart();

        int N = garmonic.getCountOfDescreteCalls();
        garmonic.getCoefficientsMap(N).forEach((key, value) -> System.out.print(/*"Key: " + key +
                ", value: " + */value + ", "));
    }
}
