import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class SecondTask {

    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
                "12", "13", "14", "15"));
        AtomicInteger counter = new AtomicInteger();
        CopyOnWriteArrayList<String> newList = new CopyOnWriteArrayList<>();
        Thread A = new Thread(() -> {
            while (counter.get() < strings.toArray().length) {
                if (Integer.parseInt(strings.get(counter.get())) % 3 == 0
                        && Integer.parseInt(strings.get(counter.get())) % 5 != 0) {
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
            while (counter.get() < strings.toArray().length) {
                if (Integer.parseInt(strings.get(counter.get())) % 3 != 0 && Integer.parseInt(strings.get(counter.get())) % 5 == 0) {
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
            while (counter.get() < strings.toArray().length) {
                if (Integer.parseInt(strings.get(counter.get())) % 3 == 0 &&
                        Integer.parseInt(strings.get(counter.get())) % 5 == 0) {
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
            while (counter.get() < strings.toArray().length) {
                if (Integer.parseInt(strings.get(counter.get())) % 3 != 0 &&
                        Integer.parseInt(strings.get(counter.get())) % 5 != 0) {
                    newList.add(strings.get(counter.get()));
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