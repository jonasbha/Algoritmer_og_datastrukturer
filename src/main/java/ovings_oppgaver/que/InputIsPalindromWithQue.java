package ovings_oppgaver.que;

import tools.jsjf.ArrayStack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class InputIsPalindromWithQue {

    /*
    Skriv om palindromprogrammet fra uke 3 slik at det bruker en stack og en kø til å finne ut
    om en tekststreng er et palindrom. Programmet skal først legge alle tegnene både på
    stacken og i køen, og skal deretter sjekke om tegnene utgjør et palindrom.
     * */

    public static boolean isPalindrom(String line) {
        ArrayStack<Character> stack = new ArrayStack<>();
        Queue<Character> que = new LinkedList<>();

        for (int i = 0; i < line.length(); i++) {
            stack.push(line.charAt(i));
            que.add(line.charAt(i));
        }
        for (int i = 0; i < line.length(); i++) {
            if (stack.pop() != que.remove())
                return false;
        }
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
