package jwc.debug_pro.testbean;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

public class ImportClassRegistrar implements ImportBeanDefinitionRegistrar {

	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(ImportClassFromRegistry.class);
		builder.addConstructorArgValue("test");
		registry.registerBeanDefinition("ImportClassFromRegistry", builder.getBeanDefinition());
	}

	public static class ImportClassFromRegistry {
		final String environment;

		public ImportClassFromRegistry(String environment) {
			super();
			this.environment = environment;
		}
	}
}
