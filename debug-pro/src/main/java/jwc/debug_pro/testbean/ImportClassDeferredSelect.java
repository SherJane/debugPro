package jwc.debug_pro.testbean;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class ImportClassDeferredSelect implements DeferredImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		return new String[] {ImportClassFromDeferredSelect.class.getName()};
	}
	
	public static class ImportClassFromDeferredSelect {
		
	}

}
