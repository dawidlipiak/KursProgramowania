import java.lang.Math;

public class Kolo extends Figura  {
    double obwod;
    double pole;

    Kolo(int promien){
        obwod = 2*Math.PI*promien;
        pole = Math.PI*promien*promien;
    }
    
    @Override
    public double obliczObwod() {
        return obwod;
    }

    @Override
    public double obliczPole() {
        return pole;
    }

}