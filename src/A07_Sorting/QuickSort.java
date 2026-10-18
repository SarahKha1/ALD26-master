package A07_Sorting;

public class QuickSort implements PersonenSort {

    public void sort(Person[] personen) {
        sort(personen, 0, personen.length - 1);
    }

    private void sort(Person[] personen, int left, int right) {

        if (left >= right) {
            return;
        }

        int mitte = partition(personen, left, right);

        sort(personen, left, mitte);
        sort(personen, mitte + 1, right);
    }

    private int partition(Person[] personen, int left, int right) {

        Person pivot = personen[left + (right - left) / 2];

        int i = left - 1;
        int j = right + 1;

        while (true) {

            do {
                i++;
            } while (personen[i].compareTo(pivot) < 0);

            do {
                j--;
            } while (personen[j].compareTo(pivot) > 0);

            if (i >= j) {
                return j;
            }

            Person temp = personen[i];
            personen[i] = personen[j];
            personen[j] = temp;
        }
    }
}