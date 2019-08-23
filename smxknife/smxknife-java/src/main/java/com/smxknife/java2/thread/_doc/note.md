### runnableGet

 executor.submit(Runnable runnable, T result);
 
 这个result没有任何改变，后输出，使用时需注意
 
 ### cancel#RunnableInterrupt vs cancel#CallableInterrupt
 
 Runnable调用Interrupt方法后，会继续输出
 Callable调用Interrupt方法后，不会继续输出