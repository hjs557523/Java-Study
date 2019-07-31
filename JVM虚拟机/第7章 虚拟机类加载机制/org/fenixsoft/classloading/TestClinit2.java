public class TestClinit2 {
    static class DeadLoopClass {
        static{
            /*如果不加上这个if语句，编译器将提示“Initializer does not complete normally”并拒绝编译*/
            if (true) {
                System.out.println(Thread.currentThread() + "init DeadLoopClass");
                /**
                 * 若注销下面的while (true) {}死循环，则输出结果如下：
                 * Thread[main,5,main]first
                 * Thread[Thread-0,5,main]start
                 * Thread[Thread-1,5,main]start
                 * Thread[Thread-0,5,main]init DeadLoopClass
                 * Thread[Thread-0,5,main]run over
                 * Thread[Thread-1,5,main]run over 
                 * 
                 * 注意，因为static变量的用户赋值和static块语句，只会在类加载（初始化阶段）被执行且仅被执行一次，
                 * 所以Thread-0或Thread-1中的一个 在对DeadLoopClass的new操作后并首次执行完DeadLoopClass内的static后，
                 * 另一个new完后将不会执行里面的static
                 */

                while (true) {}
                /**
                 * 如果不注销while，则输出结果如下
                 * Thread[main,5,main]first
                 * Thread[Thread-0,5,main]start
                 * Thread[Thread-1,5,main]start
                 * Thread[Thread-0,5,main]init DeadLoopClass 
                 */
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread() + "first");
        Runnable script = new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                DeadLoopClass dlc = new DeadLoopClass();  //new关键字会触发JVM对类DeadLoopClass进行初始化
                System.out.println(Thread.currentThread() + "run over");
            }
        };

        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);
        thread1.start();
        thread2.start();
    }
}