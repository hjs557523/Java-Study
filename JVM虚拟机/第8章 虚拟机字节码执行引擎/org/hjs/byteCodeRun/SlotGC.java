/**
 * placeholder能否被回收的根本原因：
 * 局部变量表中的Slot是否还存在有关于placeholder数组对象的引用。虽然placeholder的作用域在代码块{}
 * 中，但是后面如果没有对局部变量表的读写操作，则placeholder原本所占有的Slot就不会被其它变量所复用，
 * 所以作为GC Roots一部分的局部变量表仍然保持着对它的关联。因此将会导致此方法的栈帧和对象在堆区的内
 * 存长时间不能被回收。因此Java编程中有一条推荐的编码规则：“不使用的对象应手动赋值为null”。而实际上，
 * 以恰当的变量作用域来控制变量回收时间才是最好的解决方法，因为有时候赋null值的操作在经过JIT编译优化
 * 后就会被消除掉，因此这时候将变量设置为null值没有意义。
 * 
 */
public class SlotGC {
    public static void main(String[] args) {
        {
            byte[] placeholder = new byte[64 * 1024 * 1024];
        }
        //int a = 0;
        System.gc();//若注释掉"int a = 0;" ，则64MB的placeholder将不会被回收
    }
}