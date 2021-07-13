package jwc.debug_pro.contexthierarchy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 父容器配置,扫描基础组件
 * @author chenweijun
 *
 */
@Configuration
@ComponentScan(value = "jwc.debug_pro",excludeFilters = {@Filter(type=FilterType.ANNOTATION,classes = {Controller.class,ControllerAdvice.class, RestControllerAdvice.class})})
public class RootConfig {

}
