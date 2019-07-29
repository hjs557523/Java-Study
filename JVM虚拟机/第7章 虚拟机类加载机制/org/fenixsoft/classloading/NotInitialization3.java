/**
 * 被动引用
 * 举例3：常量在编译阶段会存在调用类的常量池中，本质上并没有直接引用到定义常量的类，
 * 因此不会触发定义常量的类的初始化，这一点需要注意。
 */
class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }
    public static final String HELLOWORD = "hello world";
}

/**
 * 非主动使用类字段演示
 */
public class NotInitialization3 {
    public static void main(String[] args) {
        System.out.println(ConstClass.HELLOWORD);
        /**
         * 输出结果: hello world
         * 没有输出"ConstClass init", 这是因为虽然在Java代码中引用了ConstClass类的常量HELLOWORD，
         * 但在编译阶段已经将此常量的值"hello world"存储到了NotInitialization3类的常量池中，以后
         * NotInitialization3对常量ConstClass.HELLOWORLD的引用实际都被转化为NotInitialization3
         * 对自身常量池的引用。也就是说NotInitialization3的Class文件之中并没有ConstClass类的符号
         * 引用入口，这两个类在编译成Class之后就不存在任何联系了。
         */
    }
}