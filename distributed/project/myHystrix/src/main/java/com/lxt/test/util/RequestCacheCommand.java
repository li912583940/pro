package com.lxt.test.util;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;

import junit.framework.Assert;

public class RequestCacheCommand extends HystrixCommand<String>{

	private final int id;
	
	/**
	 * @param setter
	 */
	protected RequestCacheCommand(int id) {
		super(HystrixCommandGroupKey.Factory.asKey("CacheCommand"));
		this.id=id;
	}

	public static void main(String[] args) {
		HystrixRequestContext hrc= HystrixRequestContext.initializeContext();
		try {
			RequestCacheCommand r1=new RequestCacheCommand(2);
			RequestCacheCommand r2= new RequestCacheCommand(2);
			r1.execute();
			Assert.assertFalse(r1.isResponseFromCache());
			r2.execute();
			Assert.assertFalse(r2.isResponseFromCache());
		} finally {
			hrc.shutdown();
		}
		hrc=HystrixRequestContext.initializeContext();
		try {
			 RequestCacheCommand r3 = new RequestCacheCommand(2); 
			 r3.execute();  
	         Assert.assertFalse(r3.isResponseFromCache()); 
		} finally {
			hrc.shutdown();
		}
	}

	/* (non-Javadoc)
	 * @see com.netflix.hystrix.HystrixCommand#run()
	 */
	@Override
	protected String run() throws Exception {
		System.out.println(Thread.currentThread().getName()+" cerrid: "+id);
		return "cerrid: "+id;
	}
	@Override
	protected String getCacheKey(){
		return String.valueOf(id);
	}
}
