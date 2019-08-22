/**
 * 客户端测试：访问集合c0 = {leaf1, {leaf2, leaf3}}
 * c0
 * |—— leaf1
 * |—— c1 —— leaf2
 *      | —— leaf3
 */
public class ClientTest {
    public static void main(String[] args) {
        AbstractComponent c0 = new BranchComponent();
        AbstractComponent c1 = new BranchComponent();
        AbstractComponent leaf1 = new LeafComponent("leaf1");
        AbstractComponent leaf2 = new LeafComponent("leaf2");
        AbstractComponent leaf3 = new LeafComponent("leaf3");
        c0.add(leaf1);
        c0.add(c1);
        c1.add(leaf2);
        c1.add(leaf3);
        c0.operation(); 
    }
}