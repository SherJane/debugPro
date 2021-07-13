package jwc.debug_pro.beaninject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import jwc.debug_pro.contexthierarchy.MyWebAppInitializer;
import jwc.debug_pro.testbean.ClassA;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyWebAppInitializer.class})
@WebAppConfiguration
public class TestBeanInject {
	
	@Autowired
	private ApplicationContext context;
	
	@Test
	public void test() {
		ClassA ca = context.getBean(ClassA.class);
		ca.test();
	}

}
