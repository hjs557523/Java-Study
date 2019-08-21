/**
 * 外观角色
 */
class Facade {
    private SubSystem01 obj1;
    private SubSystem02 obj2;
    private SubSystem03 obj3;
    
    public Facade() {
        obj1 = new SubSystem01();
        obj2 = new SubSystem02();
        obj3 = new SubSystem03();
    }

    public void method() {
        obj1.method1();
        obj2.method2();
        obj3.method3();
    }
}