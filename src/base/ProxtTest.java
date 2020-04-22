package base;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxtTest {

    public static void main(String[] args) {
        // 1.创建被代理对象
        Test test = new Test();
        // 2.创建handler,传入真实对象
        ProxyHandler handler = new ProxyHandler(test);
        // 3.创建代理对象,传入类加载器,接口,handler
        TestInterface testProxy = (TestInterface) Proxy.newProxyInstance(
                TestInterface.class.getClassLoader(), new Class[]{TestInterface.class}, handler);
        // 4.调用方法
        testProxy.sayHello();
    }
}
// 接口类
interface TestInterface {
    void sayHello();
}
// 被代理类,实现接口
class Test implements TestInterface
{
    @Override
    public void sayHello() {
        System.out.println("hello");
    }
}
/**
 * 创建InvocationHandler实现类
 *
 * 每次动态代理类对象时,都需要指定一个实现了InvocationHandler接口的调用处理器对象
 */
class ProxyHandler implements InvocationHandler {
    private Object subject; // 要代理的真实对象

    public ProxyHandler(Object subject) {
        this.subject = subject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("在调用实际方法前可以做一些事情");
        System.out.println("当前调用的方法:" + method.getName());
        result = method.invoke(subject, args); // 指定代理对象和传入参数
        System.out.println(method.getName() + "方法的返回值是" + result);
        System.out.println("可以在调用后做一些事情");
        return result;
    }
}