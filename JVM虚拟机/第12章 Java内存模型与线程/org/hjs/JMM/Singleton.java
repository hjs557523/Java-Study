/**
 * volatile关键字禁止指令重排序优化
 * DCL单例模式代码
 */
public class Singleton {

    private volatile static Singleton instance;

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) { 
                //synchronized(*.class) 类锁，只要任意一个线程要访问这个类的方法，就会同步。因为所有该类的实例共用同一把锁。
                //static synchronized
                if (instance == null) {
                    instance = new Singleton();   
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }

}