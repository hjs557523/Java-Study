import java.io.*;
/**
 * 自定义ClassLoader：
 * 因为系统的ClassLoader只会加载指定目录下的class文件，如果想加载自己的class文件，就必须自定义一个ClassLoader
 * 自定义ClassLoader需要完成以下步骤：
 * (1) 继承java.lang.ClassLoader类，或匿名内部类实现java.lang.ClassLoader
 * (2) 重写loaderClass()方法或findClass()方法，findClass()在加载器的loaderClass()内进行调用
 * 
 * 
 * loadClass(): 实现双亲委派模型来进行类加载，内部调用findClass()将字节码加载到内存中。
 *              (1) 调用findLoadedClass(String)查看某个Class是否已经被加载 
 *              (2) 如果没有被加载，则查看父类加载器是否为空，不为空则向上提交请求，递归调用父类加载器的LoadClass()
 *              (3) 直到父类加载器为null，说明父类加载器是启动类加载器，查找对应的Class
 *              如果要违背双亲委派模型，就需要开发者重写loadClass()和findClass()。
 *              
 * findClass(): 找到class文件并把字节码加载到内存中。这个方法在ClassLoader中并未实现，所以开发者自定义ClassLoader时，
 *              一般会覆盖这个方法，以便使用不同形式的资源加载路径
 * 
 * defineClass(): 将字节码转化为Class对象
 * 
 * 
 * 
 * 双亲委派模型思想：
 * 子类先检查自己是否加载过该类，如果没有，先委托父类加载（递归）
 * 每个父类加载器有自己的加载范围，范围内没有找到（不符合加载范围的要求？），则拒绝加载，并返回给子类（这句话是重点）
 * 子类在收到父类无法加载的时候，才会自己去加载
 * 
 * 三种类加载器：
 * 
 * （1）启动类加载器（Bootstrap ClassLoader）：
 * C++实现，为JVM内核一部分。java里无法获取（所以用java代码获取Extension ClassLoader的父加载器会返回null），负责加载/lib下的类,即核心类库
 * JVM启动时，Bootstrap也随之启动，负责加载完核心类库后，并构造Extension ClassLoader和Application ClassLoader
 * 
 * （2）扩展类加载器（Extension ClassLoader）：
 * Java实现，可以在Java里获取，负责加载/lib/ext下的类
 * 
 * （3）应用程序类加载器（Application ClassLoader）：
 * 负责加载Classpath（用户类路径）的所有类，如果用户没有自定义类加载器，则所有用户应用程序默认使用它进行类加载
 * 
 */

 /**
  * 自定义ClassLoader
  * 不同类加载器对instanceof关键字运算结果的影响（用户自定义类 vs 应用程序类）
  */
 public class ClassLoaderTest {
     public static void main(String[] args) throws Exception {

        ClassLoader myLoader = new ClassLoader() {

            public Class<?> loaderClass(String name) throws ClassNotFoundException {
                try {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                    InputStream is = getClass().getResourceAsStream(fileName);
                    /**
                     * Java中获取资源有以下两种方式：
                     * (1) Class.getResource(String path)
                     *     path不以'/'开头时, 默认是从此类所在的包下取资源
                     *     path以'/'开头时, 则是从项目的ClassPath根下获取资源。在这里'/'表示ClassPath的根目录
                     * 
                     * (2) Class.getClassLoader().getResource(String path)
                     *     path不能以'/'开头, path是指类加载器的加载范围, 在资源加载过程中，使用逐级向上委托的形式加载
                     * 
                     * 
                     * (3) Class.getResourceAsStream(String path)
                     * 
                     * (4) Class.getResourceAsStream(String path): 默认是从ClassPath根下获取，
                     */
                    if (is == null) {
                        return super.loadClass(name);//调用父类双亲委派模型来完成
                    }
                    byte[] b = new byte[is.available()];
                    is.read(b);
                    return defineClass(name, b, 0, b.length);//创建类对象，将字节流解析成JVM能够识别的Class对象
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