//Test.cpp main

#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <string.h>
#include "headline.hpp"

int main (int argc, char *argv[])
{
   try {
       int n = std::stoi(argv[1]);

       if( n < 0 ) {
           printf("%d - Nieprawidlowy numer wiersza\n", n);
           return 0;
       }

       PascalTriangle pascalTriangle (n);

       int k;

       for(int i = 2; i < argc; i++){
           try{
               k = std::stoi(argv[i]);

               if( (k >= 0) && (k < n+1) ) {
                   printf ("%d - %d\n", k, pascalTriangle.wspolczynnik(k));
               }
               
               else {
                   printf ("%d - Liczba spoza zakresu\n", k);
               }
           }

           catch (std::invalid_argument ex){
               std::cout << argv[i] << " - Nieprawidlowa dana\n";
           }
       }
   }

   catch (std::invalid_argument ex){
       std::cout << argv[1] << " - Nieprawidlowy numer wiersza\n";    
   }

    return 0;

}
// Najwiekszy numer wiersza to taki w którym najwiekszy wspolczynnik ze wzoru bedzie w zakresie do 2,147,483,647
// i bedzie to wiersz 33 gdzie wspolczynniki 16 i 17 sa rowne 1,166,803,110