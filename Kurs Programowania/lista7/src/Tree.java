/**
 * Tree class
 * @param <T> type of the tree
 */
public class Tree<T extends Comparable<T>> {
    private TreeElement<T> root;

    /**
     * Creating a tree
     */
    public Tree() {
        root = null;
    }

    /**
     * Call out the ins function for inserting the element to the tree
     * @param elem element being inserted to the tree
     */
    public void insert(T elem) {
        root = ins(elem, root);
    }
    /**
     * Function that adds element to the tree
     * @param elem inserted element to the tree
     * @param w node which the element will be placed under
     * @return new tree element added to the tree
     */
    private TreeElement<T> ins (T elem, TreeElement<T> w) {
        if( w==null )
            return new TreeElement<T>(elem);

        if( elem.compareTo(w.element)<0 )
            w.left = ins(elem, w.left);

        else if( elem.compareTo(w.element)>0)
            w.right = ins(elem, w.right);

        return w;
    }

    /**
     * Calling out private search function to find element in the tree
     * @param elem element being searched in the tree
     * @return false or true if the element exists in the tree
     */
    public boolean search(T elem)
    {
        return search(elem,root);
    }

    /**
     * Function that searches for the element in the tree
     * @param element element being searched
     * @param w node which is compared to the searched element
     * @return result of the searching
     */
    private boolean search(T element, TreeElement<T> w) {
        if( w==null )
            return false;

        if( element.compareTo(w.element) == 0 )
            return true;

        if( element.compareTo(w.element)<0)
            return search(element, w.left);

        else
            return search(element, w.right);
    }

    /**
     * Calls out toS function
     * @return function toS
     */
    public String toString() {
        return toS(root);
    }

    /**
     * Function that prints the tree in order
     * @param w node of the tree
     * @return printed tree
     */
    private String toS(TreeElement<T> w) {
        if( w!=null )
            return "("+w.element +":"+toS(w.left)+":"+toS(w.right)+")";

        return "()";
    }

    /**
     * Function that draws the tree
     * @return toString
     */
    public Tree<T> draw(){
        return this;
    }

    /**
     * Function that calls out del to delete an element of the tree
     * @param elem element being deleted
     */
    public void delete(T elem)
    {
        del(elem,root);
    }

    /**
     * Function that deletes element of the tree
     * @param elem possible element to delete
     * @param key node which the deleted element is under
     * @return the node over deleted element
     */
    private TreeElement<T> del (T elem, TreeElement<T> key){

        if (key == null)
            return key;

        if (elem.compareTo(key.element)<0)
            key.left = del(elem,key.left);

        else if (elem.compareTo(key.element)>0)
            key.right = del(elem,key.right);

        else {
            if (key.left == null)
                return key.right;

            else if (key.right == null)
                return key.left;

            key.element = minimal(key.right);
            key.right = del(key.element,key.right);
        }

        return key;
    }

    /**
     * Function that replace deleted element with the part of the tree under that element
     * @param w element which will replace the deleted element
     * @return replaced element
     */
    public T minimal( TreeElement<T> w)
    {
        T val = w.element;

        while(w.left!=null)
        {
            val=w.left.element;
            w=w.left;
        }

        return val;
    }

}
