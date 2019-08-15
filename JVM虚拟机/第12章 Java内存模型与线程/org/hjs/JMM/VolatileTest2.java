/**
 * 以下场景很适合使用volatile变量来控制并发，当shutdown()方法被调用时，
 * 能保证所有线程中执行的doWork()方法都立即停下来。
 */
public class VolatileTest2 {
    volatile boolean shutdownRequested;

    public void shutdown() {
        shutdownRequested = true;
    }

    public void doWork() {
        while(!shutdownRequested) {
            //do stuff
        }
    }
}