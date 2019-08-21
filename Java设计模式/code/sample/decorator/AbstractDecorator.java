/**
 * 抽象装饰
 */
class AbstractDecorator implements AbstractComponent {

    private AbstractComponent component;

    public AbstractDecorator(AbstractComponent component) {
        this.component = component;
    }
    
    public void operation() {
        component.operation();
    }
}