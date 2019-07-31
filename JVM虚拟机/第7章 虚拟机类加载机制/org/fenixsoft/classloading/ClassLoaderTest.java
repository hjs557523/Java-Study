import java.io.*;
/**
 * 不同类加载器对instanceof关键字运算结果的影响
 * 
 * 自定义ClassLoader：
 * 因为系统的ClassLoader只会加载指定目录下的class文件，如果想加载自己的class文件，就必须自定义一个ClassLoader
 * 自定义ClassLoader需要完成以下步骤：
 * (1) 继承java.lang.ClassLoader类，或匿名内部类实现java.lang.ClassLoader
 * (2) 重写loaderClass()方法或findClass()方法，findClass()会在调用加载器的loaderClass()
 * 
 * 
 * 重写loadClass()方法和重写findClass()方法的区别
 */

 public class ClassLoaderTest {
     public static void main(String[] args) throws Exception {

        ClassLoader myLoader = new ClassLoader() {

            public Class<?> loaderClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    if (is == null) {
                        return super.loadClass(name);
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);
                } catch (IOException e) {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myLoader.loadClass("org.fenixsoft.classloading.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof org.fenixsoft.classloading.ClassLoaderTest);
     }
 }