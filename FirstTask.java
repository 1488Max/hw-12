class FirstTask {
    public static void main(String[] args) throws InterruptedException {
        Thread eachSecond = new Thread(() -> {
            int counter = 1;
            Thread fiveSecond = new Thread(() ->{
                while (true) {
                    synchronized (Thread.currentThread()) {
                        try {
                            Thread.currentThread().wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Прошло 5 секунд!!!");
                }
            });
            fiveSecond.start();

            while (true){
                if(counter%5==0){
                    synchronized (fiveSecond){
                        fiveSecond.notify();

                    }
                }

                    System.out.println("Прошло " + counter + " секунд.");
                    counter++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }

        });

        eachSecond.start();




    }
}