import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建动态代理对象
 * 动态代理不需要实现接口，但是需要指定接口类型
 */
public class ProxyFactory{

    //维护一个目标对象
    private Object target;

    public ProxyFactory(Object target){
        this.target = target;
    }


    //给目标对象生成代理对象
    public Object getProxyInstance(){
        return Proxy.newProxyInstance(
            target.getClass().getClassLoader(), //获得该对象的类装载器: .class文件 --> JVM上的Class对象
            target.getClass().getInterfaces(),  //获得该对象所实现的所有接口
            new InvocationHandler(){            //匿名类实现 InvocationHandler 接口
            
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    
                    //在这里写新增的业务逻辑代码
                    //......

                    System.out.println("开始事务2");

                    //执行目标对象方法
                    Object returnValue = method.invoke(target, args);
                    
                    System.out.println("提交事务2");
                    
                    return returnValue; 

                }
            }
        );
    }
}