public class MathDemo {
    /*    public void add(int a,int b){
            System.out.println("ok");
            System.out.printf("sum =" + a + "+" + b, a + b);
        }*/
    public void add(Object... objects) {
        System.out.println("ok");
        Object a1 = objects[0];
        Object b1 = objects[1];
        int a = (int) a1;
        int b = (int) b1;
        System.out.printf("sum =" + (a + b), a + b);
    }

    /*public void add() {
        System.out.println("ok");
        int a = 1;
        int b = 1;
        System.out.printf("sum =" + a + "+" + b, a + b);
    }*/
}
