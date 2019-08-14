/**
 * 语法糖：
 * 条件编译：Java中只能用if(常量)，如果使用其他带有条件判断能力的语句 + 常量，则将被拒绝编译
 */
public class IfDef {
    public static void main(String[] args) {
        if (true) {
            System.out.println("block 1");
        } else {
            System.out.println("block 2");
        }
    }
}