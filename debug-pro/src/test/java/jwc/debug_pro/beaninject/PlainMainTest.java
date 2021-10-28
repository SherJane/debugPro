package jwc.debug_pro.beaninject;

import java.lang.reflect.Proxy;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;

import jwc.debug_pro.aop.IProxyTarget;
import jwc.debug_pro.aop.LogMethodBeforeAdvice;
import jwc.debug_pro.aop.ProxyTargetImpl;
import jwc.debug_pro.aop.TargetForCglib;
import jwc.debug_pro.aop.TestAspect;
import jwc.debug_pro.aop.TestInvocationHandler;

public class PlainMainTest {

	public static void main(String[] args) {
		//testProxyFactory();
		//testCglib();
		testAspect();
	}

	public static void testJdkProxy() {
		IProxyTarget target = (IProxyTarget)Proxy.newProxyInstance(PlainMainTest.class.getClassLoader(), new Class<?>[] {IProxyTarget.class}, new TestInvocationHandler(new ProxyTargetImpl()));
		target.run();
	}
	
	public static void testProxyFactory() {
		ProxyFactory proxyFactory = new ProxyFactory(new ProxyTargetImpl());
		proxyFactory.addAdvice(new LogMethodBeforeAdvice());
		IProxyTarget target = (IProxyTarget)proxyFactory.getProxy();
		target.run();
		target.toString();
	}
	
	public static void testCglib() {
		ProxyFactory proxyFactory = new ProxyFactory(new TargetForCglib());
		proxyFactory.addAdvice(new LogMethodBeforeAdvice());
		TargetForCglib target = (TargetForCglib)proxyFactory.getProxy();
		target.run();
	}
	
	public static void testAspect() {
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory(new ProxyTargetImpl());
		proxyFactory.addAspect(TestAspect.class);
		IProxyTarget target = proxyFactory.getProxy();
		target.run();
	}
}
