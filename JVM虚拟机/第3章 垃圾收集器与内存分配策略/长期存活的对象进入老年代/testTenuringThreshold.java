/**
 * 什么时候进入老年代取决于 MaxTenuringThreshold 设置
 * VM参数: -verbose:gc
 *         -Xms20M、-Xmx20M、-Xmn10M
 *         -XX: +PrintGCDetails
 *         -XX: SurvivorRatio=8
 *         -XX: MaxTenuringThreshold=1 : 设置老年代的年龄阈值 1岁
 *         -XX: +PrintTenuringDistribution 
 */
public class testTenuringThreshold {
    private static final int _1MB = 1024 * 1024;

    @SuppressWarnings("unused")
    public static void main(){
        byte[] allocation1, allocation2, allocation3;
        //(8+1)MB = 9216KB的新生代可用空间
        
        allocation1 = new byte[_1MB / 4]; // (1) Eden分配 256KB(实际测试多于256KB), 剩余 7 MB + 768 KB 
        //(4) 第一次Minor GC: 
        //    判定 allocation1 存活, 256KB < Survivor空间(1 MB), 可以容纳; 剩余 Survivor = 768 KB, 设置 age = 1;
        //(8) 第二次Minor GC时: 
        //    判定 allocation1 存活, 但由于MaxTenuringThreshold=1, 而此时 age = 1, 直接进入老年代, 老年代剩余 6MB - 256KB = 5MB + 768KB = 5888KB; Survivor区为空。


        allocation2 = new byte[4 * _1MB]; // (2) Eden分配 4MB, 剩余 3MB + 768 KB
        //(5) 第一次Minor GC: 
        //    判定 allocation2 存活, 4MB > Survivor空间(768 KB), 无法容纳; 直接进入老年代, 老年代剩余 10MB - 4MB = 6MB
        //(9) 第二次Minor GC时: 
        //    allocation2 已位于老年代, 老年代剩余 5888 KB

        allocation3 = new byte[4 * _1MB]; // (3) 剩余的Eden区不够分配 4MB，JVM触发第一次 Minor GC完成后, Eden区剩余 8MB - 4MB = 4MB
        //(10) 第二次Minor GC时: 
        //     判定原 allocation3 为回收对象, 但此时Edan区和Survivor区已经没有存活对象, 直接清理Edan区和Survivor区空间

        allocation3 = null;// (6) 断开allocation3的对象引用 (注:此时4MB的原 allocation3对象仍在Eden区, Eden区剩余 8MB - 4MB = 4MB)


        allocation3 = new byte[4 * _1MB]; // (7) 再次创建对象，此时Eden区剩余 4MB(实际不足4MB), JVM触发第二次 Minor GC完成后, Eden区剩余 4MB + 4MB = 8MB, 再分配4MB创建对象, 剩余 8MB - 4MB = 4MB 
    
        
    }    
}