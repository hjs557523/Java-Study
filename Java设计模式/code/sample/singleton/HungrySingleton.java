/**
 * 饿汉式单例
 * 类一旦加载就创建一个单例，保证在调用 getInstance 方法之前单例已经存在了
 * 线程安全，可直接用于多线程不会出现问题
 */
public class HungrySingleton {

    private static final HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {}

    public static HungrySingleton getInstance() {
        return instance;
    }
}