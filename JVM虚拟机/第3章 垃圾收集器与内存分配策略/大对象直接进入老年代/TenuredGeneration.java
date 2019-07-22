/**
 * 大对象直接进入老年代
 * VM参数: -verbose:gc
 *         -Xms20M、-Xmx20M、-Xmn10M -Xmn10M 
 *         -XX: +PrintGCDetails
 *         -XX: SurvivorRatio=8
 *         -XX: PretenureSizeThreshold=3145728 : 即3145728 = 3 * 1024 * 1024 B = 3MB，令大于3MB的对象直接在老年代里分配
 *         
 */
public class TenuredGeneration {
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args) {
        byte[] allocation;
        allocation = new byte[4 * _1MB]; //直接分配在老年代里
    }

}