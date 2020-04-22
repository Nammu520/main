package base;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxyTest {

    public static void main(String[] args) {
        ProxyCglib proxy = new ProxyCglib();
        // 通过生成子类的方法创建代理类
        TestHello test = (TestHello)proxy.getProxy(TestHello.class);
        test.say();
    }
}

// 创建被代理类
class TestHello{
    public void say() {
        System.out.println("hello");
    }
}

// 创建代理类
class ProxyCglib implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();
    public Object getProxy(Class clazz) {
        // 设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        // 通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("在调用实际方法前可以做一些事情");
        //通过代理类调用父类中的方法
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("可以在调用后做一些事情");
        return result;
    }
}