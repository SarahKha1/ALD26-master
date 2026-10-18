package A06_Tiefensuche;

import java.util.List;
import java.util.ArrayList;

import A05_Breitensuche.BaseTree;
import A05_Breitensuche.Node;

public class Tiefensuche extends BaseTree<Film> {

    @Override
    /**
     * Sortierkriterium im Baum: Länge des Films
     */
    protected int compare(Film a, Film b) {
        return Double.compare(a.getLänge(), b.getLänge());
    }

    /**
     * Retourniert die Titelliste der Film-Knoten des Teilbaums in symmetrischer Folge (engl. in-order, d.h. links-Knoten-rechts)
     * @param node Wurzelknoten des Teilbaums
     * @return Liste der Titel in symmetrischer Reihenfolge
     */
    public List<String> getNodesInOrder(Node<Film> node) {
        List<String> result = new ArrayList<>();

        collectInOrder(node, result);

        return result;
    }

    private void collectInOrder(Node<Film> node, List<String> result) {
        if (node == null) {
            return;
        }

        collectInOrder(node.getLeft(), result);
        result.add(node.getValue().getTitel());
        collectInOrder(node.getRight(), result);
    }

    /**
     * Retourniert Titelliste jener Filme, deren Länge zwischen min und max liegt, in Hauptreihenfolge (engl. pre-order, d.h. Knoten-links-rechts)
     * @param min Minimale Länge des Spielfilms
     * @param max Maximale Länge des Spielfilms
     * @return Liste der Filmtitel in Hauptreihenfolge
     */
    public List<String> getMinMaxPreOrder(double min, double max) {
        List<String> result = new ArrayList<>();

        collectMinMaxPreOrder(root, min, max, result);

        return result;
    }

    private void collectMinMaxPreOrder(
            Node<Film> node, double min, double max, List<String> result) {

        if (node == null) {
            return;
        }

        Film film = node.getValue();

        if (film.getLänge() >= min && film.getLänge() <= max) {
            result.add(film.getTitel());
        }

        collectMinMaxPreOrder(node.getLeft(), min, max, result);
        collectMinMaxPreOrder(node.getRight(), min, max, result);
    }

}