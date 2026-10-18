
package A01_Stack;

public class Stack<T>
{
    private Node<T> first;

    /**
     * Oberstes Element entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws StackEmptyException
     */
    public T pop() throws StackEmptyException {

        if (first == null) {
            throw new StackEmptyException();
        }

        T data = first.getData();
        first = first.getNext();

        return data;
    }

    /**
     * Übergebenen T auf Stack (als oberstes Element) speichern.
     * @param i data
     */
    public void push(T i) {

        Node<T> newNode = new Node<>(i);
        newNode.setNext(first);
        first = newNode;

    }

    /**
     * Liefert die Anzahl der Elemente im Stack
     * @return
     */
    public int getCount() {

        int count = 0;
        Node<T> current = first;

        while (current != null) {
            count++;
            current = current.getNext();
        }

        return count;
    }
}
// eventuell Lösung mit einem extra counter schon im push und pop
