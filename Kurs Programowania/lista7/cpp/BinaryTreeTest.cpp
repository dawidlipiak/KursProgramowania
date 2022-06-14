#include "headline.hpp"
#include<string.h>
using namespace std;

int main(int argc, char *argv[]){
    
    Tree<string> * const tree = new Tree<string>();

    string element;
    string action;

    while(action != "exit"){
        cout<< "\nPodaj akcje ktora chcesz wykonac: \n";
        cout<< "| 1. exit | 2. draw | 3. insert ... | 4. search ... | 5. delete |\n";
        cin >> action; 
        
        if(action == "draw"){
            tree -> draw();
        }
        else if(action == "insert"){
            cin >> element;
            //cout<<"ins test " << element << endl;
            tree -> insert(element);
        }
        else if(action == "search"){
            cin >> element;
            cout<< tree -> search(element) << endl;
        }
        else if(action == "delete"){
            cin >> element;
                tree -> deleteNode(element);
        }
    }

    return 0;    
}

