package ovings_oppgaver.heap;

import java.util.Optional;

public class Oppgave2 {

    public static void main(String[] args) {
        // oppgave 2:
        node newNode = new node(41,
                new node(1,
                        new node(3,
                                new node(30, null, null),
                                new node(8, null, null)),
                        new node(4,
                                new node(9, null, null),
                                new node(10, null, null))),
                new node(2,
                        new node(45,
                                new node(11, null, null),
                                new node(12, null, null)),
                        new node(6,
                                new node(45, null, null),
                                new node(14, null, null))));

        node.lag_heap_ordning(newNode);
        System.out.println(node.tell(newNode, 45));
    }

}
    class node {
        public node(int verdi, node v, node h) {
            this.verdi = verdi;
            this.v = v;
            this.h = h;
        }

        int verdi;  // Heltallsverdi
        node v, h;  // Venstre og høyre barn

        // a
        public static void repair(node rot) {

            node current = rot;
            int temp;
            while (true) {
                if (current.v != null && current.h != null) {
                    if (current.h.verdi >= current.v.verdi) {
                        temp = current.verdi;
                        current.verdi = current.v.verdi;
                        current.v.verdi = temp;
                        current = current.v;

                        if (current.v == null)
                            return;
                    }
                    else {
                        temp = current.verdi;
                        current.verdi = current.h.verdi;
                        current.h.verdi = temp;
                        current = current.h;

                        if (current.h == null)
                            return;
                    }
                }
                else if (current.v != null) {
                    temp = current.verdi;
                    current.verdi = current.v.verdi;
                    current.v.verdi = temp;

                    current = current.v;

                    if (current.v == null)
                        return;
                }
                else if (current.h != null) {
                    temp = current.verdi;
                    current.verdi = current.h.verdi;
                    current.h.verdi = temp;
                    current = current.h;

                    if (current.h == null)
                        return;
                }
                else return;
            }
        }

        // b
        public static void lag_heap_ordning(node rot) {

            if (rot == null)
                return;

            repair(rot);

            lag_heap_ordning(rot.v);
            lag_heap_ordning(rot.h);
        }

        public static int tell(node rot, int verdi) {
            if (rot == null)
                return 0;

            if (rot.verdi == verdi)
                return tell(rot.v, verdi) + tell(rot.h, verdi) + 1;
            else
                return tell(rot.v, verdi) + tell(rot.h, verdi);
        }
    }