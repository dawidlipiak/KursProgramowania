import java.util.*;

public class LiczbyPierwsze {

    int [] tabPierwsze;

    public LiczbyPierwsze(int n) {

        boolean czyPierwsza;
        tabPierwsze = new int [n];

        int licznik = 0;

        for(int i = 2; i <= n; i++){

            czyPierwsza = true;

            for(int j = 2; j<i ; j++) {
                 if (i%j == 0) {
                    czyPierwsza = false;
                    break;
                }
            }

            if(czyPierwsza == true){
                tabPierwsze[licznik] = i;
                licznik++;
            }
        }
    }

    public int liczba (int m){
        return tabPierwsze[m];
    }
}
