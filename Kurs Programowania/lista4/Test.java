

public class Test {
    public static void main (String args[]){
        Obliczenia tab[] = new Figura[args[0].length()]; 
        
        char rodzajFigury;
        int licznik = 1;
        int rightNumberOfValues = 0;
       
        //Sprawdzenie poprawnej ilości danych
        for(int i = 0; i< args[0].length(); i++){
            try {
                rodzajFigury = args[0].charAt(i);
                
                switch (rodzajFigury)
                {
                    case 'p':
                        rightNumberOfValues++;
                        break;
                    case 'o':
                        rightNumberOfValues++;
                        break;
                    case 's':
                        rightNumberOfValues++;
                        break;
                    case 'c':
                        rightNumberOfValues += 5;
                        break;
                    
                    default:
                        System.out.println(rodzajFigury + " - nieprawidlowe oznaczenie figury");
                        System.exit(0);
                }
            }
            catch(NumberFormatException ex){
                System.out.println(args[0].charAt(i) + " - nieprawidlowe oznaczenie figury");
                System.exit(0);
            }  
        }
        
        if( rightNumberOfValues != args.length-1 ){
            System.out.println("Nieprawidlowa ilosc danych wejsciowych");
            System.exit(0);
        }

        for(int i = 0; i < args[0].length(); i++ ){ 
            
            rodzajFigury = args[0].charAt(i);
            
            switch (rodzajFigury) {
                case 'o':
                    try{    
                        int n = Integer.parseInt(args[licznik]);
                        Obliczenia kolo = new Kolo(n);
                        tab[i] = kolo;
                        
                        
                        licznik++;
                        break;
                    }
                    catch(NumberFormatException ex){
                        System.out.println(args[1] + " - nieprawidlowa dana");
                        System.exit(0);
                    }
                case 'p':
                    try{    
                        int n = Integer.parseInt(args[licznik]);
                        Obliczenia pieciokat = new Pieciokat(n);
                        tab[i] = pieciokat;
                        licznik++;
                        break;
                    }
                    catch(NumberFormatException ex){
                        System.out.println(args[1] + " - nieprawidlowa dana");
                        System.exit(0);
                    }
                case 's':
                    try{    
                        int n = Integer.parseInt(args[licznik]);
                        Obliczenia szesciokat = new Szesciokat(n);
                        tab[i] = szesciokat;
                        licznik++;
                        break;
                    }
                    catch(NumberFormatException ex){
                        System.out.println(args[1] + " - nieprawidlowa dana");
                        System.exit(0);
                    }
                case 'c':
                    try{    
                        int bok1 = Integer.parseInt(args[licznik]);
                        licznik++;
                        int bok2 = Integer.parseInt(args[licznik+1]);
                        licznik++;
                        int bok3 = Integer.parseInt(args[licznik+2]);
                        licznik++;
                        int bok4 = Integer.parseInt(args[licznik+3]);
                        licznik++;
                        int kat = Integer.parseInt(args[licznik+4]);
                        licznik++;

                        if(kat > 90){
                            System.out.println(kat + " - Zla wartosc kata dla czworokata");
                            System.exit(0);
                        }
                        
                        if(kat != 90 ){
                            Obliczenia romb = new Romb(bok1, kat);
                            tab[i] = romb;
                        }
                        
                        else if( (bok1 != bok2) && (bok1 != bok3) && (bok1 != bok4) ){
                            Obliczenia kwadrat = new Kwadrat(bok1);
                            tab[i] = kwadrat;
                        }
                        else{
                            Obliczenia prostokat = new Prostokat(bok1, bok2, bok3, bok4);
                            tab[i] = prostokat;
                        }
                        break;
                    }
                    catch(NumberFormatException ex){
                        System.out.println(args[licznik] + " - nieprawidlowa dana");
                        System.exit(0);
                    }
                
                default:
                    System.out.println(rodzajFigury + " - nieprawidlowe oznaczenie figury");
                    System.exit(0);
            }
                
            
        }    
        System.out.println();
        licznik = 1;

        //wypisywanie pól i obwodów figur
        for(int i = 0; i < args[0].length(); i++){
            if(args[0].charAt(i) == 'o'){
                System.out.println("Obwod kola: " + tab[i].obliczObwod() + "\nPole kola: " + tab[i].obliczPole());
                licznik++;
            }
            else if(args[0].charAt(i) == 'p'){
                System.out.println("Obwod pieciokata: " + tab[i].obliczObwod() + "\nPole pieciokata: " + tab[i].obliczPole());
                licznik++;
            } 
            else if(args[0].charAt(i) == 's'){               
                System.out.println("Obwod szesciokata: " +tab[i].obliczObwod() + "\nPole szesciokata: " + tab[i].obliczPole());
                licznik++;
            } 
            else if(args[0].charAt(i) == 'c'){
                if(Integer.parseInt(args[licznik+4]) != 90 ){
                    System.out.println("Obwod rombu: " + tab[i].obliczObwod() + "\nPole rombu: " + tab[i].obliczPole());
                }
                else if( (args[licznik] == args[licznik+1]) && (args[licznik] == args[licznik+2]) && (args[licznik] == args[licznik+3] )  ){
                    System.out.println("Obwod kwadratu: " + tab[i].obliczObwod() + "\nPole kwadratu: " + tab[i].obliczPole());
                }
                else{
                    System.out.println("Obwod prostokata: " + tab[i].obliczObwod() + "\nPole prostokata: " + tab[i].obliczPole());
                }
                licznik = licznik + 5;
            } 
            System.out.println();
        }
    }
    
}
