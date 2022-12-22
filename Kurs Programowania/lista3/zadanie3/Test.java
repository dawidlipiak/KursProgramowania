
public class Test {
    public static void main (String args[]){        
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
                        System.out.println("Obwod kola: " + Figury.OneValueFigures.KOLO.obliczObwod(n) + "\nPole kola: " + Figury.OneValueFigures.KOLO.obliczPole(n));
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
                        System.out.println("Obwod pieciokata: " + Figury.OneValueFigures.PIECIOKAT.obliczObwod(n) + "\nPole pieciokata: " + Figury.OneValueFigures.PIECIOKAT.obliczPole(n));
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
                        System.out.println("Obwod szesciokata: " + Figury.OneValueFigures.SZESCIOKAT.obliczObwod(n) + "\nPole szesciokata: " + Figury.OneValueFigures.SZESCIOKAT.obliczPole(n));
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
                        int bok2 = Integer.parseInt(args[licznik]);
                        licznik++;
                        int bok3 = Integer.parseInt(args[licznik]);
                        licznik++;
                        int bok4 = Integer.parseInt(args[licznik]);
                        licznik++;
                        int kat = Integer.parseInt(args[licznik]);
                        licznik++;

                        if(kat > 90){
                            System.out.println(kat + " - Zla wartosc kata dla czworokata");
                            System.exit(0);
                        }
                        
                        if(kat != 90 ){
                            System.out.println("Obwod rombu: " + Figury.TwoValueFigures.ROMB.obliczObwod(bok1,kat) + "\nPole rombu: " + Figury.TwoValueFigures.ROMB.obliczPole(bok1,kat));
                        }
                        
                        else if( (bok1 != bok2) && (bok1 != bok3) && (bok1 != bok4) ){
                            System.out.println("Obwod kwadratu: " + Figury.OneValueFigures.KWADRAT.obliczObwod(bok1) + "\nPole kwadratu: " + Figury.OneValueFigures.KWADRAT.obliczPole(bok1));
                        }
                        else {
                            if(bok1 != bok2)
                                System.out.println("Obwod prostokata: " + Figury.TwoValueFigures.PROSTOKAT.obliczObwod(bok1, bok2) + "\nPole prostokata: " + Figury.TwoValueFigures.PROSTOKAT.obliczPole(bok1, bok2));
                            else if(bok1 != bok3)
                                System.out.println("Obwod prostokata: " + Figury.TwoValueFigures.PROSTOKAT.obliczObwod(bok1, bok3) + "\nPole prostokata: " + Figury.TwoValueFigures.PROSTOKAT.obliczPole(bok1, bok3));
                            else
                                System.out.println("Obwod prostokata: " + Figury.TwoValueFigures.PROSTOKAT.obliczObwod(bok1, bok4) + "\nPole prostokata: " + Figury.TwoValueFigures.PROSTOKAT.obliczPole(bok1, bok4));   
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

    }
}
