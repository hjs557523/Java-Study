import java.util.Observable;
import java.util.Observer;
/**
 * 具体观察者：空方
 */
class Bear implements Observer {

    public void update(Observable o, Object arg) {
        float price = ((Float) arg).floatValue();
        if (price > 0) {
            System.out.println("油价上涨" + price + "元，空方伤心了！");
        }
        else {
            System.out.println("油价下跌" + (-price) + "元，空方高兴了！");
        }
    }
}