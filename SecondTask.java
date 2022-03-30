import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondTask {
     static boolean isDigit(String string) {
        char[] chars = string.toCharArray();

        for (char character : chars) {
            if (Character.isDigit(character)) {
                return true;
            }
        }

        return false;
    }
    public static void main(String[] args) throws InterruptedException {
        List<String> strings = new ArrayList(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
                "12", "13", "14", "15"));
        class A extends Thread {
            public synchronized void run() {
                for (int i = 0; i < strings.size(); i++) {
                    if(isDigit(strings.get(i))) {
                        if (Integer.parseInt(strings.get(i)) % 3 == 0 && Integer.parseInt(strings.get(i)) % 5 != 0) {
                            strings.set(i, " fizz");
                        }
                    }
                }


            }
        }
        class B extends Thread {
            public synchronized void run()  {
                for (int i = 0; i < strings.size(); i++) {
                    if(isDigit(strings.get(i))) {
                        if (Integer.parseInt(strings.get(i)) % 3 != 0 && Integer.parseInt(strings.get(i)) % 5 == 0) {
                            strings.set(i, " buzz");
                        }
                    }
                }


            }
        }
        class C extends Thread {
            public synchronized void run() {
                for (int i = 0; i < strings.size(); i++) {
                    if(isDigit(strings.get(i))) {
                        if (Integer.parseInt(strings.get(i)) % 3 == 0 && Integer.parseInt(strings.get(i)) % 5 == 0) {
                            strings.set(i, " fizzbuzz");
                        }
                    }
                }


            }
        }
        class D extends Thread {
            public synchronized void run() {
                for (int i = 0; i < strings.size(); i++) {
                    if(isDigit(strings.get(i))) {
                        if (Integer.parseInt(strings.get(i)) % 3 != 0 && Integer.parseInt(strings.get(i)) % 5 != 0) {

                        }
                    }
                }


            }
        }
                new A().start();
                new B().start();
                new C().start();
                new D().start();
        System.out.println(strings.toString());


    }
}
