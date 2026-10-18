package A05_Breitensuche;

import java.util.List;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;

public class Breitensuche extends BaseTree<Integer> {

    @Override
    protected int compare(Integer a, Integer b) {
        return a.compareTo(b);
    }

    /**
     * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück
     * @param start Startknoten für Teilbaum
     * @return Liste der Knoten in Breitenfolge
     */
    public List<Integer> getBreadthFirstOrder(Node<Integer> start) {
        List<Integer> result = new ArrayList<>();

        if (start == null) {
            return result;
        }

        Queue<Node<Integer>> queue = new ArrayDeque<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Node<Integer> current = queue.remove();
            result.add(current.getValue());

            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }

            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }

        return result;
    }

    /**
     * Liefert Knoten des Baums ausgehend von Start in Reihenfolge der Breitensuche zurück,
     * allerdings nur jene Knoten, die in der angegebenen Ebene liegen (Start hat Ebene=1)
     * @param start Startknoten für Teilbaum
     * @param level Nur Knoten dieser Ebene ausgeben
     * @return Liste aller Knoten
     */
    public List<Integer> getBreadthFirstOrderForLevel(Node<Integer> start, int level) {
        List<Integer> result = new ArrayList<>();

        if (start == null || level < 1) {
            return result;
        }

        Queue<Node<Integer>> queue = new ArrayDeque<>();
        queue.add(start);

        int currentLevel = 1;

        while (!queue.isEmpty()) {
            int nodesInLevel = queue.size();

            for (int i = 0; i < nodesInLevel; i++) {
                Node<Integer> current = queue.remove();

                if (currentLevel == level) {
                    result.add(current.getValue());
                } else {
                    if (current.getLeft() != null) {
                        queue.add(current.getLeft());
                    }

                    if (current.getRight() != null) {
                        queue.add(current.getRight());
                    }
                }
            }

            if (currentLevel == level) {
                return result;
            }

            currentLevel++;
        }

        return result;
    }

}