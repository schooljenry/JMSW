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
	    //Rnner数组，想当于并发多少个。 
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
	    
	    // 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入 
	    MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs); 
	    try { 
	        // 开发并发执行数组里定义的内容 
	    	long start = (new Date()).getTime();
	        mttr.runTestRunnables();
	        System.out.println("花费时间:" + ((new Date()).getTime() - start) + " 毫秒");
	        System.out.println("一共生产了:" + products.getTotalProduct());
	        System.out.println("一共取了:" + products.getTotalConsume());
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
		//Rnner数组，想当于并发多少个。 
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
		
		// 用于执行多线程测试用例的Runner，将前面定义的单个Runner组成的数组传入 
		MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs); 
		try { 
			// 开发并发执行数组里定义的内容 
			long start = (new Date()).getTime();
			mttr.runTestRunnables();
			System.out.println("花费时间:" + ((new Date()).getTime() - start) + " 毫秒");
			System.out.println("一共生产了:" + products.getTotalProduct());
			System.out.println("一共取了:" + products.getTotalConsume());
		} catch (Throwable e) { 
			e.printStackTrace(); 
		} 
		
		//consume();
		//produce();
	}
	
	
}
