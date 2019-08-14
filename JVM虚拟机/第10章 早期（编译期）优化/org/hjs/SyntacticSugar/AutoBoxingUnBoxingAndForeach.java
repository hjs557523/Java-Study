import java.util.Arrays;
import java.util.List;

public class AutoBoxingUnBoxingAndForeach {
    public static void main(String[] args) {
        // 将数组转化为List
        List<Integer> list = Arrays.asList(1, 2, 3, 4);//泛型 + 自动装箱(Integer)
        // 如果在JDK1.8中，还有另外一颗语法糖
        // 能让这句代码简写成: List<Integer> list = [1, 2, 3, 4];
        int sum = 0;
        for (int i : list) {//遍历循环 + 自动拆箱
            sum += i;
        }
        System.out.println(sum);
    }

}