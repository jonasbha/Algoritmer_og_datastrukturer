package ovings_oppgaver.stack;


import tools.jsjf.ArrayStack;

public class StackPractice {

    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();
        stack.push(7);
        stack.push(2);
        stack.push(1);
        stack.push(5);
        stack.push(2);

        System.out.println(getThirdElement(stack));
        System.out.println(getLastElement(stack));
        removeElement(stack, 2);
        System.out.println(stack.size());
    }


    /*
    * Returner innholdet av tredje element på S, telt fra toppen av stacken.
    * Hvis S inneholder mindre enn 3 elementer, returneres null.
    * Innholdet av S skal ikke være forandret når funksjonen terminerer.
    * */

    public static int getThirdElement(ArrayStack<Integer> stack) {
        if (stack.size() < 3)
            return 0;

        int first = stack.pop();
        int second = stack.pop();
        int third = stack.pop();
        stack.push(third);
        stack.push(second);
        stack.push(first);

        return third;
    }

    /*
    * Returner innholdet av elementet på bunnen av S.
    * Hvis S er tom, returneres null. S skal ikke være forandret ved terminering.
    * Her må(?) det brukes en ekstra stack.
    * */

    public static int getLastElement(ArrayStack<Integer> stack) {
        ArrayStack<Integer> tempStack = new ArrayStack<>();
        int lastElement = 0;

        if (stack.isEmpty())
            return lastElement;
        else {
            while (!stack.isEmpty()) {
                    lastElement = stack.pop();
                    tempStack.push(lastElement);
            }
            while (!tempStack.isEmpty())
                stack.push(tempStack.pop());
        }

        return lastElement;
    }

    /*
    * Fjern alle elementer på S som har verdi lik x.
    * De resterende elementene på S skal ligge i uforandret rekkefølge.
    * */

    public static void removeElement(ArrayStack<Integer> stack, int x) {
        ArrayStack<Integer> tempStack = new ArrayStack<>();
        int element;

        while (!stack.isEmpty()) {
            element = stack.pop();
            if (element != x)
                tempStack.push(element);
        }
        while (!tempStack.isEmpty())
            stack.push(tempStack.pop());
    }
}
