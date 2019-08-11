/**
 * 单分派、多分派演示
 * 目标方法的选择过程如下：
 * 1、先进行静态分派（编译阶段的编译器的选择）
 * 依据：(1) 方法接受者的静态类型是Father还是Son  (2) 方法参数是QQ还是_360
 * 产物：两条invokevirtual指令，参数分别为常量池中指向Father.hardChoice(_360)及Father.hardChoice(QQ)方法的符号引用；
 *      因为此时是根据两个宗量（方法的接收者和方法的参数）进行选择，所以Java语言的静态分派属于多分派类型。
 * 
 * 2、接着进行动态分派（运行阶段虚拟机的选择）
 * 依据：(1) 方法接收者的实际类型是Father还是Son
 * 产物：对应执行目标方法为：Father.hardChoice(_360) 和 Son.hardChoice(QQ)
 *      因为此时只有一个宗量作为选择依据，所以Java语言的动态分派属于单分派类型。
 * 
 * 所以Java是一门静态多分派、动态单分配的语言
 */ 
public class Dispatch {
    static class QQ {}
    static class _360 {}
    public static class Father {
        public void hardChoice(QQ arg) {
            System.out.println("father choose QQ");
        }
        public void hardChoice(_360 arg) {
            System.out.println("father choose 360");
        }
    }

    public static class Son extends Father {
        public void hardChoice(QQ arg) {
            System.out.println("son choose QQ");
        }
        public void hardChoice(_360 arg) {
            System.out.println("son choose 360");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());//father choose 360
        son.hardChoice(new QQ());//son choose QQ
    }
}