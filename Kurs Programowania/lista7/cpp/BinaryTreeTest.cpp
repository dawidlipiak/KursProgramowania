#include "headline.hpp"
#include<string.h>
using namespace std;

int main(int argc, char *argv[]){
    Tree <string> *tree = new Tree <string>();

    tree -> insert("Maciek");
    tree -> insert("Ala");
    tree -> insert("Kot");
    tree -> insert("Maciek");
    tree -> insert("Zbyszek");

    cout<<"Sprawdzenie czy wystepuje Ala"<<endl;
    cout << tree->search("Ala") << endl;

    cout<<"Sprawdzenie czy wystepuje test"<<endl;
    cout << tree->search("test") << endl;

    tree->draw();
    tree->deleteNode("Kot");
    tree->draw();

    return 0;    
}