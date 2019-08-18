import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

	public static ThreadLocal<String> threadLocal = new ThreadLocal<String>();
    
	public static String baseString ="";
	
	
	public static ExecutorService  executorService=Executors.newFixedThreadPool(4);
 
	
	private static void setTLocal(String value) {
		threadLocal.set(value);
	}
 
	private static String getTlocal() {
		return "ThreadLocal保存:"+Thread.currentThread().getName()+" : "+ threadLocal.get();
	}
	
	private static void setBLocal(String value) {
		baseString=value;
	}
 
	private static String getBlocal() {
		return "Baseocal保存:   "+Thread.currentThread().getName()+" : "+baseString;
	}
	
	
	
	public static void main(String[] args) throws InterruptedException {
 
		Thread thread=new Thread(new Runnable() {
			public void run() {
				setTLocal("子线程一保存变量");
				try {
					//睡眠一秒，模拟在处理某些程序
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(getTlocal());
			}
		});
		
		Thread thread2=new Thread(new Runnable() {
			public void run() {
				setTLocal("子线程二保存变量");
				System.out.println(getTlocal());
			}
		});
		
		executorService.execute(thread);
		executorService.execute(thread2);
		
		setTLocal("主线程保存变量");
		System.out.println(getTlocal());
		
		Thread.sleep(1000);
		
		
		
		Thread thread3=new Thread(new Runnable() {
			public void run() {
				setBLocal("子线程一保存变量");
				try {
					//睡眠一秒，模拟在处理某些程序
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println(getBlocal());
			}
		});
		
		Thread thread4=new Thread(new Runnable() {
			public void run() {
				setBLocal("子线程二保存变量");
				System.err.println(getBlocal());
			}
		});
		
		executorService.execute(thread3);
		executorService.execute(thread4);
		
		setBLocal("主线程保存变量");
		System.err.println(getBlocal());	
	}	
}
