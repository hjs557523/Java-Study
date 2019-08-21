import java.util.Observer;
public class CrudeOilFutures {
    public static void main(String[] args) {
        OilFutures oil = new OilFutures();
        Observer bull = new Bull();
        Observer bear = new Bear();
        oil.addObserver(bull);
        oil.addObserver(bear);
        oil.setPrice(10);
        oil.setPrice(-8);
    }
}