#include <math.h>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include "headline.hpp"

Romb::Romb(int bok1, int kat){
    double angleInRadians = (kat*M_PI)/180;

    obwod = 4*bok1;
    pole = (sin(angleInRadians)) * bok1*bok1; 
}

double Romb::rombObwod(){
    return obwod;
}
double Romb::rombPole(){
    return pole;
}
