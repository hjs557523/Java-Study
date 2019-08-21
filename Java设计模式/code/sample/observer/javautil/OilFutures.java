import java.util.Observable;

/**
 * 具体目标类：原油期货
 */
class OilFutures extends Observable {
    private float price;

    public void setPrice(float price){
        this.price = price;
        super.setChanged(); //设置内部标志位，注明数据发生变化----先
        super.notifyObservers(price);  //通知观察者价格改变了----后
    }

    public float getPrice() {
        return this.price;
    }
}