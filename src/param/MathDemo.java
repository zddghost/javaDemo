package param;

public class MathDemo {
    public static void main(String[] args) {
        add(1,2,3);
    }
    public static void add(Object... objects) {
        System.out.println("ok");
        Object a1 = objects[0];
        Object b1 = objects[1];
        int a = (int) a1;
        int b = (int) b1;
        System.out.printf("sum =" + (a + b), a + b);
    }

}
