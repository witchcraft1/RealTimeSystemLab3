package labs.allLabsInOneClass;

import labs.lab2.Garmonic2;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.util.Collection;
import java.util.TreeSet;

public class Main {
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
