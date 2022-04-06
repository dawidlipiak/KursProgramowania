#include <math.h>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include "headline.hpp"


Szesciokat::Szesciokat(int bok){
    obwod = 6*bok;
    pole = (3*bok*bok*sqrt(3))/2;
}

double Szesciokat::szesciokatObwod(){
    return obwod;
}

    
double Szesciokat::szesciokatPole(){
    return pole;
}
