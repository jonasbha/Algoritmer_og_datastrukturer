package ovings_oppgaver.que;

/* Tidssdrevet simulering av aktiviteten pÃ¥ et postkontor:
 *
 * - Alle kundene som ankommer stiller seg i en og samme kundekÃ¸
 * - Det kommer maksimalt Ã©n ny kunde per tidsenhet, sannsynligheten
 *   for kundeankomst er den samme i alle tidsteg
 * - Det er et fast antall kasser/betjeningsluker
 * - Det er en Ã¸vre grense for betjeningstiden for en kunde
 * - Brukes Javas egen innebygde kÃ¸
 *
 * Skriv om koden for simulering av et postkontor slik at det brukes flere køer.
 * Det skal nå være en kø av kunder foran hver kasse.
 * Nyankomne kunder vil alltid stille seg i den korteste køen.
 *
 * Test programmet og se om det blir noen forskjell i ventetider i forhold til
 * simuleringen med bare én kundekø.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class postkontor
{
    int    antKasser;  // Antall Ã¥pne kasser/betjeningsluker
    int    maksBetTid; // Maksimal betjeningstid for en  kunde
    int    maksTid;    // Antall tidssteg som simuleringen skal gÃ¥
    float  P_kunde;    // Sannsynligheten for kundeankomst i hvert tidssteg

    public postkontor(int antKasser, int maksBetTid, int maksTid, float p_kunde) {
        this.antKasser = antKasser;
        this.maksBetTid = maksBetTid;
        this.maksTid = maksTid;
        P_kunde = p_kunde;
    }

    // KonstruktÃ¸r som leser data/parametre for simuleringen

    // Indre klasse som lagrer ankomsttiden for en kunde
    private class kunde
    {
        private int ankomst;
        public kunde(int ankomst) { this.ankomst = ankomst; }
        public int venteTid(int tid) { return tid - ankomst; }
    }

    // Indre klasse som lagrer tidspunktet nÃ¥r en kasse blir ledig
    private class kasse
    {
        private int tidFerdigKunde;
        public kasse() { tidFerdigKunde = 0; }

        public void settTidFerdigKunde(int tid) { tidFerdigKunde = tid; }
        public boolean erLedig(int tid) { return tid >= tidFerdigKunde; }
    }

    // Metode som gjÃ¸r selve kÃ¸simuleringen
    public void simuler()

    {
        // Variable for Ã¥ samle opp data underveis i simuleringen
        //
        int totalBetTid   = 0; // Sum betjeningstid for alle betjente kunder
        int totalVenteTid = 0; // Sum ventetid for alle betjente kunder
        int totAntKunder  = 0; // Totalt antall kunder betjent
        int totAntledig   = 0; // Totalt antall ganger en kasse var ledig

        // Lager nye tomme kundekøer
        ArrayList<Queue<kunde>> kundeKøer = new ArrayList<>();
        for (int i = 0; i < antKasser; i++)
            kundeKøer.add(new LinkedList<>());

        // Oppretter en array som lagrer alle Ã¥pne kasser
        kasse[] kasser = new kasse[antKasser];

        // Setter alle kassene til å være ledige
        for (int i = 0; i < antKasser; i++)
            kasser[i] = new kasse();

        // Initierer trekkingen av tilfeldige tall
        Random R = new Random();

        // Simulerer et og et tidssteg
        for (int tid = 0; tid < maksTid; tid++)
        {

            // velger ut korteste kø
            Queue kortesteKø = kundeKøer.get(0);
            for (int i = 1; i < kundeKøer.size(); i++) {
                if (kundeKøer.get(i).size() < kortesteKø.size())
                    kortesteKø = kundeKøer.get(i);
            }

            // Trekker nytt tilfeldig tall. Hvis tallet som trekkes er
            // mindre enn sannsynligheten for ny kunde (P_kunde),
            // settes en ny kunde inn i den korteste køen

            if (R.nextDouble() < P_kunde) {
                kortesteKø.add(new kunde(tid));
            }

            // Tar ut en kunde fra køen for hver ledige kasse
            for (int i = 0; i < antKasser; i++)
                if (kasser[i].erLedig(tid))
                {
                    if (!kundeKøer.get(i).isEmpty())
                    {
                        // Trekk en tilfelding betjeningstid
                        int betTid = R.nextInt(maksBetTid) + 1;

                        // Tar ut kunde fra kÃ¸
                        kunde k = kundeKøer.get(i).remove();

                        // Markerer at kassen er opptatt
                        kasser[i].settTidFerdigKunde(tid + betTid);

                        // Samler opp data fra dette tidssteget
                        totalBetTid += betTid;
                        totalVenteTid += k.venteTid(tid);
                        totAntKunder++;
                    }
	            else
                    // KÃ¸en er tom og vi har en ledig kasse
                    totAntledig++;
                }
        }

        int antKunderIKø = 0;
        for (int i = 0; i < antKasser; i++)
            antKunderIKø += kundeKøer.get(i).size();

        // Simulering ferdig, skriv ut litt statistikk
        System.out.println("\nPostkontoret er nå stengt");
        System.out.println("Antall kunder behandlet: " + totAntKunder);
        for (int i = 0; i < antKasser; i++)
            System.out.println("Antall kunder i kø " + (i+1) + ": " + kundeKøer.get(i).size());
        System.out.println("Totalt antall kunder i kø: " + antKunderIKø);
        if (totAntKunder > 0)
        {
            System.out.println("Gjennomsnittlig betjeningstid: " +
                    (float) totalBetTid / (float) totAntKunder);
            System.out.println("Gjennomsnittlig ventetid: " +
                    (float) totalVenteTid / (float) totAntKunder);

        }
        System.out.println("Gjennomsnittlig antall ledige kasser: " +
                (float) totAntledig / (float) maksTid );

    }

    // Testprogram
    public static void main(String[] args)
    {
        int antKasser = 5;
        int maksBetjeningstid = 20;
        int maksTid = 60*8;
        float kundeTrafikk = (float) 0.6;

        postkontor pk = new postkontor(antKasser, maksBetjeningstid, maksTid, kundeTrafikk);
        pk.simuler();
    }

}