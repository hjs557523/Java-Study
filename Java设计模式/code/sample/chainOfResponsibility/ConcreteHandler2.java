/**
 * 具体处理者2
 */
class ConcreteHandler2 extends AbstractHandler {
    public void handleRequest(String request) {
        if (request.equals("two")) {
            System.out.println("具体处理者2负责处理该请求！");
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