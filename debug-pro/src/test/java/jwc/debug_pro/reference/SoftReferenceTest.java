package jwc.debug_pro.reference;

import java.lang.ref.SoftReference;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SoftReferenceTest {
	private SoftReference<List<Object>> ref = new SoftReference<>(new LinkedList<>());
	
	public static void main(String[] args) throws SQLException {
	
	}
	
	private <T> void testSoftRef(Iterator<T> it) {
		while(it.hasNext()) {
			List<Object> list = ref.get();
			if(list == null) {
				throw new RuntimeException("太多对象，内存不够用啦");
			}else {
				list.add(it.next());
			}
			
			//这一步的作用，不置为null的话，后续循环中list作为强引用可能影响SoftReference的正常工作
			list = null;
		}
	}

}
