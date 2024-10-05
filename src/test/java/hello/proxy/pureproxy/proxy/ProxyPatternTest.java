package hello.proxy.pureproxy.proxy;

import hello.proxy.pureproxy.proxy.code.CashProxy;
import hello.proxy.pureproxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureproxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {

    @Test
    void noProxyTest() {
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest() {
        RealSubject realSubject = new RealSubject();
        CashProxy cashProxy = new CashProxy(realSubject);
        ProxyPatternClient client = new ProxyPatternClient(cashProxy);

        client.execute();
        client.execute();
        client.execute();
    }
}
