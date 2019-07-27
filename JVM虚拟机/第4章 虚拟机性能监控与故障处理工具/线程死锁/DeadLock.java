/**
 * 死锁代码样例
 * 这里会造成死锁主要原因在于方法Integer.valueOf()
 * Integer.valueOf(int x): 该方法会基于减少对象创建次数和节省内存的考虑，[-128, 127]之间的数字会被缓存，当
 * valueOf()方法传入参数在这个范围之内, 将直接返回缓存中的对象。如果在某个线程的两个synchronized块之间发生了
 * 一次线程切换，那就会出现线程A等着被线程B持有的Integer.valueOf(1),线程B又等着线程A持有的Integer.valueOf(2),
 * 结果两个线程都无法跑下去 
 */
public class DeadLock {
    static class SynAddRunnable implements Runnable {
        int a, b;
        public SynAddRunnable (int a, int b){
            this.a = a;
            this.b = b;
        }
        
        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                //如果这个位置进行线程切换,会发生死锁
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }
    
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            //代码调用了200次Integer.valueOf(), 一共就只返回了两个不同的对象
            new Thread(new SynAddRunnable(1, 2)).start();
            new Thread(new SynAddRunnable(2, 1)).start();
        }
    }

}