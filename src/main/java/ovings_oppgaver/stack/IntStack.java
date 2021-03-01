package ovings_oppgaver.stack;

//Skriv om IntStack slik at den håndterer feil på en fornuftig måte
// den kræsjer hvis array blir full - konvertere til arraylist eller kopiere over content til nytt array * X.
// den tåler ikke fjerning av data når stacken er tom - cast exception
// den tåler ikke peek() når stacken er tom - cast exception

import java.util.ArrayList;

public class IntStack {
    private int top;
    private ArrayList<Integer> stack;

    public IntStack(int length)
    {
        top = 0;
        stack = new ArrayList<>(length);
    }

    public void push(int i) throws Exception {
        if (stack.isEmpty())
            throw new Exception("stack is empty");
        stack.add(i);
    }

    public int pop() throws Exception {
        if  (stack.isEmpty())
            throw new Exception("stack is empty");
        int val = stack.get(stack.size()-1);
        stack.remove(val);
        return(val);
    }

    public int peek()
    {
        return stack.get(stack.size()-1);
    }

    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    public int size()
    {
        return stack.size();
    }
}
