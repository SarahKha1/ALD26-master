package A02_Heap;

import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;
import java.util.Random;

public class HeapArrayListTest {

	@Test
	public void emptyHeap() {
		TaskHeapArrayList th = new TaskHeapArrayList();
		assertNull(th.remove());
	}

	@Test
	public void removeTest() {
		TaskHeapArrayList th = new TaskHeapArrayList();
		th.insert(new Task(1, 12));
		th.insert(new Task(2, 2));
		th.insert(new Task(3, 14));
		th.insert(new Task(4, 3));
		th.insert(new Task(5, 5));
		th.insert(new Task(6, 6));
		assertEquals(2, th.remove().getId());
		assertEquals(4, th.remove().getId());
		assertEquals(5, th.remove().getId());
		assertEquals(6, th.remove().getId());
		assertEquals(1, th.remove().getId());
		assertEquals(3, th.remove().getId());
		assertNull(th.remove());
	}
	
	@Test
	public void removeTest2() {
		TaskHeapArrayList th = new TaskHeapArrayList();
		th.insert(new Task(1, 7));
		th.insert(new Task(2, 2));
		th.insert(new Task(3, 14));
		assertEquals(2, th.remove().getId());
		th.insert(new Task(4, 9));
		th.insert(new Task(5, 1));
		assertEquals(5, th.remove().getId());
		th.insert(new Task(6, 8));
		assertEquals(1, th.remove().getId());
		assertEquals(6, th.remove().getId());
		assertEquals(4, th.remove().getId());
		assertEquals(3, th.remove().getId());
		assertNull(th.remove());
	}


    @Test
    public void laufzeitMessungE2() {

        int[] groessen = {1000, 5000, 10000, 20000, 40000};

        System.out.println("Java: " + System.getProperty("java.version"));

        for (int n : groessen) {

            Task[] daten = new Task[n];
            Random random = new Random(42);

            for (int i = 0; i < n; i++) {
                daten[i] = new Task(i, random.nextInt(1000000));
            }

            long heapZeit = 0;
            long listenZeit = 0;

            // Ein Warm-up und 5 Messungen
            for (int r = 0; r < 6; r++) {

                TaskHeapArrayList heap = new TaskHeapArrayList();

                long start = System.nanoTime();

                for (Task t : daten) {
                    heap.insert(t);
                }

                for (int i = 0; i < n; i++) {
                    heap.remove();
                }

                long zeitHeap = System.nanoTime() - start;

                ArrayList<Task> liste = new ArrayList<>();

                start = System.nanoTime();

                for (Task t : daten) {

                    // Richtige Einfügeposition suchen
                    int links = 0;
                    int rechts = liste.size();

                    while (links < rechts) {
                        int mitte = (links + rechts) / 2;

                        if (liste.get(mitte).getPriority()
                                > t.getPriority()) {
                            links = mitte + 1;
                        } else {
                            rechts = mitte;
                        }
                    }

                    liste.add(links, t);
                }

                // Kleinste Priorität zuerst entfernen
                for (int i = 0; i < n; i++) {
                    liste.remove(liste.size() - 1);
                }

                long zeitListe = System.nanoTime() - start;

                // Ersten Durchlauf nicht mitzählen
                if (r > 0) {
                    heapZeit += zeitHeap;
                    listenZeit += zeitListe;
                }
            }

            System.out.println("Tasks: " + n);
            System.out.println("Heap gesamt: "
                    + heapZeit / 5000000.0 + " ms");
            System.out.println("ArrayList gesamt: "
                    + listenZeit / 5000000.0 + " ms");
        }
    }

}
