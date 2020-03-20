package labs.lab1;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;

import java.awt.*;

public class Lab1_1 {
    public static void main(String[] args) {
        Garmonic1_1 garmonic = new Garmonic1_1(12,1100,64);
        double[] i = new double[garmonic.getCountOfDescreteCalls()];
        for (int i1 = 0; i1 < i.length; i1++) {
            i[i1] = i1;
        }

       // XYChart chart = QuickChart.getChart("x(t)", "t", "x", "x(t)", i , garmonic.calculateSygnalsForResultingGarmonic());
        XYChart chart = new XYChartBuilder()
                .width(600)
                .height(400)
                .title("x(t)")
                .xAxisTitle("t")
                .yAxisTitle("x")
                .build();
        XYSeries series = chart.addSeries("x(t)", i , garmonic.calculateSygnalsForResultingGarmonic());
        series.setLineColor(new Color((int)garmonic.calculateRGBNumberForChart(), 255 - (int)garmonic.calculateRGBNumberForChart(),
                (int)garmonic.calculateRGBNumberForChart()));

        double[] mathExpectation = new double[garmonic.getCountOfDescreteCalls()];
        double[] dispersion = new double[garmonic.getCountOfDescreteCalls()];
        for (int i1 = 0; i1 < i.length; i1++) {
            mathExpectation[i1] = garmonic.calculateMathExpectation();
            dispersion[i1] = garmonic.calculateDispersion() + mathExpectation[i1];
        }

        System.out.println(garmonic.calculateRGBNumberForChart());

        chart.addSeries("MathExpectation", i , mathExpectation);
        chart.addSeries("Dispersion", i, dispersion);

       // XYStyler xyStyler = chart.getStyler();
       // xyStyler.setChartBackgroundColor(new Color(250,150,50));
        // Show it
        new SwingWrapper(chart).displayChart();
    }
}
