import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SecondTask {

    public static void main(String[] args) {
        int amountOfNumbers = 30;

        AtomicInteger counter = new AtomicInteger();
        counter.getAndIncrement();

        ArrayList<String> newList = new ArrayList<>();
        Thread A = new Thread(() -> {
            while (counter.get() <= amountOfNumbers) {
                if (counter.get() % 3 == 0
                        && counter.get() % 5 != 0) {
                    synchronized (newList) {
                        newList.add("fizz");
                        System.out.println(newList.toString());
                    }
                    synchronized (counter) {
                        counter.getAndIncrement();
                    }
                    System.out.println("A");
                }


                else {
                    synchronized (Thread.currentThread()) {
                        try {
                            Thread.currentThread().wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        Thread B = new Thread(() -> {
            while (counter.get() <= amountOfNumbers) {
                if (counter.get() % 3 != 0 && counter.get() % 5 == 0) {
                    synchronized (newList) {
                        newList.add("buzz");
                        System.out.println(newList.toString());
                    }
                    synchronized (counter) {
                        counter.getAndIncrement();
                    }
                    System.out.println("B");
                    synchronized (A) {
                        A.notify();
                    }
                }
                synchronized (A) {
                    A.notify();
                }
                synchronized (Thread.currentThread()) {
                    try {
                        Thread.currentThread().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread C = new Thread(() -> {
            while (counter.get() <= amountOfNumbers) {
                if (counter.get() % 3 == 0 &&
                        counter.get() % 5 == 0) {
                    synchronized (newList) {
                        newList.add("fizzbuzz");
                        System.out.println(newList.toString());
                    }
                    synchronized (counter) {
                        counter.getAndIncrement();
                    }
                    System.out.println("C");
                    synchronized (B) {
                        B.notify();
                    }
                }
                synchronized (B) {
                    B.notify();
                }
                synchronized (Thread.currentThread()) {
                    try {
                        Thread.currentThread().wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Thread D = new Thread(() -> {
            while (counter.get() <= amountOfNumbers) {
                if (counter.get() % 3 != 0 &&
                        counter.get() % 5 != 0) {
                    synchronized (newList) {
                        newList.add(counter.toString());
                        System.out.println(newList.toString());
                    }
                    synchronized (counter) {
                        counter.getAndIncrement();
                    }

                    System.out.println("D");

                    synchronized (C) {
                        C.notify();
                    }
                }
                synchronized (C) {
                    C.notify();
                }
            }
        });
        A.start();
        B.start();
        C.start();
        D.start();
    }
}