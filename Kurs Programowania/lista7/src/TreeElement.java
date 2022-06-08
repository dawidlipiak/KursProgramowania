// Klasa elemenu drzewa
class TreeElement<T extends Comparable<T>> {
    T element;
    TreeElement<T> left;
    TreeElement<T> right;

    TreeElement(T element) {
        this.element = element;
        left = null;
        right = null;
    }

    public String toString() {
        return element.toString();
    }
}
