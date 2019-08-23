# execute和submit区别

* execute没有返回值，submit可以有返回值
* execute在默认情况下异常直接抛出，不能捕获。但是可以通过自定义ThreadFactory的方式进行捕获。而submit方法在默认情况下，可以catch ExecutionException捕获异常

    **重点注意**：submit想要获取捕获异常，必须是调用get方法之后，才可以捕获，否则会什么信息都无法获得，包括异常信息
    
    execute虽然可以捕获异常，但是是指在threadFactory里面捕获，在execute外层嵌套try catch依然无法在catch中捕获异常
