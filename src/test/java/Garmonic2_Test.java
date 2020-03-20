import labs.lab2.Garmonic2;
import org.junit.*;

import java.util.*;

public class Garmonic2_Test {
    Garmonic2 garmonic,garmonic2;
    @Before
    public void initialize(){
        garmonic = new Garmonic2(9,1000,64);
        garmonic.calculateSygnalsForResultingGarmonic();
        garmonic2 = new Garmonic2(12,1100,4);
        garmonic2.calculateSygnalsForResultingGarmonic();
    }

    @Ignore
    @Test
    public void calculateDFT(){
        Assert.assertArrayEquals(garmonic.calculateDFT(), garmonic.calculateDFT(garmonic2.getSygnalsForResultingGarmonic()), 0.001);
    }

    @Test
    public void getMap(){
        int N = garmonic2.getCountOfDescreteCalls();
        Collection<Double> originalSet = garmonic2.getCoefficientsMap(N).values();

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


        Iterator<Double> it1 = originalSet.iterator(),
                it2 = set.iterator();
        while(it1.hasNext() && it2.hasNext()){
            Assert.assertEquals(it1.next(), it2.next(), 0.0000001);
        }

    }
}
