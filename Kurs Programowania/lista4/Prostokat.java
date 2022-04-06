public class Prostokat extends Czworokat {
    int obwod;
    int pole;

    public Prostokat(int bok1, int bok2, int  bok3, int bok4){
        obwod = bok1 + bok2 + bok3 + bok4;

        if( bok1 != bok2 ){
            pole = bok1*bok2;
        }
        else if(bok1 != bok3){
            pole = bok1*bok3;
        }
        else {
            pole = bok1*bok4;
        }
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
