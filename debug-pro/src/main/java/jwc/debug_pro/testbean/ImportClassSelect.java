package jwc.debug_pro.testbean;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class ImportClassSelect implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[] {ImportClassFromSelect.class.getName()};
	}

	public static class ImportClassFromSelect {
		
	}
}
