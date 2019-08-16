/**
 * volatile变量自增运算测试
 */
public class VolatileTest {

    public static volatile int race = 0;

    public static void increase() {
        race++;
        //0: getstatic    取race值到操作栈顶，volatile关键字保证race的值在此时是正确的
        //3: iconst_1     从此处开始,其他线程可能已经把race的值加大了，而在操作栈顶的值就变成了过期的数据
        //4: idd
        //5: putstatic    可能把较小的race值同步回主内存之中,即写回操作
        //8: return
    } 

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread( new Runnable(){
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        increase();
                    }
                }
            });

            threads[i].start();
        }

        //等待所有累加线程都结束
        while (Thread.activeCount() > 1) {// Thread.activeCount()方法：返回当前线程的线程组中活动线程的数量
            Thread.yield();
            //Thread.yield()方法: 当前线程让步。
            //即轮到该线程获得CPU的执行时间时，由“运行状态”进入到“就绪状态”，让出自己CPU执行的时间
            //并重新进入和其他线程争夺CPU执行权的活动中去
            //即让自己或其他等待线程获取执行权。
        }

        System.out.println(race);

    }
}