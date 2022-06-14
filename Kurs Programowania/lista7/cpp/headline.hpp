//headline.hpp
#include <stdio.h>
#include <iostream>
#include <stdlib.h>
#include <string.h>
//#define else  

template <typename T>
class TreeElement
{
    public:
        T element;

        TreeElement<T> *left;
        TreeElement<T> *right;
        
        TreeElement(T element) {
            this -> element = element;
            left = NULL;
            right = NULL;
        }

        std::string draw (){
            return element.toString();
        }

        ~TreeElement(){
            delete left;
            delete right;
            delete this;
        }
    
};

template <typename T>
class Tree {
    private:
        TreeElement<T> *root;

        TreeElement<T> *ins (T elem, TreeElement<T> *w) {
            if( w == NULL )
                return new TreeElement<T>(elem);
            if(compareTo(elem, w->element)<0) {
                w -> left = ins(elem, w -> left);
            }
            else if(compareTo(elem, w->element)>0) {
                w -> right = ins(elem, w -> right);
            }
            return w;
        }

        bool search (T elem, TreeElement<T> *w) {
            if( w == NULL )
                return false;

            if( compareTo(elem, w -> element) == 0 )
                return true;

            if( compareTo(elem, w -> element) < 0)
                return search(elem, w -> left);

            else
                return search(elem, w -> right);
        }

        std::string toS(TreeElement<T> *w ) { 
            if( w != NULL )
                return "(" + w-> element + ":" + toS(w->left) + ":" + toS(w->right) + ")";
            
            return "()";
        }

        TreeElement<T> *del (T elem, TreeElement<T> *key){

            if (key == NULL)
                return key;

            if (compareTo(elem,key->element) < 0)
                key -> left = del(elem,key->left);

            else if (compareTo(elem,key->element)>0)
                key->right = del(elem,key->right);

            else {
                if (key->left == NULL)
                    return key -> right;

                else if (key->right == NULL)
                    return key->left;

                key->element = minimal(key->right);
                key->right = del(key->element,key->right);
            }
            return key;
        }

        int compareTo(T val1, T val2) {
                if (val1 > val2) 
                    return 1;
                else if (val1 == val2)
                    return 0;
                return -1;
        }
         
    public:
        Tree(){
            root=NULL;
        }
        ~Tree(){
            delete root;
        }

        T minimal( TreeElement<T> *w) {
            T minValue = w->element;

            while(w->left != NULL)
            {
                minValue = w->left->element;
                w = w->left;
            }
            return minValue;
        }

        void insert(T elem) {
            root = ins(elem, root);
        }

        bool search(T elem) {
            return search(elem,root);
        }

        std::string toString() {
            return toS(root);
        }

        void draw(){
            std::cout << this->toS(root) << std::endl << std::endl; 
        }

        void deleteNode(T elem)
        {
            del(elem,root);
        }
};
