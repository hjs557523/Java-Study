/**
 * 具体处理者1
 */
class ConcreteHandler1 extends AbstractHandler {
    public void handleRequest(String request) {
        if (request.equals("one")) {
            System.out.println("具体处理者1负责处理该请求！");
        }
        else {
            if (getNext() != null){
                getNext().handleRequest(request);
            }
            else {
                System.out.println("无法找到合适的处理对象来处理该请求！");
            }
        }
    }
}