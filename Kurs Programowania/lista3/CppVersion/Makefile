all: figury

figury: figury.o Figura.o Czworokat.o Kolo.o Kwadrat.o Pieciokat.o Prostokat.o Romb.o Szesciokat.o
	g++ figury.o Czworokat.o Figura.o Kolo.o Kwadrat.o Pieciokat.o Prostokat.o Romb.o Szesciokat.o -o figury

figury.o: figury.cpp
	g++ -c figury.cpp

Czworokat.o: Czworokat.cpp
	g++ -c Czworokat.cpp

Kolo.o: Kolo.cpp
	g++ -c Kolo.cpp

Kwadrat.o: Kwadrat.cpp
	g++ -c Kwadrat.cpp
	
Pieciokat.o: Pieciokat.cpp
	g++ -c Pieciokat.cpp
		
Romb.o: Romb.cpp
	g++ -c Romb.cpp

Szesciokat.o: Szesciokat.cpp
	g++ -c Szesciokat.cpp

clean:
	del *.o