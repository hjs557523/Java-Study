public class AutoBoxingTrap {
    public static void main(String[] args) {
        Integer a = 1;   //Integer a = Integer.valueOf(1);  赋值过程会自动装箱
        Integer b = 2;   //Integer b = Integer.valueOf(2);
        Integer c = 3;   //Integer c = Integer.valueOf(3);
        Integer d = 3;   //Integer d = Integer.valueOf(3);
        Integer e = 321; //Integer e = Integer.valueOf(321); -128~127范围内Integer的Cache，即-128~127范围内的数据Integer.valueOf()返回的是同一个对象
        Integer f = 321; //Integer f = Integer.valueOf(321);
        Long g = 3L;     //Long g = Long.valueOf(3L);
        System.out.println(c == d);          //System.out.println(c == d)  true，-128~127内 Cache，同一个对象
        System.out.println(e == f);          //false，不在-128~127内，非同一个对象
        System.out.println(c == (a + b));    //System.out.println(c.intValue() == a.intValue() + b.intValue());            true,包装类的 "==" 运算在遇到算术运算的情况下会自动拆箱
        System.out.println(c.equals(a + b)); //System.out.println(c.equals(Integer.valueOf(a.intValue() + b.intValue()));  true,equals()不处理数据转型的关系
        System.out.println(g == (a + b));    //System.out.println(g.longValue() == (long)(a.intValue() + b.intValue()));   true
        System.out.println(g.equals(a + b)); //System.out.println(g.equals(Integer.valueOf(a.intValue() + b.intValue()))); false
        
    }
}