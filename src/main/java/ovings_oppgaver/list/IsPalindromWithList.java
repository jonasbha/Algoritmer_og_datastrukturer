package ovings_oppgaver.list;

import tools.jsjf.ArrayStack;
import tools.jsjf.ArrayUnorderedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class IsPalindromWithList {
        /*
Skriv om palindromprogrammet slik at det i stedet bruker en uordnet, lenket liste til å
sjekke om tekststreng er et palindrom . Legg først alle tegnene inn i listen. Bruk deretter
metodene for å fjerne første og siste element i en liste til å gå gjennom hele tekststrengen.
Bruk lærebokas listemodul i programmet ditt.
     * */

    // big fail
    public static boolean isPalindrom(String line) {
        ArrayUnorderedList<Character> linkedList = new ArrayUnorderedList<>();

        for (int i = 0; i < line.length(); i++) {
            linkedList.addToFront(line.charAt(i));
        }

        while (!linkedList.isEmpty() && linkedList.size() > 1) {
            if (linkedList.removeFirst() != linkedList.removeLast())
                return false;
        }
        return true;
    }


    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        if (isPalindrom(line))
            System.out.println("Input is palindrom");
        else
            System.out.println("Input is not palindrom");

    }
}
