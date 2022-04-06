import java.lang.Math;

public class Romb extends Czworokat {
    int obwod;
    double pole;

    public Romb(int bok1, int kat){
        double angleInRadians = (kat*Math.PI)/180;

        obwod = 4*bok1;
        pole = (Math.sin(angleInRadians)) * bok1*bok1; 
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
