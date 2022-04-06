
public class Kwadrat extends Czworokat {
    int obwod;
    int pole;

    public Kwadrat(int bok1){
        obwod = 4*bok1;
        pole = bok1*bok1;
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
