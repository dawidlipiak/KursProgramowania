

public class Pieciokat extends Figura{
    int obwod;
    double pole;
    
    public Pieciokat(int bok){
        obwod = 5*bok;
        pole = (bok*bok*Math.sqrt(25+10*Math.sqrt(5)))/4;
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