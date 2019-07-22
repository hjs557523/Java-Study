import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args) throws Exception{
        
        //getDeclaredFields()返回Class中所有的字段，而getFields()只返回公有字段
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];//得到第一个字段:private static final sun.misc.Unsafe theUnsafe;
        

        unsafeField.setAccessible(true);//用反射前，设置允许访问该私有变量


        Unsafe unsafe = (Unsafe) unsafeField.get(null);//传入对应的实例对象获得参数。如果是static字段，则可传入任意参数包括null
        
        while(true){
            unsafe.allocateMemory(_1MB);//死循环，向操作系统申请分配1MB内存
        }
    }
}