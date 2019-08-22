/**
 * 抽象聚合
 */
interface AbstractAggregate {
    //添加
    public void add(Object obj);
    //删除
    public void remove(Object obj);
    //创建迭代器对象的接口
    public AbstractIterator getIterator();

}