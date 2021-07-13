package jwc.debug_pro.contexthierarchy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 子容器配置，包含controller组件
 * @author chenweijun
 *
 */
@ComponentScan(value = "jwc.debug_pro", useDefaultFilters = false,
includeFilters = {@Filter(type = FilterType.ANNOTATION, classes = {Controller.class, ControllerAdvice.class, RestControllerAdvice.class})}
)
@Configuration
public class AppConfig {

}
