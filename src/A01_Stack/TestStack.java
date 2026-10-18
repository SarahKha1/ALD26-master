package A01_Stack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestStack {

	@Test
	public void getCount() throws StackEmptyException {
		Stack<Integer> s = new Stack<>();
		assertEquals("Leerer Stack", 0, s.getCount());
		s.push(1);
		assertEquals("Ein Element", 1, s.getCount());
		s.push(2);
		assertEquals("Zwei Elemente", 2, s.getCount());
		s.push(3);
		assertEquals("Drei Elemente", 3, s.getCount());
		s.pop();
		assertEquals("Zwei Elemente", 2, s.getCount());
		s.pop();
		assertEquals("Ein Element", 1, s.getCount());
		s.push(4);
		assertEquals("Zwei Elemente", 2, s.getCount());
		s.pop();
		s.pop();
		assertEquals("Leerer Stack", 0, s.getCount());
	}

	@Test(expected=StackEmptyException.class)
	public void stackEmptyException() throws StackEmptyException {
		Stack<Integer> s = new Stack<>();
		s.pop();
	}

	@Test(expected=StackEmptyException.class)
	public void stackEmptyAgainException() throws StackEmptyException {
		Stack<Integer> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.pop();
		s.pop();
		s.pop();
	}

	@Test
	public void pushPop() throws StackEmptyException {
		Stack<Integer> s = new Stack<>();
		s.push(1);
		s.push(2);
		s.push(3);
		assertEquals("Drei", 3, (int)s.pop());
		s.push(4);
		assertEquals("Vier", 4, (int)s.pop());
		s.push(5);
		s.push(6);
		assertEquals("Sechs", 6, (int)s.pop());
		assertEquals("Fünf", 5,(int) s.pop());
		assertEquals("Zwei", 2,(int) s.pop());
		assertEquals("Eins", 1,(int) s.pop());
		s.push(7);
		assertEquals("Sieben", 7,(int) s.pop());
		s.push(8);
		s.push(9);
		assertEquals("Neun", 9,(int) s.pop());
		assertEquals("Acht", 8,(int) s.pop());
	}

    @Test
    public void laufzeitMessungE1() {
        int[] groessen = {1000, 10000, 100000};

        System.out.println("Java: " + System.getProperty("java.version"));

        for (int n : groessen) {
            long pushZeit = 0;
            long enqueueZeit = 0;
            long stackCountZeit = 0;
            long queueCountZeit = 0;
            long listenGetZeit = 0;

            // Ein Warm-up-Durchlauf und 5 Messungen
            for (int r = 0; r < 6; r++) {
                Stack<Integer> stack = new Stack<>();
                A01a_Queue.Queue<Integer> queue =
                        new A01a_Queue.Queue<>();
                A03_DoubleLinkedList.DoubleLinkedList<Integer> liste =
                        new A03_DoubleLinkedList.DoubleLinkedList<>();

                long start = System.nanoTime();
                for (int i = 0; i < n; i++) {
                    stack.push(i);
                }
                long push = System.nanoTime() - start;

                start = System.nanoTime();
                for (int i = 0; i < n; i++) {
                    queue.enqueue(i);
                }
                long enqueue = System.nanoTime() - start;

                for (int i = 0; i < n; i++) {
                    liste.add(i);
                }

                start = System.nanoTime();
                int stackAnzahl = stack.getCount();
                long stackCount = System.nanoTime() - start;

                start = System.nanoTime();
                int queueAnzahl = queue.getCount();
                long queueCount = System.nanoTime() - start;

                start = System.nanoTime();
                Integer letzterWert = liste.get(n);
                long listenGet = System.nanoTime() - start;

                assertEquals(n, stackAnzahl);
                assertEquals(n, queueAnzahl);
                assertEquals(Integer.valueOf(n - 1), letzterWert);

                // Ersten Durchlauf nicht mitrechnen
                if (r > 0) {
                    pushZeit += push;
                    enqueueZeit += enqueue;
                    stackCountZeit += stackCount;
                    queueCountZeit += queueCount;
                    listenGetZeit += listenGet;
                }
            }

            System.out.println("Elemente: " + n);
            System.out.println("Stack push: " + pushZeit / 5000000.0 + " ms");
            System.out.println("Queue enqueue: " + enqueueZeit / 5000000.0 + " ms");
            System.out.println("Stack getCount: " + stackCountZeit / 5000000.0 + " ms");
            System.out.println("Queue getCount: " + queueCountZeit / 5000000.0 + " ms");
            System.out.println("Liste get(n): " + listenGetZeit / 5000000.0 + " ms");
        }
    }
}