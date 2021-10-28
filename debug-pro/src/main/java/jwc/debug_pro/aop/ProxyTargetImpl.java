package jwc.debug_pro.aop;

public class ProxyTargetImpl implements IProxyTarget {

	@Override
	public void run() {
		System.out.println("run method");
	}

}
