package jwc.debug_pro.servletcontainerinitializer;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;

/**
 * 
 * ServletContainerInitializer的实现类通过META-INF/services/javax.servlet.ServletContainerInitializer指定，spring-web中为SpringServletContainerInitializer，由web容器进行处理
 * web容器会扫描查找所有实现@HandlesTypes指定class的类，并调用onStartup函数
 * @author chenweijun
 */
@HandlesTypes(Number.class)
public class MyServletContainerInitializer implements ServletContainerInitializer {

	public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
		System.out.println("【ServletContainerInitializer】 onStartup called 扫描到以下类");
		for(Class<?> cls : c) {
			System.out.println(cls.getName());
		}
	}

}
