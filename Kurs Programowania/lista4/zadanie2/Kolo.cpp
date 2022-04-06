#include <math.h>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include "headline.hpp"


Kolo::Kolo(int promien){
        obwod = 2*M_PI*promien;
        pole = M_PI*promien*promien;
}
    
double Kolo::koloObwod(){
    return obwod;
}
    
double Kolo:: koloPole(){
    return pole;
}

