package test;

import java.util.Date;

import org.junit.Test;

import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;

public class testSynchronized {

	@Test
	public void test1()  throws Exception{
		
		final Products products=new Products();
		
		TestRunnable runner1 = new TestRunnable() { 
	        @Override 
	        public void runTest() throws Throwable { 
	        	//SynchronizedTest test = new SynchronizedTest();
	        	//Date now = new Date();
	        	products.consume(products);
	        } 
	    };
	    
	    TestRunnable runner2 = new TestRunnable() { 
	    	@Override 
	    	public void runTest() throws Throwable { 
	    		//SynchronizedTest test = new SynchronizedTest();
	    		//Date now = new Date();
	    		products.produce(products);
	    	} 
	    };
		
		int runnerCount = 100; 
	    //Rnner���飬�뵱�ڲ������ٸ��� 
	    TestRunnable[] trs = new TestRunnable[runnerCount];
	    
	    int i = 0;
	    for (; i < 30; i++) {
	    		trs[i] = runner1;
	    }
	    for (i=30; i < 80; i++) {
	    	trs[i] = runner2;
	    }
	    
	    for (i=80; i < runnerCount; i++) {
	    		trs[i] = runner1;
	    }
	    
	    // ����ִ�ж��̲߳���������Runner����ǰ�涨��ĵ���Runner��ɵ����鴫�� 
	    MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs); 
	    try { 
	        // ��������ִ�������ﶨ������� 
	    	long start = (new Date()).getTime();
	        mttr.runTestRunnables();
	        System.out.println("����ʱ��:" + ((new Date()).getTime() - start) + " ����");
	        System.out.println("һ��������:" + products.getTotalProduct());
	        System.out.println("һ��ȡ��:" + products.getTotalConsume());
	    } catch (Throwable e) { 
	        e.printStackTrace(); 
	    } 
		
		//consume();
		//produce();
	}
	
	@Test
	public void test2()  throws Exception{
		
		final Products2 products=new Products2();
		
		TestRunnable runner1 = new TestRunnable() { 
			@Override 
			public void runTest() throws Throwable { 
				//SynchronizedTest test = new SynchronizedTest();
				//Date now = new Date();
				products.consume(products);
			} 
		};
		
		TestRunnable runner2 = new TestRunnable() { 
			@Override 
			public void runTest() throws Throwable { 
				//SynchronizedTest test = new SynchronizedTest();
				//Date now = new Date();
				products.produce(products);
			} 
		};
		
		int runnerCount = 100; 
		//Rnner���飬�뵱�ڲ������ٸ��� 
		TestRunnable[] trs = new TestRunnable[runnerCount];
		
		int i = 0;
		for (; i < 30; i++) {
			trs[i] = runner1;
		}
		for (i=30; i < 80; i++) {
			trs[i] = runner2;
		}
		for (i=80; i < runnerCount; i++) {
			trs[i] = runner1;
		}
		
		// ����ִ�ж��̲߳���������Runner����ǰ�涨��ĵ���Runner��ɵ����鴫�� 
		MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs); 
		try { 
			// ��������ִ�������ﶨ������� 
			long start = (new Date()).getTime();
			mttr.runTestRunnables();
			System.out.println("����ʱ��:" + ((new Date()).getTime() - start) + " ����");
			System.out.println("һ��������:" + products.getTotalProduct());
			System.out.println("һ��ȡ��:" + products.getTotalConsume());
		} catch (Throwable e) { 
			e.printStackTrace(); 
		} 
		
		//consume();
		//produce();
	}
	
	
}
