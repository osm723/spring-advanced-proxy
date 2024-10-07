package hello.proxy.proxyfactory;

import hello.proxy.common.advice.TimeAdvice;
import hello.proxy.common.service.ConcreteService;
import hello.proxy.common.service.ServiceImpl;
import hello.proxy.common.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.AopUtils;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class ProxyFactoryTest {

    @Test
    @DisplayName("인터페이스 -> jdk 동적 프록시")
    void interfaceProxy() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.save();

        boolean aopProxy = AopUtils.isAopProxy(proxy);
        boolean jdkDynamicProxy = AopUtils.isJdkDynamicProxy(proxy);
        boolean cglibProxy = AopUtils.isCglibProxy(proxy);
        assertThat(aopProxy).isTrue();
        assertThat(jdkDynamicProxy).isTrue();
        assertThat(cglibProxy).isFalse();
    }

    @Test
    @DisplayName("구체클래스 -> cglib")
    void concreteProxy() {
        ConcreteService target = new ConcreteService();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.addAdvice(new TimeAdvice());
        ConcreteService proxy = (ConcreteService) proxyFactory.getProxy();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.call();

        boolean aopProxy = AopUtils.isAopProxy(proxy);
        boolean jdkDynamicProxy = AopUtils.isJdkDynamicProxy(proxy);
        boolean cglibProxy = AopUtils.isCglibProxy(proxy);
        assertThat(aopProxy).isTrue();
        assertThat(jdkDynamicProxy).isFalse();
        assertThat(cglibProxy).isTrue();
    }

    @Test
    @DisplayName("proxyTargetClass -> cglib")
    void proxyTargetClass() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice(new TimeAdvice());
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        log.info("targetClass={}", target.getClass());
        log.info("proxyClass={}", proxy.getClass());

        proxy.save();

        boolean aopProxy = AopUtils.isAopProxy(proxy);
        boolean jdkDynamicProxy = AopUtils.isJdkDynamicProxy(proxy);
        boolean cglibProxy = AopUtils.isCglibProxy(proxy);
        assertThat(aopProxy).isTrue();
        assertThat(jdkDynamicProxy).isFalse();
        assertThat(cglibProxy).isTrue();
    }
}
