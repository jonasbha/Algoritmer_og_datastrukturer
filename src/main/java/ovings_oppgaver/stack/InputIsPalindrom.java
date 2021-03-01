package ovings_oppgaver.stack;

import tools.jsjf.ArrayStack;

import java.util.Scanner;

public class InputIsPalindrom {

    /*
    * Skriv om eksempelprogrammet fra forelesning som reverserer tekststrenger,
    * slik at programmet i stedet bruker en stack til å sjekke om den innleste strengen er et palindrom.
    *
    * Et palindrom er et ord, uttrykk eller tall som gir samme resultat
    * enten det leses fra høyre eller venstre
    * (man ser vanligvis bort fra mellomrom og tegnsetting mellom bokstavene).
    * */

    public static boolean isPalindrom(String line) {
        ArrayStack<Character> stack = new ArrayStack<>();

        for (int i = 0; i < line.length(); i++)
            stack.push(line.charAt(i));

        int i = 0;

        while(!stack.isEmpty())
            if (line.charAt(i++) != stack.pop())
                return false;
        return true;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();

        line = line.toLowerCase();
        line = line.replaceAll("\\s","");

        if (isPalindrom(line))
            System.out.println("Input is palindrom");
        else
            System.out.println("Input is not palindrom");

    }
}