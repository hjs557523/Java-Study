/**
 * 类适配器
 */
class ClassAdapter extends Adaptee implements Target {

    public void request() {

        specificRequest();
        
    }
}