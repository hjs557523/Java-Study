/**
 * 抽象构件
 */
interface AbstractComponent {

    //透明式需要声明以下 访问和管理子类的接口
    public void add(AbstractComponent c);
    public void remove(AbstractComponent c);
    public AbstractComponent getChild(int i);


    public void operation();
    
}