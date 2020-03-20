package labs.lab1;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;

import java.awt.*;

public class Lab1_2 {
    public static void main(String[] args) {
        System.out.println("Lab 1.2");

        Garmonic1_2 garmonic = new Garmonic1_2(12,1100,64);
        Garmonic1_2 garmonic2 = new Garmonic1_2(12,1100,64);
        double[] i = new double[garmonic2.getCountOfDescreteCalls()];
        for (int i1 = 0; i1 < i.length; i1++) {
            i[i1] = i1;
        }

        // XYChart chart = QuickChart.getChart("x(t)", "t", "x", "x(t)", i , garmonic2.calculateSygnalsForResultingGarmonic());
        XYChart chart = new XYChartBuilder()
                .width(600)
                .height(400)
                .title("x(t)")
                .xAxisTitle("t")
                .yAxisTitle("x")
                .build();

        XYSeries series = chart.addSeries("x(t)", i ,
                garmonic2.calculateSygnalsForResultingGarmonic());
        series.setLineColor(new Color((int)garmonic2.calculateRGBNumberForChart(),
                255 - (int)garmonic2.calculateRGBNumberForChart(),
                (int)garmonic2.calculateRGBNumberForChart()));
        //double[] sss = garmonic2.calculateSygnalsForResultingGarmonic();
      //  chart.addSeries("y(t)", i , garmonic2.calculateSygnalsForResultingGarmonic());


        double[] mathExpectation = new double[garmonic2.getCountOfDescreteCalls()];
        double[] dispersion = new double[garmonic2.getCountOfDescreteCalls()];
        for (int i1 = 0; i1 < i.length; i1++) {
            mathExpectation[i1] = garmonic2.calculateMathExpectation();
            dispersion[i1] = garmonic2.calculateDispersion() + mathExpectation[i1];
            //correlation[i1] = garmonic2.calculateCorrelation();
            //otherCorrelation[i1] = garmonic2.calculateCorrelationWithOtherFunc(garmonic);
        }

        chart.addSeries("MathExpectation", i , mathExpectation);
        chart.addSeries("Dispersion", i, dispersion);

        XYChart chart2 = new XYChartBuilder()
                .width(600)
                .height(400)
                .title("Rxx")
                .xAxisTitle("t")
                .yAxisTitle("Rxx")
                .build();
        garmonic.calculateSygnalsForResultingGarmonic();
        //double[] correlation = new double[garmonic2.getCountOfDescreteCalls()];
        double[] correlation = garmonic.calculateCorrelationWithOtherFunc(garmonic2);
        //double[] otherCorrelation = new double[garmonic2.getCountOfDescreteCalls()];
        double[] otherCorrelation = garmonic.calculateCorrelation();

        double[] i2 = new double[correlation.length];
        for (int k = 0; k < i2.length; k++) {
            i2[k] = k;
        }

        chart2.addSeries("Correlation", i2, correlation);
        chart2.addSeries("Correlation2", i2, otherCorrelation);

        // XYStyler xyStyler = chart.getStyler();
        // xyStyler.setChartBackgroundColor(new Color(250,150,50));
        // Show it
        new SwingWrapper<>(chart).displayChart();
        new SwingWrapper<>(chart2).displayChart();

        int countXX = 0, countXY = 0;

        for (int j = 0; j < 1000; j++) {
            long timeRxx = garmonic.calculateExecutionTimeRxx();
            long timeRxy = garmonic.calculateExecutionTimeRxy(garmonic2);

            if (timeRxx > timeRxy) {
                countXX++;
            } else {
                countXY++;
            }
            System.out.println((timeRxx > timeRxy ? "Rxx більше " : "Rxy більше ") + "Rxx: " + timeRxx +  " Rxy: " + timeRxy);
        }
        System.out.println("Rxx більше по часу: " + countXX + " разів");
        System.out.println("Rxy більше по часу: " + countXY + " разів");
    }
}
