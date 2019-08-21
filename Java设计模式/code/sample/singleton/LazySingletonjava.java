/**
 * 懒汉式单例
 * 特点：类加载时没有生成单例，只有当第一次调用getInstance()方法时才去创建这个单例。
 * 对于多线程程序，必须加上关键字volatile和synchronized，否则将存在线程安全问题。
 * 由此可见每次访问时都需要同步，影响性能，消耗资源
 */
public class LazySingleton {
    
    private static volatile LazySingleton instance = null; //保证instance在所有线程中同步
    
    private LazySingleton() {} //private 避免类在外部被new实例化
    
    public static synchronized LazySingleton getInstance(){
        //getInstance()方法前加同步

        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}