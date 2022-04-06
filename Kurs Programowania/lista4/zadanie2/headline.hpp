//headline.hpp 
#include <math.h>

class Figura{
    public:
        double obwod;
        double pole;
};

class Kolo : public Figura{
    public:
        Kolo(int promien);
        double koloObwod();
        double koloPole();

};

class Pieciokat : public Figura{
    public:
        Pieciokat(int bok);
        double pieciokatObwod();
        double pieciokatPole();
};

class Szesciokat : public Figura{
    public:
        Szesciokat(int bok);
        double szesciokatObwod();
        double szesciokatPole();
};

class Czworokat : public Figura {};

class Kwadrat : public Czworokat{
    public:
        Kwadrat(int bok1);
        double kwadratObwod();
        double kwadratPole();
};

class Romb : public Czworokat {
    public:
    Romb(int bok1, int kat);
    double rombObwod();
    double rombPole();
};

class Prostokat : public Czworokat {
    public:
        Prostokat(int bok1, int bok2, int  bok3, int bok4);
        double prostokatObwod();
        double prostokatPole();
};