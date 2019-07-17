/**
 * 测试类
 */
public class App{
    public static void main(String[] args){
        //目标对象
        IUserDao target = new UserDao();//class UserDao

        //原始的类型
        System.out.println(target.getClass());

        //给目标对象，创建代理对象
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();

        //内存中动态生成的代理对象
        System.out.println(proxy.getClass());//class com.sun.proxy.$Proxy0

        //执行方法
        proxy.save();
    }
}