package ovings_oppgaver.stack;

/*
*
*  1 Lag en ny tom stack S
  2 For alle tegn T i uttrykket:
     2.1 Hvis T er en operand: Skriv ut T
     2.2 Ellers:
          2.2.1 Så lenge T har rang lavere eller lik toppen av S:
                 2.2.1.1 Pop S og skriv ut
          2.2.2 Push T på S
  3 Pop resten av S og skriv ut
Du kan anta at:
Regneuttrykkene har ikke paranteser.
Uttrykkene inneholder kun de binære operatorene + - * / .
Alle operander er enkle bokstaver i intervallet a-z.
Programmet skal lese inn et regneuttrykk som dette:
  a+b*c+d/f-e
og deretter skrive ut det tilsvarende postfixutrykket:
  abc*+df/+e-  */

import tools.jsjf.ArrayStack;

import java.util.Scanner;

public class InfixToPostfix {

    public static void main(String[] args) {
        ArrayStack<Character> S = new ArrayStack(100);

        String input = new Scanner(System.in).nextLine();



            //Så lenge T har rang lavere eller lik toppen av S:
            //                 2.2.1.1 Pop S og skriv ut

            for (int i = 0; i < input.length(); i++) {
                if (isOperand(input.charAt(i)))
                    System.out.println(input.charAt(i));
                else
                    if (!S.isEmpty())
                        while (input.charAt(i) <= S.peek())
                            System.out.println(S.pop());
                    else
                        S.push(input.charAt(i));
            }
            while (!S.isEmpty())
                System.out.println(S.pop());


        }

    private static boolean isOperand(char c) {
        return c >= 'a' && c <= 'z';
    }
}

