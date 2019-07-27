/**
 * 堆栈追踪显示
 * 线程：main              状态：RUNNABLE           阻塞总数：0            等待总数：0
 * 等待System.in的键盘输入，Runnable状态的线程会被分配运行时间，但如果检测到流没有更新时会立刻归还执行令牌
 * 
 * 线程：testBusyThread    状态：RUNNABLE           阻塞总数：0            等待总数：0  
 * 一直执行空循环，并且没有归还线程执行令牌的动作，直到在空循环上用尽全部执行时间直到线程切换
 * 
 * 
 * 线程：testLockThread    状态：WAITING            阻塞总数：0            等待总数：1
 * 等待lock对象的notify()/notifyAll()出现，处于WAITING状态，在被唤醒前不会被分配执行时间。为活锁状态
 *  
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ThreadWaitDemo {
    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable(){
        
            @Override
            public void run() {
                while(true);
            }
        },"testBusyThread");

        thread.start();
    }


    /**
     * 线程等待演示
     */
    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable(){
        
            @Override
            public void run() {
                synchronized(lock){
                    try{
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");

        thread.start();
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        createBusyThread();
        br.readLine();
        Object obj = new Object();
        createLockThread(obj); //活锁等待中，需要lock对象的notify()或notifyAll()方法调用才能使这个线程有机会继续执行(从等待池队列进入锁池抢锁队列)
    }

}