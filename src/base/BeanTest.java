package base;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * BeanTest
 *
 * @author dengyu
 * @date 2020/4/23
 * @since 1.0.0
 */
@Component
public class BeanTest implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean {

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanTest.setBeanFactory invoke");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanTest.setBeanName invoke");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("BeanTest.destory invoke");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("BeanTest.afterPropertiesSet invoke");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("BeanTest.setApplicationContext invoke");
    }

    // 自定义初始化方法
    @PostConstruct
    public void springPostConstruct(){
        System.out.println("@PostConstruct");
    }

    // 自定义销毁方法
    @PreDestroy
    public void springPreDestory(){
        System.out.println("@PreDestroy");
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("--------inside finalize----------");
        super.finalize();
    }

//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        if()
//        return bean;
//    }
//
//    @Override
//    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
//        return bean;
//    }
}
