#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <string.h>


class LiczbyPierwsze{
    int* tabPierwsze;
    
    public:
    LiczbyPierwsze(int n){
        
        tabPierwsze = (int*) malloc(n*sizeof(int));
        
        int licznik = 0;
        bool czyPierwsza;

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
    int liczba (int m){
        return tabPierwsze[m];
    }
    
    ~LiczbyPierwsze(){
        free(tabPierwsze);
    }

};

int main (int argc, char *argv[])
{
    try{
        int n = std::stoi(argv[1]);
        
        if( n < 2 ){
            printf("%d -  Nieprawidlowy zakres\n", n );
            return 0;
        }
        
        LiczbyPierwsze pierwsze(n);
        
        for( int i = 2; i < argc; i++){
                try {
                    int k = std::stoi(argv[i]);

                    if( (0<=k) && (k<=n) && (pierwsze.liczba(k)>0) ) {
                        std::cout<< argv[i] << " - " << pierwsze.liczba(k)<<"\n";
                    }
                    else {
                         std::cout<< argv[i] << " - Liczba spoza zakresu\n";
                    }

                }
                catch (std::invalid_argument ex) {
                    std::cout<< argv[i] << " - Nieprawidlowa dana\n";
                }
            }

        
    } 
    catch (std::invalid_argument ex){
         std::cout << argv[1] << " - Nieprawidlowy zakres\n"; 
    }
     

    return 0;
}


