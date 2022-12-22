#include <math.h>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include "headline.hpp"


Pieciokat::Pieciokat(int bok){
    obwod = 5*bok;
    pole = (bok*bok*sqrt(25+10*sqrt(5)))/4;
}

double Pieciokat::pieciokatObwod(){
    return obwod;
}

    
double Pieciokat::pieciokatPole(){
    return pole;
}
