package jwc.debug_pro.util;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

public class RunTimeAnalyzer {
	private MemoryMXBean memoryMXBean;
	
	public RunTimeAnalyzer() {
		this.memoryMXBean = ManagementFactory.getMemoryMXBean();
	}
	
	public void outputSummary() {
		MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
		System.out.println(String.format("TotalMemory=%sM", memoryUsage.getInit() / 1024/ 1024));
		System.out.println(String.format("MaxMemory=%sM", memoryUsage.getMax() / 1024/ 1024));
	}
	
	public void outputUsed() {
		MemoryUsage memoryUsage = memoryMXBean.getHeapMemoryUsage();
		System.out.println(String.format("UsedMemory=%sM", memoryUsage.getUsed() / 1024/ 1024));
		//System.out.println(String.format("FreeMemory=%sM", (memoryUsage.getInit() - memoryUsage.getUsed()) / 1024/ 1024));
	}
}
