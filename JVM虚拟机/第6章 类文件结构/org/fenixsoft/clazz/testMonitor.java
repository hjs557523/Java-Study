package org.fenixsoft.clazz;
public class testMonitor {
    public void doSomething(){};
    void onlyMe (testMonitor f){
        synchronized (f) {
            doSomething();
        }
    }
}