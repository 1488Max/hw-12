public class SecondTask {
    public static class A extends Thread{
        public void check(int i) {
            if(i % 3 == 0 && i%5!=0){
                System.out.print(" fizz,");
            }
        }
    }
    public static class B extends Thread{
        public void check(int i) {
            if(i % 5 == 0 && i%3!=0){
                System.out.print(" buzz,");
            }
        }
    }
    public static class C extends Thread{
        public void check(int i) {
            if(i % 5 == 0 && i%3==0){
                System.out.print(" fizzbuzz,");
            }
        }
    }
    public static class D extends Thread{
        public void check(int i) {
            if(i % 5 != 0 && i%3 != 0){
                System.out.print(" " +i + ",");
            }
        }
    }

    public static void main(String[] args) {
        int n = 15;
        for (int i = 1; i <= n; i++) {
            new A().check(i);
            new B().check(i);
            new C().check(i);
            new D().check(i);
        }
    }
}
