package labs.lab2;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.Collection;
import java.util.TreeSet;

public class Lab2_1 {
    public static void main(String[] args) {
        System.out.println("Lab 2.1");

        Garmonic2 garmonic = new Garmonic2(12, 1100,64);
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

        garmonic.getCoefficientsMap(10).forEach((key, value) -> System.out.print(/*"Key: " + key +
                ", value: " + */value + ", "));
        int N = 10;
        System.out.println();
        System.out.println();
        Collection<Double> set = new TreeSet<>();
        boolean[] repeated = new boolean[N];
        for (int p = 0; p < N; p++) {
            for (int k = 0; k < N; k++) {
                if(!repeated[(p*k) % N]) {
                    set.add(2. * Math.PI * p * k / N);
                    repeated[(p * k) % N] = true;
                }
            }
        }
        set.forEach(x->System.out.print(x + ", "));
    }
}
