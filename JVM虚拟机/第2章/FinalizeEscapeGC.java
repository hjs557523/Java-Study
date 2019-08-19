/**
 * 1、对象在一次标记后，在二次标记前会进行自我拯救
 * 2、自救机会只有一次，因为一个对象的finalize()方法最多只会被系统自动调用一次。
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;//类静态属性引用
    public void isAlive() {
        System.out.println("yes, i am still alive.");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize() executed!");
        FinalizeEscapeGC.SAVE_HOOK = this;//把自己(当前类的对象实例)赋给某个类变量，与引用链建立关联
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeEscapeGC();

        //对象第一次成功拯救自己
        SAVE_HOOK = null;
        System.gc();//执行垃圾回收

        //因为finalize方法优先级很低，所以暂停0.5s等待它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead.");
        }

        //如果对象面临下一次回收，无法再次调用finalize()，即无法自救
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no, i am dead.");
        }
    }
}