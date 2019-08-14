import java.lang.reflect.Method;

/**
 * Java Class 执行工具
 */
public class JavaClassExecuter {
    /**
     * 执行外部传过来的代表一个Java类的byte数组
     * 将输入类的byte数组中代表java.lang.System的CONSTANT_Utf8_info常量修改为劫持后的HackSystem类
     * 执行方法为该类的static main(String[] args)方法，输出结果为该类向System.out/err输出的信息
     */
    public static String execute(byte[] classByte) {
        HackSystem.clearBuffer();
        ClassModifier cm = new ClassModifier(classByte);
        byte[] modiBytes = cm.modifyUTF8Constant("java/lang/System", "org/hjs/case2/HackSystem");
        HotSwapClassLoader loader = new HotSwapClassLoader();
        Class clazz = loader.loadByte(modiBytes);
        try {
            /**
             * Class.getMethod()
             * 参数1：方法名称
             * 参数2：方法参数所属的的类对象
             */
            Method method = clazz.getMethod("main", new Class[] {String[].class});

            /**
             * Method.invoke(Objetc obj, Object... args)
             * 参数1：调用该方法的所属对象。如果底层方法是静态的，那么可以忽略指定的obj参数。该参数可以为null
             * 参数2：该方法的所有参数。如果形参数为0，则所提供的args数组长度可以为0或null new Object[] { }
             */
            method.invoke(null, new String[] { null });
        } catch (Throwable e) {
            e.printStackTrace(HackSystem.out);
        }
        return HackSystem.getBufferString();
    }
}