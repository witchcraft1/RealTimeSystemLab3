package labs.lab1;

public class Garmonic1_2 extends Garmonic1_1 {
    public Garmonic1_2(int countOfGarmonics, int limitFrequency, int countOfDescreteCalls) {
        super(countOfGarmonics, limitFrequency, countOfDescreteCalls);
    }

   /* public double calculateCorrelation(){ //TODO Lab1.2
        double correlation = 0;
        double mathExp = calculateMathExpectation();
        for (int tau = 0; tau < getCountOfDescreteCalls()/2-1; tau++){
            for (int t = 0; t < getCountOfDescreteCalls()/2-1; t++){
                correlation += (getSygnalsForResultingGarmonic()[t] - mathExp)*(getSygnalsForResultingGarmonic()[t+tau] - mathExp);
            }
        }
        return correlation/(getCountOfDescreteCalls()-1);
    }*/

    public double[] calculateCorrelation(){ //TODO Lab1.2
       return calculateCorrelationWithOtherFunc(this);
    }

    /*public double[] calculateCorrelation2() {
        double[] correlation_arr = new double[getCountOfDescreteCalls()/2];
        double mathExp = calculateMathExpectation();

        for (int tau = 0; tau < getCountOfDescreteCalls()/2; tau++){
            double correlation = 0;
            for (int t = 0; t < getCountOfDescreteCalls()/2; t++){
                correlation += (getSygnalsForResultingGarmonic()[t] - mathExp)*(getSygnalsForResultingGarmonic()[t+tau] - mathExp);
            }
            correlation_arr[tau] = correlation/(getCountOfDescreteCalls()-1);
        }
        return correlation_arr;
    }*/

    public long calculateExecutionTimeRxx(){
        long startTime = System.nanoTime();
        calculateCorrelation();
        return System.nanoTime() - startTime;
    }
    public long calculateExecutionTimeRxy(Garmonic1_2 harmonic){
        long startTime = System.nanoTime();
        calculateCorrelationWithOtherFunc(harmonic);
        return System.nanoTime() - startTime;
    }


    public double[] calculateCorrelationWithOtherFunc(Garmonic1_2 otherGarmonic){ ////TODO Lab1.2
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
}
