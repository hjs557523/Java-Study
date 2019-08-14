import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * AutoBoxingUnBoxingAndForeach.java编译后的变化如下：
 */
public class AutoBoxingUnBoxingAndForeach2 {
    public static void main(String[] args) {
        List list = Arrays.asList( new Integer[] {
            Integer.valueOf(1), 
            Integer.valueOf(2), 
            Integer.valueOf(3), 
            Integer.valueOf(4)
        });
        
        int sum = 0;
        /**
         * 老方法迭代器取元素
         */
        for (Iterator localIterator = list.iterator() ; localIterator.hasNext(); ) {
            int i = ((Integer) localIterator.next()).intValue();
            sum += i;
        }

        System.out.println(sum);
    }
}