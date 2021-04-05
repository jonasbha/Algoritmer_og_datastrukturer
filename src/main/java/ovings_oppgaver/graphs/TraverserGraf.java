package ovings_oppgaver.graphs;

import java.util.*;

// Dybde-fÃ¸rst og bredde-fÃ¸rst traversering av graf.
//
// Bruker en enkel uvektet graf med nabomatrise, der data i nodene
// bare er en tekststreng (superklassen enkelGraf)
//
public class TraverserGraf extends EnkelGraf
{

    // Bruker en boolsk array til Ã¥ merke av oppsÃ¸kte noder under
    // traversering
    boolean oppsokt[];


    // KonstruktÃ¸r, leser inn en graf fra fil
    //
    public TraverserGraf(String filNavn)
    {
        super(filNavn);
        oppsokt = new boolean[n];
    }


    // Dybde-fÃ¸rst traversering med start i gitt node
    // OppsÃ¸ker hver node og skriver ut data i noden
    //
    public void DFS(int start)
    {
        // Markerer alle noder som ikke oppsÃ¸kte
        for (int i = 0; i < n; i++)
            oppsokt[i] = false;

        // Kaller en intern rekursiv metode som gjÃ¸r selve
        // traverseringen
        iDFS(start);

        System.out.println();
    }

    // Intern rekursiv dybde-fÃ¸rst traversering med start i gitt node
    //
    private void rDFS(int x)
    {
        // Skriver ut innholdet i noden
        System.out.print(data[x] + " ");

        // Markerer at denne noden er oppsÃ¸kt
        oppsokt[x] = true;

        // OppsÃ¸ker rekursivt alle nodens naboer som ikke er oppsÃ¸kt
        // tidligere
        for (int y = 0; y < n; y++)
            if (nabo[x][y] && !oppsokt[y])
                rDFS(y);
    }

    // Iterativ versjon av dybde-først traversering.
    //
    private void iDFS(int init)
    {
        Deque<Integer> stack = new ArrayDeque<>();

        stack.add(init);
        oppsokt[init] = true;

        while (!stack.isEmpty()) {
            int x = stack.pop();
            System.out.print(data[x] + " ");

            for (int y = 0; y < n; y++)
                if (nabo[x][y] && !oppsokt[y]) {
                    stack.add(y);
                    oppsokt[y] = true;
                }
        }
    }


    // Bredde-fÃ¸rst traversering med start i gitt node
    //
    // Bruker kÃ¸-implementasjonen i java.util (der "enqueue"-metoden
    // heter "add" og "dequeue" heter "remove")
    //
    public void BFS(int start)
    {
        // Markerer alle noder som ikke oppsÃ¸kte
        for (int i = 0; i < n; i++)
            oppsokt[i] = false;

        // Lager ny tom kÃ¸ med heltall/nodenumre
        Queue<Integer> Q = new LinkedList<>();

        // Legger start-noden fÃ¸rst i kÃ¸en
        Q.add(start);

        // Markerer at startnoden er lagret/oppsÃ¸kt, slik at den ikke
        // legges i kÃ¸ en gang til senere
        oppsokt[start] = true;

        // GÃ¥r i lÃ¸kke inntil kÃ¸en er tom og alle noder er oppsÃ¸kt
        while (!Q.isEmpty())
        {
            // Tar ut fÃ¸rste node fra kÃ¸en og oppsÃ¸ker/skriver ut
            // denne
            int x = Q.remove();
            System.out.print(data[x] + " ");

            // Legger alle uoppsÃ¸kte/ikke-lagrede naboer til nÃ¥vÃ¦rende
            // node bakerst i kÃ¸en
            for (int y = 0; y < n; y++)
                if (nabo[x][y] && !oppsokt[y])
                {
                    Q.add(y);
                    oppsokt[y] = true;
                }
        }
        System.out.println();
    }

    // Testprogram
    //
    public static void main(String args[])
    {
        // Leser navnet pÃ¥ en fil med grafdata som input fra
        // kommandolinjen
        String filNavn = "src/main/java/ovings_oppgaver/graphs/uvektet.txt";

        // Oppretter ny graf
        TraverserGraf G = new TraverserGraf(filNavn);

        // Dybde-fÃ¸rst traversering en gang med start i hver node
        System.out.println("DFS:");
        for (int i = 0; i < G.antallNoder(); i++)
            G.DFS(i);

        // Bredde-fÃ¸rst traversering en gang med start i hver node
        System.out.println("\nBFS:");
        for (int i = 0; i < G.antallNoder(); i++)
            G.BFS(i);
    }
}
