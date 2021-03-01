package ovings_oppgaver.stack;

import java.util.Scanner;
import java.util.StringTokenizer;
import tools.jsjf.ArrayStack;

// Skriv om det enkle programmet for beregning av Postfix-uttykk,
// slik at det også håndterer negative heltallsverdier riktig.

public class PostFix {
    public static int beregn(String uttrykk)
    {
        ArrayStack S = new ArrayStack(1000);

        char operasjon;
        int operand_1, operand_2, resultat = 0;

        String token;
        StringTokenizer tokenizer = new StringTokenizer(uttrykk);

        while (tokenizer.hasMoreTokens())
        {
            token = tokenizer.nextToken();
            operasjon = token.charAt(0);

            if (operasjon == '+' || (operasjon == '-' && token.length() == 1) ||
                    operasjon == '*' || operasjon == '/')
            {
                operand_2 = (int) S.pop();
                operand_1 = (int) S.pop();

                switch (operasjon) {
                    case '+' -> resultat = operand_1 + operand_2;
                    case '-' -> resultat = operand_1 - operand_2;
                    case '*' -> resultat = operand_1 * operand_2;
                    case '/' -> resultat = operand_1 / operand_2;
                }
                S.push(resultat);
            }
            else
                S.push(Integer.parseInt(token));
        }
        return (int) S.pop();
    }

    public static void main (String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.print("Postfix ? ");
        System.out.println("Resultat: " + beregn(in.nextLine()));
    }
}
