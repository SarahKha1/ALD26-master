package A07_Sorting;

import org.junit.Before;
import org.junit.Test;
import java.util.Random;
import static org.junit.Assert.*;

public class QuickSortTest extends PersonenSortTest {

	@Before
	public void setUp() {
		ps = new QuickSort();
	}

    @Test
    public void laufzeitMessungE4() {

        int[] groessen = {1000, 5000, 10000, 20000};

        System.out.println("Java: " + System.getProperty("java.version"));

        for (int n : groessen) {

            Person[] daten = new Person[n];
            Random random = new Random(42);

            // Zufällige Personen erstellen
            for (int i = 0; i < n; i++) {
                daten[i] = new Person(
                        "Vorname" + random.nextInt(100000),
                        "Nachname" + random.nextInt(100000)
                );
            }

            long bubbleZeit = 0;
            long quickZeit = 0;

            // Ein Warm-up und 5 Messungen
            for (int r = 0; r < 6; r++) {

                // Beide bekommen dieselben Daten
                Person[] bubbleDaten = daten.clone();
                Person[] quickDaten = daten.clone();

                // BubbleSort messen
                long start = System.nanoTime();
                new BubbleSort().sort(bubbleDaten);
                long zeitBubble = System.nanoTime() - start;

                // QuickSort messen
                start = System.nanoTime();
                new QuickSort().sort(quickDaten);
                long zeitQuick = System.nanoTime() - start;

                // Überprüfen, ob richtig sortiert wurde
                for (int i = 1; i < n; i++) {
                    assertTrue(bubbleDaten[i - 1]
                            .compareTo(bubbleDaten[i]) <= 0);

                    assertTrue(quickDaten[i - 1]
                            .compareTo(quickDaten[i]) <= 0);
                }

                // Ersten Durchlauf nicht mitzählen
                if (r > 0) {
                    bubbleZeit += zeitBubble;
                    quickZeit += zeitQuick;
                }
            }

            System.out.println("Personen: " + n);
            System.out.println("BubbleSort: "
                    + bubbleZeit / 5000000.0 + " ms");
            System.out.println("QuickSort: "
                    + quickZeit / 5000000.0 + " ms");
        }
    }

}
