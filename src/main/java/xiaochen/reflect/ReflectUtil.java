package xiaochen.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class ReflectUtil {



    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String clzName = Person.class.getName();
        Class<?> clz = Class.forName(clzName);
        Method[] methods = clz.getDeclaredMethods();
        Method sayMethod = null;
        String targetMethod = "say";
        for (Method method : methods){
            Parameter[] parameters = method.getParameters();
            System.out.println(method.getName() + "|" + Arrays.asList(parameters));
            if (targetMethod.equals(method.getName())){
                method.setAccessible(true);
                sayMethod = method;
                //sayMethod.invoke(clz.newInstance(), "chentk");
                sayMethod.invoke(clz.getConstructors()[0].newInstance(),"chentk");
            }
        }


    }
}
