/**
 * 目标对象实现接口
 */
public class UserDao implements IUserDao{
    public void save(){
        System.out.println("成功保存数据！");
    }
}
