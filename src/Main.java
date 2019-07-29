import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        mathdemo();
    }

    private static void mathdemo() {
        Class<?> mathDemo = null;
        try {
            mathDemo = Class.forName("MathDemo");
            Method method = mathDemo.getDeclaredMethod("add", Object[].class);
            //强制反射
            method.setAccessible(true);
            Object o = mathDemo.newInstance();
            Object[] a = {1, 2};
            Object[] objects = new Object[]{a};
            for (Object object : objects) {
                System.err.println(object.toString());
            }
            o = method.invoke(o, objects);
        } catch (Exception e) {
            System.err.println("反射失败!");
        }
    }

    private static void mathdemo1() {
        Class<?> mathDemo = null;
        try {
            mathDemo = Class.forName("MathDemo");
            Method method = mathDemo.getDeclaredMethod("add");
            //强制反射
            method.setAccessible(true);
            Object o = mathDemo.newInstance();
            method.invoke(o);
        } catch (Exception e) {
            System.err.println("反射失败!");

        }
    }

    private static void listDemo() {
        List<String> names = new ArrayList<>();
        names.add("刘备");    //索引为0
        names.add("关羽");    //索引为1
        names.add("张飞");    //索引为2
        names.add("刘备");    //索引为3
        names.add("张飞");    //索引为4
        System.out.println(names.indexOf("刘备"));
        System.out.println(names.lastIndexOf("刘备"));
        System.out.println(names.indexOf("张飞"));
        System.out.println(names.lastIndexOf("张飞"));
        Class<? extends List> aClass = names.getClass();
        try {
            Method add = aClass.getMethod("add", Object[].class);
            add.invoke(names, 1);
            System.out.println(names);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

