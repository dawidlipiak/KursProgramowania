#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <string.h>
#include <headline.hpp>


LiczbyPierwsze::LiczbyPierwsze(int n){
        
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
    LiczbyPierwsze::liczba (int m){
        return tabPierwsze[m];
    }
    
    LiczbyPierwsze::~LiczbyPierwsze(){
        free(tabPierwsze);
    }




