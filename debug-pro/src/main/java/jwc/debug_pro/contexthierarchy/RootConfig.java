package jwc.debug_pro.contexthierarchy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jwc.debug_pro.testbean.BeanAnnotatedClass;
import jwc.debug_pro.testbean.ImportClass;
import jwc.debug_pro.testbean.ImportClassDeferredSelect;
import jwc.debug_pro.testbean.ImportClassRegistrar.ImportClassFromRegistry;

/**
 * 父容器配置,扫描基础组件
 * @author chenweijun
 *
 */
@Configuration
@ComponentScan(value = "jwc.debug_pro",excludeFilters = {@Filter(type=FilterType.ANNOTATION,classes = {Controller.class,ControllerAdvice.class, RestControllerAdvice.class}),
                                                         @Filter(type=FilterType.ASSIGNABLE_TYPE, classes = {AppConfig.class})})
@PropertySource(value = "classpath:test.properties", name="env-test")
@Import(value= {ImportClass.class, ImportClassFromRegistry.class, ImportClassDeferredSelect.class})
public class RootConfig {
	@Value("${name}")
	private String name;

	public String getName() {
		return name;
	}
	
	@Bean
	public BeanAnnotatedClass beanClass() {
		return new BeanAnnotatedClass();
	}
	
	@Configuration
	public static class RootInnerConfig {
		
	}
}
