package jwc.debug_pro.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TestInvocationHandler implements InvocationHandler {
	private Object target;
	
	public TestInvocationHandler(Object target) {
		super();
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(String.format("method[%s] in class[%s] is invoked proxy is [%s]", method.getName(), target.getClass().getName(), proxy.getClass().getName()));
		return method.invoke(target, args);
	}

}
