//import java.util.*;


public class Test{
    public static void main (String[] args){

        try {
            int n = Integer.parseInt(args[0]);

            if (n < 0) {
                System.out.println(args[0] + " - Nieprawidlowy numer wiersza");
                System.exit(0);
            }

            WierszTrojkataPascala wtp = new WierszTrojkataPascala(n);


            int k;

            for (int i = 1; i < args.length; i++) {
                try {
                    k = Integer.parseInt(args[i]);

                    if ((k >= 0) && (k < n+1)) {
                        System.out.println(k + " - " + wtp.wspolczynnik(k));
                    } else {
                        System.out.println(k + " - liczba spoza zakresu");
                    }
                }

                catch(NumberFormatException ex){
                    System.out.println(args[i] + " - Nieprawidlowa dana");
                }
            }
        }

        catch (NumberFormatException ex){
                System.out.println(args[0] + " - Nieprawidlowy numer wiersza");
        }
    }
}

// Najwiekszy numer wiersza to taki w którym najwiekszy wspolczynnik ze wzoru bedzie w zakresie do 2,147,483,647
// i bedzie to wiersz 33 gdzie wspolczynniki 16 i 17 sa rowne 1,166,803,110