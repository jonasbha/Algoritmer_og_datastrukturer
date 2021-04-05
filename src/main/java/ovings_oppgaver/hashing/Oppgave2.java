package ovings_oppgaver.hashing;

public class Oppgave2 {

    // Lag en enkel hashfunksjon som regner ut indekser mellom 0 og n - 1 ut i
    // fra datanøkler som består av 3-bokstavssekvenser

    // enkel folding
    public static int simpelFolding(String key, int hashLength) {
        int value = 0;

        for (int i = 0; i < key.length(); i++)
            value += Character.getNumericValue(key.charAt(i));

        return value % hashLength;
    }

    public static int vektetFolding2(String key, int hashLength) {
        int value = 0;

        for (int i = 0; i < key.length(); i+=2) {
            String string = String.valueOf(Character.getNumericValue(key.charAt(i)));

            int num = 1;
            for (int k = 0; k < string.length(); k++) {
                value += Integer.parseInt(String.valueOf(string.charAt(k))) * num;
                num*=10;
            }
        }
        return value % hashLength;
    }

    // Use folding on a string, summed 4 bytes at a time
    public static long vektetFolding(String s, int M) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char c[] = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char c[] = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return(Math.abs(sum) % M);
    }

    public static void main(String[] args) {
        String[] strings = {"PAL", "LAP", "PAM", "MAP", "PAT", "PET", "SET", "SAT", "TAT", "BAT"};

        for (String string : strings) System.out.println(vektetFolding2(string, 13));

    }
}
