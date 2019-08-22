/**
 * 客户端测试迭代器
 */
public class ClientTest {
    public static void main(String[] args) {
        AbstractAggregate ag = new ConcreteAggregate();
        ag.add("香港中文大学");
        ag.add("阿里巴巴达摩院");
        ag.add("字节跳动/抖音");
        System.out.println("聚合的内容有：");
        AbstractIterator iterator = ag.getIterator();
        while (iterator.hasNext()) {
            Object ob = iterator.next();
            System.out.println(ob.toString() + "\t");
        }

        Object ob = iterator.first();
        System.out.println("\nFitst：" + ob.toString());
    }
}