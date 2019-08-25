/**
 * 初始化：
 * 父类的<clinit>()方法先执行，所以父类中定义的静态语句块优先于子类的静态变量赋值操作
 * 
 */
public class TestClinit {
    /**
     * static用户赋值和语句块的执行，只在类加载（初始化阶段）的时候被执行且仅被执行一次
     */
    static class Parent {
        public static int A = 1; //(1)
        public static final int ex = 16041321;
        static {
            A = 2; //(2)
        }
    
    }

    static class Sub extends Parent {
        public static int B = A; //(3)
    }

    public static void main(String[] args) {
        //Sub.B  读取类Sub的静态字段，会触发JVM对类Sub进行初始化
        System.out.println(Sub.B); //result: 2
        System.out.println(Sub.ex);
    }
}