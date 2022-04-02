import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SecondTask {

    public static void main(String[] args) {
        int amountOfNumbers = 30;

        AtomicInteger counter = new AtomicInteger();
        counter.getAndIncrement();

        CopyOnWriteArrayList<String> newList = new CopyOnWriteArrayList<>();
        Thread A = new Thread(() -> {
            while (counter.get() <= amountOfNumbers) {
                if (counter.get() % 3 == 0
                        && counter.get() % 5 != 0) {
                    newList.add("fizz");
                    counter.getAndIncrement();
                    System.out.println(newList.toString());
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
                    newList.add("buzz");
                    counter.getAndIncrement();
                    System.out.println(newList.toString());
                    System.out.println("B");
                    synchronized (A){A.notify();}


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
        Thread C = new Thread(() -> {
            while (counter.get() <= amountOfNumbers) {
                if (counter.get() % 3 == 0 &&
                        counter.get() % 5 == 0) {
                    newList.add("fizzbuzz");
                    counter.getAndIncrement();
                    System.out.println(newList.toString());
                    System.out.println("C");


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
        Thread D = new Thread(() -> {
            while (counter.get() <= amountOfNumbers) {
                if (counter.get() % 3 != 0 &&
                        counter.get() % 5 != 0) {
                    newList.add(counter.toString());
                    counter.getAndIncrement();
                    System.out.println(newList.toString());
                    System.out.println("D");


                } else {
                    synchronized (A){
                    A.notify();}
                    synchronized (B){B.notify();}
                    synchronized (C){C.notify();}
                }
            }
        });


        A.start();
        B.start();
        C.start();
        D.start();



    }
}