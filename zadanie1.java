// Mikołaj Stegliński 6577 D
// Zadanie 1
package zadanie1;
class MyThread extends Thread{
    private String str;
    private int cnt;
    public MyThread(String s, int c){
        str = s;
        cnt = c;
    }
    public void run() {
        try{
            for (int i = 0; i < cnt; i++){
                System.out.println("ping");
                System.out.println("pong");
                System.out.println("peng");
                sleep( (int)(Math.random() *800  +200));
            }
        }
        catch (InterruptedException ex) { }
    }
}
public class zadanie1 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("##### START #####");
        MyThread t1 = new MyThread("PING", 2);
        MyThread t2 = new MyThread("PONG", 4);
        MyThread t3 = new MyThread("PENG", 4);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("##### STOP #####");
    }
}