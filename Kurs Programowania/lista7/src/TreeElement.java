/**
 * Tree element class
 * @param <T> type of the tree
 */
public class TreeElement<T extends Comparable<T>> {
    T element;
    TreeElement<T> left;
    TreeElement<T> right;

    /**
     * Creating a base of the tree or a subtree
     * @param element root of the tree
     */
    TreeElement(T element) {
        this.element = element;
        left = null;
        right = null;
    }

    /**
     * Function converting element to string type
     * @return element converted to string type
     */
    public String toString() {
        return element.toString();
    }
}
