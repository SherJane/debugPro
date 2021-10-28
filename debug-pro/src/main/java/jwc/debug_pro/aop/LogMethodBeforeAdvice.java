package jwc.debug_pro.aop;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class LogMethodBeforeAdvice implements MethodBeforeAdvice {

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		System.out.println("this is advice before method");
		System.out.println(String.format("method={%s}", method.getName()));
		System.out.println(String.format("args={%s}", args == null || args.length == 0 ? "" : args));
		System.out.println(String.format("target={%s}", target.getClass().getName()));
	}

}
