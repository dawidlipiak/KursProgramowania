#include <math.h>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include "headline.hpp"

Prostokat::Prostokat(int bok1, int bok2, int  bok3, int bok4){
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


double Prostokat::prostokatObwod() {
    return obwod;
}
double Prostokat::prostokatPole(){
    return pole;
}
