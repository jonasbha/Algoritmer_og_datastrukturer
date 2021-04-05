package ovings_oppgaver.hashing.testing;

import java.io.*;
import java.util.Random;

// Tester effektivitet av binÃ¦rt sÃ¸ketre mot hashtabeller.
// Testprogrammet bruker fÃ¸lgende moduler med enkle og
// begrensende implementasjoner av:
//
//   intBST.java            BinÃ¦rt sÃ¸ketre med heltall
//   intHashLinear.java     Hashing av heltall med lineÃ¦r probing
//   intHashQuadratic.java  Hashing av heltall med kvadratisk probing
//   intHashChained.java    Hashing av heltall med kjeding

public class EfficiencyTestHashingBST
{
    final static int nMax     = 20000000;   // Maks. antall data for testing
    final static int nRepeats =     1000;   // Antall ganger hver operasjon skal testes

    static IntBST T;             // SÃ¸ketreet
    static IntHashLinear HL;            // Hashtabell med lineÃ¦r probing
    static IntHashQuadratic HQ;            // Hashtabell med kvadratisk probing
    static IntHashChained HC;            // Hashtabell med kvadratisk probing

    static Random R = new Random();         // For Ã¥ generere tilfeldige heltall som
    // brukes som data i testingen

    public static void main(String[] argv)
    {
        // Leser max. load faktorer for hashtabellen fra kommandolinjen
        double loadFactor = 0.75, loadFactorC = 0.75;

        // Oppretter et sÃ¸ketre
        T = new IntBST();

        // Oppretter hashtabeller med tilstrekkelig lengde

        int hashLengde = (int)(nMax/loadFactor);
        HL = new IntHashLinear(hashLengde);
        HQ = new IntHashQuadratic(hashLengde);

        int hashLengdeC = (int)(nMax/loadFactorC);
        HC = new IntHashChained(hashLengdeC);

        // Setter inn Ã¸kende antall data og tester effektivitet

        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("                                 Open Adress Hashing   Chained   Load Factors");
        System.out.println("       #  Operation   BST        Linear     Quadratic  Hashing   Open    Chained      n");
        System.out.print("----------------------------------------------------------------------------------------");

        test(       0,    10000);  // n =     10 000
        test(   10000,   90000);   // n =    100 000
        test(  100000,  200000);   // n =    300 000
        test(  300000,  700000);   // n =  1 000 000
        test( 1000000, 4000000);   // n =  5 000 000
        test( 5000000, 5000000);   // n = 10 000 000
        test(10000000, 10000000);  // n = 20 000 000

        System.out.println("----------------------------------------------------------------------------------------");
    }


    public static void test(int nOld, int nNew)
    {
        // Stor og stygg testfunksjon for Ã¥ mÃ¥le hastighet pÃ¥
        // binÃ¦rt sÃ¸ketre og hashtabell
        //
        //     nOld: Antall data som allerede er lagret i hver struktur.
        //
        //     nNew: Antall tilfeldige tall som skal legges inn i hver
        //           av de tre datastrukturene.
        //
        // Funksjonen setter inn alle de nye tallene, og tester
        // deretter tidsforbruket for sÃ¸king og innsetting

        int n = nOld + nNew;
        long time;

        // Setter inn alle de nye dataene

        System.out.printf("\n%8d  Insert     ", nNew);

        // Setter inn i sÃ¸ketre
        time = System.currentTimeMillis();
        for (int i = 0; i < nNew; i++)
            T.insert(R.nextInt());
        time = System.currentTimeMillis() - time;
        System.out.printf("%6.3f s   ", time/1000.0);

        // Setter inn i hashtabellene

        time = System.currentTimeMillis();
        for (int i = 0; i < nNew; i++)
            HL.insert(R.nextInt());
        time = System.currentTimeMillis() - time;
        System.out.printf("%6.3f s   ", time/1000.0);

        time = System.currentTimeMillis();
        for (int i = 0; i < nNew; i++)
            HQ.insert(R.nextInt());
        time = System.currentTimeMillis() - time;
        System.out.printf("%6.3f s   ", time/1000.0);

        time = System.currentTimeMillis();
        for (int i = 0; i < nNew; i++)
            HC.insert(R.nextInt());
        time = System.currentTimeMillis() - time;
        System.out.printf("%6.3f s  %5.2f   %5.2f  %9d\n", time/1000.0, HQ.loadFactor(), HC.loadFactor(), HQ.antData());


        // Innsetting av nye data ferdig, tester hastighet pÃ¥ sÃ¸king,
        // og innsetting

        // SÃ¸king

        System.out.printf("%8d  Search     ", nRepeats);

        // SÃ¸ketre
        time = System.currentTimeMillis();
        for (int i = 0; i < nRepeats; i++)
            T.search(R.nextInt());
        time = System.currentTimeMillis() - time;
        System.out.printf("%6.3f s   ", time/1000.0);

        // Hashtabellene

        time = System.currentTimeMillis();
        for (int i = 0; i < nRepeats; i++)
            HL.search(R.nextInt());
        time = System.currentTimeMillis() - time;
        System.out.printf("%6.3f s   ", time/1000.0);

        time = System.currentTimeMillis();
        for (int i = 0; i < nRepeats; i++)
            HQ.search(R.nextInt());
        time = System.currentTimeMillis() - time;
        System.out.printf("%6.3f s   ", time/1000.0);

        time = System.currentTimeMillis();
        for (int i = 0; i < nRepeats; i++)
            HC.search(R.nextInt());
        time = System.currentTimeMillis() - time;
        System.out.printf("%6.3f s\n", time/1000.0);


        // Innsetting

        System.out.printf("%8d  Insert     ", nRepeats);

        // SÃ¸ketre
        time = System.currentTimeMillis();
        for (int i = 0; i < nRepeats; i++)
            T.insert(R.nextInt());
        time = System.currentTimeMillis() - time;
        System.out.printf("%6.3f s   ", time/1000.0);

        // Hashtabellene

        time = System.currentTimeMillis();
        for (int i = 0; i < nRepeats; i++)
            HL.insert(R.nextInt());
        time = System.currentTimeMillis() - time;
        System.out.printf("%6.3f s   ", time/1000.0);

        time = System.currentTimeMillis();
        for (int i = 0; i < nRepeats; i++)
            HQ.insert(R.nextInt());
        time = System.currentTimeMillis() - time;
        System.out.printf("%6.3f s   ", time/1000.0);

        time = System.currentTimeMillis();
        for (int i = 0; i < nRepeats; i++)
            HC.insert(R.nextInt());
        time = System.currentTimeMillis() - time;
        System.out.printf("%6.3f s\n", time/1000.0);
    }

}
