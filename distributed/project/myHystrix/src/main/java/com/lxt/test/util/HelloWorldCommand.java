package com.lxt.test.util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

/**
 * 描述 []
 * 创建者 LXT
 * 创建时间 2017年9月1日 上午11:14:30
 */
public class HelloWorldCommand extends HystrixCommand<String>{
	private final String name;
	/**
	 * @param setter
	 */
	public HelloWorldCommand(String name) {
		super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
		this.name=name;
	}

	/* (non-Javadoc)
	 * @see com.netflix.hystrix.HystrixCommand#run()
	 */
	@Override
	protected String run() throws Exception {
		// TODO Auto-generated method stub
		return "hello: "+name+", thread: "+Thread.currentThread().getName();
	}

	public static void  main(String[] args) throws InterruptedException, ExecutionException, TimeoutException{
		HelloWorldCommand h= new HelloWorldCommand("Synchronous-hystrix");
		String result=h.execute();
		System.out.println("result: "+result);
		h= new HelloWorldCommand("Asynchronous-hystrix");
		Future<String> f= h.queue();
		result=f.get(100, TimeUnit.MILLISECONDS);
		System.out.println("fresult: "+result);
		System.out.println("threadname: "+Thread.currentThread().getName());
		
		Observable<String> fs= new HelloWorldCommand("world").observe();
	
		fs.subscribe(new Observer<String>() {

			public void onCompleted() {
				// onNext/onError完成之后最后回调  
                System.out.println("execute onCompleted"); 
			}

			public void onError(Throwable arg0) {
				 // 当产生异常时回调  
                System.out.println("onError " + arg0.getMessage());  
                arg0.printStackTrace();
			}

			public void onNext(String arg0) {
				 // 获取结果后回调  
                System.out.println("onNext: " + arg0);
			}
		});
		
		fs.subscribe(new Action1<String>() {
			public void call(String result) {
				System.out.println("action1: "+result);
			}
		});
	}
	
	
}
