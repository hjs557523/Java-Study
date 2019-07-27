/**
 * 对象优先在Eden分配
 * VM参数: -verbose:gc     查看Java垃圾收集结果
 *         -Xms20M, -Xmx20M, -Xmn10M: 3个参数限制Java堆大小为20MB不可扩展(初始化堆内存为20M, 最大为20M)，其中10MB分配给新生代，剩下10MB分配给老年代
 *         -XX: PrintGCDetails       打印GC日志信息
 *         -XX: SurvivorRatio = 8 新生代 Eden : Survivor = 8 : 1 , 即新生代可用内存为 (8+1)MB = 9216KB 
 *         
 *         GC后的最终结果： 
 *         Heap
 *         def new generation total 9216K, used 4326K [0x029d0000, 0x033d0000, 0x033d0000)
 *         eden space 8192K, 51% used [0x029d0000, 0x02de4828, 0x031d0000)
 *         from space 1024K, 14% used [0x032d0000, 0x032f5370, 0x033d0000)
 *         to space 1024K,   0% used [0x031d0000, 0x031d0000, 0x031d0000)
 * 
 *         tenured generation total 10240K, used 6144K [0x033d0000, 0x03dd0000, 0x03dd0000)
 *         the space 10240K, 60% used [0x033d0000, 0x039d0030, 0x039d0200, 0x03dd0000)
 * 
 *         compacting perm gen total 12288K, used 2114K [0x03dd0000, 0x049d0000, 0x07dd0000)
 *         the space 12288K, 17% used [0x03dd0000, 0x03fe0998, 0x03fe0a00, 0x049d0000)
 *         No shared spaces configured
 */
public class MinorGC {
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args){
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];//Eden分配 2MB, 剩余 6MB
        allocation2 = new byte[2 * _1MB];//Eden分配 2MB, 剩余 4MB
        allocation3 = new byte[2 * _1MB];//Eden分配 2MB, 剩余 2MB
        allocation4 = new byte[4 * _1MB];//Eden不足以分配 4MB, 发生一次Minor GC
        //由于GC时, allocation1、allocation2、allocation3 3个对象都是存活的(栈帧中有引用指向)，没有可回收对象。
        //而3个 2MB 的对象每一个都无法放入Survivor空间(1MB), 所以只能通过分配担保机制放入老年代里。
        
        //最后Eden占用4MB(allocation4), Survivor空闲, 老年代被占用6MB(allocation1、allocation2、allocation3)
    }

}