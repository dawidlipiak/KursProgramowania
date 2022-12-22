//PascalTriangle.cpp 
#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <string.h>
#include "headline.hpp"


PascalTriangle::PascalTriangle(int n){
    
    tab = new int[n+1];

    if( n == 0 ){
        tab[0] = 1;
    }

    else {
        tab[0] = 1;
        tab[1] = 1;

        tabPrevious = new int[n + 1];
        tabPrevious[0] = 1;
        tabPrevious[1] = 1;

        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j <= i; j++) {
                if (j != i) {
                    tab[j] = tabPrevious[j - 1] + tabPrevious[j];
                } else {
                    tab[j] = tabPrevious[j] = 1;

                }
                tabPrevious[j - 1] = tab[j - 1];
            }
        }
    }
}

int PascalTriangle::wspolczynnik(int m){
    return tab[m];
}

PascalTriangle::~PascalTriangle(){
        delete [] tab;
        delete [] tabPrevious;
    }



