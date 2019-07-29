/**
 * 被动引用
 * 举例2：通过数组定义来引用类，不会触发此类的初始化
 */
public class NotInitialization2 {
    public static void main(String[] args) {
        SuperClass sca = new SuperClass(); // SuperClass init!

        //SuperClass[] sca1 = new SubClass[10]; //没有触发初始化
        
        System.out.println(sca1[0].value);
        /**
         * SuperClass init!
         * 123
         */
    }
}