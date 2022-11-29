
import java.util.ArrayList;

public class Queue<E> {

    private ArrayList<E> fila;

    public Queue() {
        fila = new ArrayList<>();
    }

    public int size() {
        return fila.size();
    }

    public boolean isEmpty() {
        return fila.isEmpty();
    }

    public E head() throws EmptyQueueException {
        if (fila.isEmpty()) {
            throw new EmptyQueueException("A fila esta vazia");
        } else {
            E elem = fila.get(0);
            return elem;
        }
    }

    public void enqueue(E element) {
        fila.add(element);
    }

    public E dequeue() throws EmptyQueueException {
        if (fila.isEmpty()) {
            throw new EmptyQueueException("Queue is empty!");
        } else {
            E elem = fila.remove(0);
            if (elem != null) {
                return elem;
            } else {
                throw new EmptyQueueException("Queue is empty!");
            }
        }
    }

    public boolean reverse() {
        if (fila.isEmpty()) {
            return false;
        } else {
            int totalElementos = fila.size();
            E elem1, elem2;
            for (int i = 0, j = totalElementos - 1; i < totalElementos / 2; i++, j--) {
                elem1 = fila.get(j);
                elem2 = fila.get(i);
                fila.remove(i);
                fila.add(i, elem1);
                fila.remove(j);
                fila.add(j, elem2);
            }
            return true;
        }
    }

    public void clear() {
        fila.clear();
    }
}
