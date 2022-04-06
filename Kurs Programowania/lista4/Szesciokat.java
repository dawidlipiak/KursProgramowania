
public class Szesciokat extends Figura {
    int obwod;
    double pole;

    public Szesciokat(int bok){
        obwod = 6*bok;
        pole = (3*bok*bok*Math.sqrt(3))/2;
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