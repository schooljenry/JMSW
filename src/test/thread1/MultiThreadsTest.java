package test.thread1;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

public class MultiThreadsTest {
	private static final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	@Test
	public void multiWSReqTest() throws Exception {
		
	    final String account1 = "zhangsan";
	    final SynchronizedTest test = new SynchronizedTest();// ����Runner 
	    
	    TestRunnable runner1 = new TestRunnable() { 
	        @Override 
	        public void runTest() throws Throwable { 
	        	//SynchronizedTest test = new SynchronizedTest();
	        	Date now = new Date();
	        	test.opAccountMoney(account1 , 1000 , df.format(now));
	        } 
	    }; 
	    
	    final String account2 = "lisi";
	    TestRunnable runner2 = new TestRunnable() { 
	        @Override 
	        public void runTest() throws Throwable { 
	        	//SynchronizedTest test = new SynchronizedTest();
	        	Date now = new Date();
	        	test.opAccountMoney(account2 , 1000 , df.format(now));
	        } 
	    }; 
	
	    
	    int runnerCount = 30; 
	    //Rnner���飬�뵱�ڲ������ٸ��� 
	    TestRunnable[] trs = new TestRunnable[runnerCount]; 
	    
	    int i = 0;
	    for (; i < runnerCount; i++) {
	    	if(i%2 == 0){
	    		trs[i] = runner1; 
	    	}else{
	    		trs[i] = runner2; 
	    	}
	    } 
	    // ����ִ�ж��̲߳���������Runner����ǰ�涨��ĵ���Runner��ɵ����鴫�� 
	    MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs); 
	    try { 
	        // ��������ִ�������ﶨ������� 
	    	long start = (new Date()).getTime();
	        mttr.runTestRunnables();
	        System.out.println("����ʱ��:" + ((new Date()).getTime() - start) + " ����");
	        
	    } catch (Throwable e) { 
	        e.printStackTrace(); 
	    } 
	}
}
