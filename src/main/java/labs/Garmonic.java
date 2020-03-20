package labs;

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
