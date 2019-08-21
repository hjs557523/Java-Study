/**
 * 适配者实现
 */
class AdapteeRealize implements TwoWayAdaptee {
    
    public void specificRequest() {
        System.out.println("适配者代码被调用！");
    }

}