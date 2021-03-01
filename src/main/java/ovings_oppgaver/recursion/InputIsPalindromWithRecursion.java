package ovings_oppgaver.recursion;

import tools.jsjf.ArrayStack;

import java.util.Scanner;

public class InputIsPalindromWithRecursion {

    /*
      Skriv om palindromprogrammet fra uke 3 slik at det bruker en rekursiv funksjon til
      å sjekke om en tekststreng er et palindrom .
    * */

    public static boolean isPalindrom(String n) {

        if (n.length() <= 1)
            return true;
        if (n.charAt(0) == n.charAt(n.length()-1))
            return isPalindrom(n.substring(1, n.length()-1));
        return false;
    }

    static ArrayStack<Character> stack;
    static String line;

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        line = in.nextLine();

        line = line.toLowerCase();
        line = line.replaceAll("\\s","");

        stack = new ArrayStack<>();

        for (int i = 0; i < line.length(); i++)
            stack.push(line.charAt(i));

        if (isPalindrom(line))
            System.out.println("Input is palindrom");
        else
            System.out.println("Input is not palindrom");

    }
}