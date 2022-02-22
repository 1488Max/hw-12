class FirstTask {
    public static class ShowTimeThread extends Thread{

        @Override
        public  void run() {
            int counter = 0;
            while (true){
                counter++;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(counter%5 != 0){
                    System.out.println("Прошло " + counter  + " секунд" );

                }
            }
        }
    }

    public static class ShowFiveSeconds extends Thread{
        int counter = 0;
        @Override
        public void run() {
            while (true){
                counter++;
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(counter%5==0){
                    System.out.println("Прошло 5 секунд!!!");
            }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ShowTimeThread().start();
        new ShowFiveSeconds().start();

        }


}


