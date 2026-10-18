
package A01a_Queue;

public class Queue<T>
{
    private Node<T> first;

    private Node<T> last;

    /**
     * Das vorderste (=erste) Element aus der Queue entfernen und zurückliefern.
     * Existiert kein Element, wird eine Exception ausgelöst.
     * @throws QueueEmptyException
     */
    public T dequeue() throws QueueEmptyException {

        if (first == null) {
            throw new QueueEmptyException();
        }

        T data = first.getData();
        first = first.getNext();

        if (first == null) {
            last = null;
        }

        return data;
    }

    /**
     * Übergebenen Integer am Ende der Queue anhängen.
     * @param i Zahl
     */
    public void enqueue(T i) {

        Node<T> newNode = new Node<>(i);

        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            last = newNode;
        }
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
