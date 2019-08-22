/**
 * 指挥者：调用建造者中的方法完成复杂对象的创建
 */
class Director {
    private AbstractBuilder builder;

    public Director(AbstractBuilder builder) {
        this.builder = builder; 
    }

    //产品构建与组装方法
    public Product construct() {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
        return builder.getResult();
    }
}