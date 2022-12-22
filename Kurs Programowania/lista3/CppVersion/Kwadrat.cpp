#include <math.h>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include "headline.hpp"

Kwadrat::Kwadrat(int bok1){
    obwod = 4*bok1;
    pole = bok1*bok1;
}

double Kwadrat::kwadratObwod() {
    return obwod;
}
double Kwadrat::kwadratPole(){
    return pole;
}
