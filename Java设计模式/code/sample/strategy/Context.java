/**
 * 环境类
 */
class Context {
    
    //持有一个策略类的引用，最终给客户端调用
    private Strategy strategy;

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void strategyMethod() {
        strategy.strategyMethod();
    }
}