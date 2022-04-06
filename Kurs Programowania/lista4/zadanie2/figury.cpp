//main.cpp lista4 zadanie 4
#include <math.h>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "headline.hpp"

int main (int argc, char* argv[])
{  
    char rodzajFigury;
    int licznik = 2;
    int rightNumberOfValues = 0;
    
    //Sprawdzenie poprawnej ilości danych
    for(int i = 0; i< strlen(argv[1]); i++){
        try {
            rodzajFigury = argv[1][i];

            
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
                    std::cout<<rodzajFigury << " - nieprawidlowe oznaczenie figury\n";
            }
        }
        catch(std::invalid_argument ex){
            std::cout << argv[1][i] << " - nieprawidlowe oznaczenie figury\n";
        }  
    }
    
    if( rightNumberOfValues != argc -2 ){
        std:: cout<<"Nieprawidlowa ilosc danych wejsciowych";
        return 0;
    }

    Figura*t [rightNumberOfValues];

    for(int i = 0; i < strlen(argv[1]); i++ ){ 
        
        rodzajFigury = argv[1][i];
        
        switch (rodzajFigury) {
            case 'o':
                try{    
                    int n = std::stoi(argv[licznik]);
                    Kolo *kolo = new Kolo(n);
                    t[i] = kolo;
                    licznik++;
                    break;
                }
                catch(std::invalid_argument ex){
                    std::cout<< argv[licznik] << " - nieprawidlowa dana";
                    return 0;
                    
                }
            case 'p':
                try{    
                    int n = std::stoi(argv[licznik]);
                    Pieciokat *pieciokat = new Pieciokat(n);
                    t[i] = pieciokat;              
                    licznik++;
                    break;
                }
                catch(std::invalid_argument ex){
                    std::cout<< argv[licznik] << " - nieprawidlowa dana";
                    return 0;
                    
                }
            case 's':
                try{    
                    int n = std::stoi(argv[licznik]);
                    Szesciokat *szesciokat = new Szesciokat(n);
                    t[i] = szesciokat;              
                    licznik++;
                    break;
                }
                catch(std::invalid_argument ex){
                    std::cout<< argv[licznik] << " - nieprawidlowa dana";
                    return 0;
                    
                }
            case 'c':
                try{    
                    int bok1 = std::stoi(argv[licznik]);
                    int bok2 = std::stoi(argv[licznik+1]);
                    int bok3 = std::stoi(argv[licznik+2]);
                    int bok4 = std::stoi(argv[licznik+3]);
                    int kat = std::stoi(argv[licznik+4]);

                    if(kat > 90){
                        std::cout<< kat << " - Zla wartosc kata dla czworokata";
                        return 0;
                    }
                    
                    if(kat != 90 ){
                        Romb *romb = new Romb(bok1, kat);
                        t[i] = romb;
                    }
                    
                    else if( (bok1 != bok2) && (bok1 != bok3) && (bok1 != bok4) ){
                        Kwadrat *kwadrat = new Kwadrat(bok1);
                        t[i] = kwadrat;
                    }
                    else{
                        Prostokat *prostokat = new Prostokat(bok1, bok2, bok3, bok4);
                        t[i] = prostokat;                        
                    }
                    
                    licznik = licznik + 5;
                    break;
                }
                catch(std::invalid_argument ex){
                    std::cout<< " - nieprawidlowa dana";
                    return 0;
                    
                }
            
            default:
                std::cout<< rodzajFigury << " - nieprawidlowe oznaczenie figury";
                return 0;
                
        }
            
        
    }    
    printf("\n");
    licznik = 2;

    //wypisywanie pól i obwodów figur
    for(int i = 0; i < strlen(argv[1]); i++){
        if(argv[1][i] == 'o'){
            std::cout<< "Obwod kola: " << t[i]->obwod << "\nPole kola: " << t[i]->pole << '\n';
            licznik++;
        }
        else if(argv[1][i] == 'p'){
            std::cout<< "Obwod pieciokata: " << t[i]->obwod << "\nPole pieciokata: " << t[i]->pole << '\n';
            licznik++;
        } 
        else if(argv[1][i] == 's'){               
            std::cout<< "Obwod szesciokata: " << t[i]->obwod << "\nPole szesciokata: " << t[i]->pole << '\n';
            licznik++;
        } 
        else if(argv[1][i] == 'c'){
            if(std::stoi(argv[licznik+4]) != 90 ){
                std::cout<< "Obwod rombu: " << t[i]->obwod << "\nPole rombu: " << t[i]->pole << '\n';
            }
            else if( (argv[licznik] == argv[licznik+1]) && (argv[licznik] == argv[licznik+2]) && (argv[licznik] == argv[licznik+3] )  ){
                std::cout<< "Obwod kwadratu: " << t[i]->obwod << "\nPole kwadratu: " << t[i]->pole << '\n';
            }
            else{
                std::cout<< "Obwod prostokata: " << t[i]->obwod << "\nPole prostokata: " << t[i]->pole << '\n';
            }  
            licznik += 5;  
        }
    }
    delete *t;
    
    return 0;    
}
    