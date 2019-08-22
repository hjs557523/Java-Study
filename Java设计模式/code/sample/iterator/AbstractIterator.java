/**
 * 抽象迭代器
 */
interface AbstractIterator {
    Object first();
    Object next();
    boolean hasNext();
}