package jwc.debug_pro.testbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassB {
	@Autowired
	private ClassA a;
	
	public ClassA getA() {
		return a;
	}
}
