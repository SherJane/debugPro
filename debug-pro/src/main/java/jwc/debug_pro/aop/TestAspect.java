package jwc.debug_pro.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
//@Component
public class TestAspect {

	/**
	  * 切点表达式中的特殊符号
	 *  1、* 代表0到多个任意字符，通常用作某个包下的某些类及某些方法
	 *  2、..放在方法参数中，代表任意个参数；放在包名后面表示当前包及其所有子包路径
	 *  3、+放在类名后，表示当前类及其子类；放在接口后，表示当前接口及其实现类
	 **/
	@Pointcut("execution(* run(..))")
	private void beforeAdd() {
		
	}
	
	@Before("beforeAdd()")
	public void before1() {
		System.out.println("-------before--------");
	}
	
	@After("execution(* jwc.debug_pro.aop.ProxyTargetImpl.run())")
	public void after() {
		System.out.println("-------after---------");
	}
}
