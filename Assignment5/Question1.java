public class Question1 extends Thread {

    public void run() {
        for(int i=1; i<=100; i++) {
            System.out.print("\nCurrent Count: "+ i);

            if(i%10==0 && i!=100) {
                System.out.println("\nA Interval of 10 Finished here\n");
            }
            else if(i==100) {
                System.out.println("\nCounting Ends here");
            }
            try {
                Thread.sleep(1000);
            }catch(Exception e) {
            }
        }
    }
    public static void main(String[] args) {
        Question1 t1 = new Question1();

        t1.start();
    }
}
