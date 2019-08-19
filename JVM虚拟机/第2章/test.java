public class test{
    public static void main(String[] args){
  
        /* String.intern()返回引用测试 */
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);//JDK1.7: true   JDK1.6: false
        //JDK1.6 的 String.intern()会把首次遇到的 字符串实例 复制到永久代中，然后返回永久代新实例的引用
        //JDK1.7 的 String.intern()会把首次遇到的 字符串实例的引用 记录到常量池中，然后返回该引用。

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);//JDK1.7: false  JDK1.6: false
        //注："java"字符串对象在字符串常量池中已经有引用


        /* 借助CGLib使方法区出现内存溢出异常 */
        //针对JDK7及以上，由于字符串常量池不再位于永久代(方法区)内，所以需要创建大量类来产生异常
        //因此可以借助CGLib字节码技术，直接操作字节码运行时生成大量的动态类

        while(true){
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor(){
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable{
                    return proxy.invokeSuper(obj, args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject{}


    

}

