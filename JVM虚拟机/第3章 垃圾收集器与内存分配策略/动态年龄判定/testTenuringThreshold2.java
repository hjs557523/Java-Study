/**
 * 动态对象年龄判定
 * VM参数: -verbose:gc
 *         -Xms20M、-Xmx20M、-Xmn10M
 *         -XX: +PrintGCDetails
 *         -XX: SurvivorRatio=8
 *         -XX: MaxTenuringThreshold=15 : 设置老年代的年龄阈值 15岁
 *         -XX: +PrintTenuringDistribution 
 */
public class testTenuringThreshold2 {
    private static final int _1MB = 1024 * 1024;
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        byte[] allocation1, allocation2, allocation3, allocation4;
        
        allocation1 = new byte[_1MB / 4]; //Eden 8192KB - 256KB = 7936KB
        //第1次 Minor GC: 判定为存活, survivor - 256KB = 768KB; age = 1
        
        allocation2 = new byte[_1MB / 4]; //Eden 7936KB - 256KB = 7680KB
        //第1次 Minor GC: 判定为存活, survivor - 256KB = 512KB; age = 1
        
        //第2次 Minor GC: 两个对象均判定为存活, 但这时Survivor区两个对象的总大小达到 512KB, 并且同年龄, 满足 “同年对象达到Survivor” 空间的一半规则
        //两个对象从Survivor区晋升到老年代

        allocation3 = new byte[4 * _1MB]; //Eden 7680KB - 4096KB = 3584KB
        //第1次 Minor GC: 判定为存活, 但survivor空间不足
        //直接存放到老年代

        allocation4 = new byte[4 * _1MB]; //Eden空间不足, JVM执行第1次 Minor GC 后, Eden 8192KB - 4096KB = 4096 KB

        allocation4 = null; //断开引用
        //第2次 Minor GC: 判定为回收对象, Survivor没有存活对象, 直接清理Edan和Survivor区

        allocation4 = new byte[4 * _1MB]; //Eden空间不足(实际测试不足4MB), JVM执行第2次 Minor GC 后, Eden 8192KB - 4096KB = 4096 KB
    }

}