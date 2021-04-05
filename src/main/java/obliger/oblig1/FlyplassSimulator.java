package obliger.oblig1;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.Scanner;

public class FlyplassSimulator {

    int maksVenteTidForLanding;
    int maksVenteTidForAvgang;
    int totaleTidsEnheter;
    int maksFlyILandingsKø;
    int maksFlyIAvgangsKø;
    double gjAnkomsterPerTidsEnhet;
    double gjAvgangerPerTidsenhet;

    public FlyplassSimulator(int maksVenteTidForLanding, int maksVenteTidForAvgang, int totaleTidsEnheter, int maksFlyILandingsKø, int maksFlyIAvgangsKø, double gjAnkomsterPerTidsEnhet, double gjAvgangerPerTidsenhet) {
        this.maksVenteTidForLanding = maksVenteTidForLanding;
        this.maksVenteTidForAvgang = maksVenteTidForAvgang;
        this.totaleTidsEnheter = totaleTidsEnheter;
        this.maksFlyILandingsKø = maksFlyILandingsKø;
        this.maksFlyIAvgangsKø = maksFlyIAvgangsKø;
        this.gjAnkomsterPerTidsEnhet = gjAnkomsterPerTidsEnhet;
        this.gjAvgangerPerTidsenhet = gjAvgangerPerTidsenhet;
    }

    public void start() {
        StringBuilder builder = new StringBuilder();

        int avisteLandinger = 0;
        int avisteAvganger = 0;
        int antallLandinger = 0;
        int antallAvganger = 0;
        int landingsKøTom = 0;
        int avgangsKøTom = 0;
        double tomFlyplass = 0;
        float totalVenteTidForAvgang = 0;
        float totalVenteTidForLanding = 0;

        Queue<Fly> landingsKø = new ArrayDeque<>();
        Queue<Fly> avgangsKø = new ArrayDeque<>();
        Rullebane bane = new Rullebane();
        Random r = new Random();

        builder.append("Simulator kjører.. \n\n");
        for (int tidsEnhet = 1; tidsEnhet <= totaleTidsEnheter; tidsEnhet++) {

            for (int i = 1; i < getPoissonRandom(gjAnkomsterPerTidsEnhet)+1; i++) {
                builder.append("Fly nr: ").append(i).append(", tidsenhet ").append(tidsEnhet).append(", søker om tilatelse til å lande.");
                if (landingsKø.size() == maksFlyILandingsKø) {
                    builder.append("Nytt ankommende fly nr ").append(i).append(" anvises til annen flyplass.\n");
                    avisteLandinger++;
                }
                else {
                    builder.append(" Søknad invilges.\n");
                    landingsKø.add(new Fly());
                }
            }

            for (int i = 1; i < getPoissonRandom(gjAvgangerPerTidsenhet) + 1; i++) {
                builder.append("Fly nr: ").append(i).append(", tidsenhet ").append(tidsEnhet).append(", søker om tilatelse til å fly.");
                if (avgangsKø.size() == maksFlyIAvgangsKø) {
                    builder.append(" Søknad avises.\n");
                    avisteAvganger++;
                }
                else {
                    builder.append(" Søknad invilges.\n");
                    avgangsKø.add(new Fly());
                }
            }

            if (landingsKø.isEmpty())
                landingsKøTom++;
            if (avgangsKø.isEmpty())
                avgangsKøTom++;

            if (!landingsKø.isEmpty()) {
                if (bane.erLedig(tidsEnhet)) {
                    builder.append("Rullebane er ledig. Fly fremst i landingskø lander.\n");
                    landingsKø.remove();
                    antallLandinger++;
                    int venteTid = r.nextInt(maksVenteTidForLanding + 1);
                    bane.setTidLedig(tidsEnhet + venteTid);
                    totalVenteTidForLanding += venteTid;
                }
            } else if (!avgangsKø.isEmpty()) {
                if (bane.erLedig(tidsEnhet)) {
                    builder.append("Rullebane er ledig. Fly fremst i avgangskø inntrer rullebanen.\n");
                    avgangsKø.remove();
                    antallAvganger++;
                    int venteTid = r.nextInt(maksVenteTidForAvgang + 1);
                    bane.setTidLedig(tidsEnhet + venteTid);
                    totalVenteTidForAvgang += venteTid;
                }
            } else {
                if (bane.erLedig(tidsEnhet))
                    tomFlyplass++;
            }
        }
        builder.append("\nSimulator er avsluttet. \n");

        int antallFlyBehandlet = antallAvganger + antallLandinger;
        tomFlyplass = tomFlyplass / ( totaleTidsEnheter ) * 100;
        float gjVenteTidLanding;
        if (antallLandinger > 0)
            gjVenteTidLanding = totalVenteTidForLanding / antallLandinger;
        else
            gjVenteTidLanding = 0;

        float gjVenteTidAvgang;
        if (antallAvganger > 0)
            gjVenteTidAvgang = totalVenteTidForAvgang / antallAvganger;
        else
            gjVenteTidAvgang = 0;

        builder.append("\n");
        builder.append("\nAntall: ");
        builder.append("\n   aviste landinger = ").append(avisteLandinger);
        builder.append("\n   aviste avganger = ").append(avisteAvganger);
        builder.append("\n   landinger = ").append(antallLandinger);
        builder.append("\n   avganger = ").append(antallAvganger);
        builder.append("\n   fly behandlet = ").append(antallFlyBehandlet);
        builder.append("\n   tidsenheter der landingskøen var tom = ").append(landingsKøTom);
        builder.append("\n   tidsenheter der avgangskøen var tom = ").append(avgangsKøTom);
        builder.append("\n   fly i landingskøen etter total tid = ").append(landingsKø.size());
        builder.append("\n   fly i avgangskøen etter total tid = ").append(avgangsKø.size());
        builder.append("\nGjennomsnittlig: ");
        builder.append("\n   ventetid for landing = ").append(gjVenteTidLanding).append(" tidsenheter");
        builder.append("\n   ventetid for avgang = ").append(gjVenteTidAvgang).append(" tidsenheter");
        builder.append("\nProsentandel av tiden der flyplassen stod tom = ").append(tomFlyplass).append("%");
        System.out.println(builder.toString());
    }

    private class Fly {
        public Fly() {

        }
    }

    private class Rullebane {
        int tidLedig;

        Rullebane() {
            tidLedig = 1;
        }

        public void setTidLedig(int tidLedig) {
            this.tidLedig = tidLedig;
        }

        public boolean erLedig(int tid) {
            return tid >= tidLedig;
        }
    }

    private static int getPoissonRandom(double mean)
    {
        Random r = new Random();
        double L = Math.exp(-mean);
        int k = 0;
        double p = 1.0;
        do
        {
            p = p * r.nextDouble();
            k++;
        } while (p > L);
        return k - 1;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Maks ventetid for landing:");
        int mVTL = Integer.parseInt(in.nextLine());
        System.out.print("Maks ventetid for avgang:");
        int mVTA = Integer.parseInt(in.nextLine());
        System.out.print("Totale antall tidsenheter flyplassen er åpen:");
        int tTE = Integer.parseInt(in.nextLine());
        System.out.print("Maks størrelse på landingskø:");
        int mFILK = Integer.parseInt(in.nextLine());
        System.out.print("Maks størrelse på avgangskø:");
        int mFIAK = Integer.parseInt(in.nextLine());
        System.out.print("gj. ankomster per tidsenhet:");
        float gAPT = Float.parseFloat(in.nextLine());
        System.out.print("gj avganger per tidsenhet:");
        float gAnPT = Float.parseFloat(in.nextLine());

        // 2, 2, 20, 4, 7, 0.6, 0.4,
        new FlyplassSimulator(
                mVTL,
                mVTA,
                tTE,
                mFILK,
                mFIAK,
                gAPT,
                gAnPT
        ).start();
    }
}
