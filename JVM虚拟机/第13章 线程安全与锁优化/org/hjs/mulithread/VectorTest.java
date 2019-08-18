/**
 * Java API标注自己是线程安全的类，大多数都不是绝对的线程安全
 * 比如java.util.Vector是一个线程安全的容器，因为它的所有方法都被修饰成同步，
 * 像add()、get()、size()这类方法都是被synchronized修饰的，但这并不意味着调用它
 * 的时候都不再需要同步手段；
 *
 * 如下，必须加入同步以保证Vector访问的线程安全性
 */
import java.util.Vector;

public class VectorTest {
    private static Vector<Integer> vector = new Vector<Integer>();

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread removeThread = new Thread(new Runnable(){
            
                @Override
                public void run() {
                    synchronized (vector) {
                        for (int i = 0; i < vector.size(); i++) {
                            vector.remove(i);
                        }
                    }
                }
            });

            Thread printThread = new Thread(new Runnable(){
            
                @Override
                public void run() {
                    synchronized (vector) {
                        for (int i = 0; i < vector.size(); i++) {
                            System.out.println(vector.get(i));
                        }
                    }   
                }
            });

            synchronized(this){

            }

            removeThread.start();
            printThread.start();

            //不要同时产生过多的线程，否则会导致操作系统假死
            while (Thread.activeCount() > 20); 
        }
    }
}