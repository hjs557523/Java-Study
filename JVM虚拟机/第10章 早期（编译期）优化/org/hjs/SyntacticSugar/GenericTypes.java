import java.util.ArrayList;
import java.util.List;

/**
 * 方法重载要求方法具备不通过的特征签名，而返回值类型是不包含在方法的特征签名之中的，所以返回值不参与重载选择
 * 但是在Class文件格式中，只要描述符不是完全一致的两个方法就可以共存。
 * 因此之所以能够编译和执行成功，是因为两个method()方法中加入了不同的返回值后才能共存于一个Class文件之中，
 */
public class GenericTypes {
    public static String method(List<String> list) {
        System.out.println("invoke method(List<String> list)");
        return "";
    }

    public static int method(List<Integer> list) {
        System.out.println("invoke method(List<Integer> list)");
        return 1;
    }

    public static void main(String[] args) {
        method(new ArrayList<String>());
        method(new ArrayList<Integer>());
    }
}