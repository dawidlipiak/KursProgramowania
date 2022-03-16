import java.util.*;


public class Test{
    public static void main (String[] args){
        try {
            int n = Integer.parseInt(args[0]);
            if( n < 2){
                System.out.println(args[0] + " - Nieprawidlowy zakres");
                System.exit(0);
            }

            LiczbyPierwsze pierwsze = new LiczbyPierwsze(n);

            for( int i = 1; i < args.length; i++){
                try {
                    int k = Integer.parseInt(args[i]);

                    if( (0<=k) && (k<=n) && (pierwsze.liczba(k)>0) ) {
                        System.out.println(args[i] + " - " + pierwsze.liczba(k));
                    }
                    else {
                        System.out.println(args[i] + " - Liczba spoza zakresu");
                    }

                }
                catch (NumberFormatException ex) {
                    System.out.println(args[i] + " - Nieprawidlowa dana");
                }
            }

        }
        catch (NumberFormatException ex) {
            System.out.println(args[0] + " - Nieprawidlowy zakres");
        }

    }
}