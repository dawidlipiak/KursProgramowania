
public class Tree<T extends Comparable<T>> {
    private TreeElement<T> root;

    public Tree() {
        root = null;
    }
    public void insert(T elem) {
        root = ins(elem, root);
    }

    public boolean search(T elem)
    {
        return search(elem,root);
    }

    public String toString() {
        return toS(root);
    }

    private String toS(TreeElement<T> w) {
        if( w!=null )
            return "("+w.element +":"+toS(w.left)+":"+toS(w.right)+")";

        return "()";
    }

    private TreeElement<T> ins (T elem, TreeElement<T> w) {
        if( w==null )
            return new TreeElement<T>(elem);

        if( elem.compareTo(w.element)<0 )
            w.left = ins(elem, w.left);

        else if( elem.compareTo(w.element)>0)
            w.right = ins(elem, w.right);

        return w;
    }

    private boolean search(T element, TreeElement<T> w) {
        if( w==null )
            return false;

        if( element.compareTo(w.element)==0 )
            return true;

        if( element.compareTo(w.element)<0)
            return search(element, w.left);

        else
            return search(element, w.right);
    }

    public Tree<T> draw(){
        return this;
    }

    public void delete(T elem)
    {
        del(elem,root);
    }

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
