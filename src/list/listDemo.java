package list;

public class listDemo {
    public static void main(String[] args) {
        mathAdd();
    }
    public static void StringTest(){
        StringBuffer stringBuffer=new StringBuffer();
        String s="1111";
        stringBuffer.append(s);
    }
    private static void mathAdd() {
        double n = 15;
        double sum = 0.0;
        while (n > 0) {
            sum = sum + 1 / n;
            n--;
        }
        System.out.println(sum);
    }

    private static void getMaxIndex() {
        int[] a = {8, 16, 20, 33, 2};
        int i = maxIndex(a);
        System.out.println(i);
    }

    private static void listTest() {
        int[] a = {8, 4, 2};
        int[] add = add(a);
        for (int i : add) {
            System.out.println(i);
        }
    }

    public static int[] add(int[] a) {
        int[] b = new int[3];
        for (int i = 0; i < a.length; i++) {
            b[i] = a[i] / 2;
        }
        return b;
    }

    public static int maxIndex(int[] a) {
        int j = 0;
        int max = a[0];
        for (int i = 1; i < a.length; i++) {
            if (a[i] > max) {
                max = a[i];
                j = i;
            }
        }
        return j;
    }
}
