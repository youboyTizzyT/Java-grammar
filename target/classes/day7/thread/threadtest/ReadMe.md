#多线程在thread包下的例子是为了介绍线程的一些方法 那么如何在实际情况中使用多线程的例子.
##其实在生活中会有很多多线程的例子,我们一一介绍,使他们程序实现.
* 银行取钱线程例子 threadtest1 明确的演示了多线程情况下的问题,安全性是最重要的,因为我们无法控制线程之间的先后,而且两个线
程完全同时访问同一个数据,获取到相同的数字然后进行运算.
* i++的例子. 比较直接的一次,也是各大教科书上最基本的例子 threadtest2里面的两个例子 
* 当然银行的例子我们也可以使用一些锁来使线程之间按照我们想要的顺序执行,我们先使用在Concurrent包下的ReentrantLock锁 threadtest3
* 解决了安全性的问题,当然也会有很多并发锁的一个在各种情境下的实现,一个一个介绍,读写锁 threadtest4
* threadtest5 介绍线程池各种知识  
#### 线程池 
#####java自带线程池
1. newSingleThreadExecutor:创建一个单线程的线程池。这个线程池只有一个线程在工作，也就是相当于单线程串行执行所有任务。如果
这个唯一的线程因为异常结束，那么会有一个新的线程来替代它。此线程池保证所有任务的执行顺序按照任务的提交顺序执行。
2. newFixedThreadPool:创建固定大小的线程池。每次提交一个任务就创建一个线程，直到线程达到线程池的最大大小。线程池的大小一旦
达到最大值就会保持不变，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程。
3. newCachedThreadPool:创建一个可缓存的线程池。如果线程池的大小超过了处理任务所需要的线程，那么就会回收部分空闲（60秒不执行
任务）的线程，当任务数增加时，此线程池又可以智能的添加新线程来处理任务。此线程池不会对线程池大小做限制，线程池大小完全依
赖于操作系统（或者说JVM）能够创建的最大线程大小。
4. newScheduledThreadPool:。创建一个大小无限的线程池。此线程池支持定时以及周期性执行任务的需求

* 对于线程池,其实在阿里规范中明确提到,创建线程或线程池时请指定有意义的线程名称，方便出错时回溯。创建线程池的时候请使用带
ThreadFactory的构造函数，并且提供自定义ThreadFactory实现或者使用第三方实现。在threadtest6中讲讲解ThreadFactory使用和Thre
adPoolExecutor.ThreadFactory的作用,是摆脱我们自己创建线程,现在让ThreadFactory来进行创建,ThreadPoolExecutor的作用是不用我
们自己进行创建线程池,现在让ThreadPoolExecutor进行创建线程池,当然,这样的使用,使我们只需要关心线程内部的业务逻辑部分,从而不
去进行写一大部分重复代码,在阿里规范里面:线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式这样的处理方式让
写的同学更加明确线程池的运行规则，规避资源耗尽的风险。 说明：Executors各个方法的弊端：
1. newFixedThreadPool和newSingleThreadExecutor:
  主要问题是堆积的请求处理队列可能会耗费非常大的内存，甚至OOM。
2. newCachedThreadPool和newScheduledThreadPool:
  主要问题是线程数最大数是Integer.MAX_VALUE，可能会创建数量非常多的线程，甚至OOM。 
* threadtest7 将会讲解concurrent包下的重入锁.ReentrantLockTest1这个将会了解一个例子,来对ReentrantLock先来一步认识
* threadtest8 讲解wati notify 和notifyAll的用法
* threadtest9 CountDownLatch CyclicBarrier LockSupport用法
* threadtest10 自定义异常处理线程异常.
* threadtest11 线程组 例子.线程组我就没用过,所以,写个例子就行了.感觉这个东西没啥用.
* threadtest12 ThreadLocal的使用和详解  分享关于ThreadLocal https://www.cnblogs.com/zhangjk1993/archive/2017/03/29/6641745.html
* threadtest13 原子操作数据结构 在此只试用一下AtomicInteger
* threadtest14 Volatile
1. 内存屏障和编译屏障就是用来告诉CPU和编译器停止优化的手段。
2. 编译屏障是指使用伪指令“memory”告诉编译器不能把“memory”执行前后的代码混淆在一起，
3. 读操作远远大于写操作，volatile 变量还可以提供优于锁的性能优势。
4. volatile不具备原子特性 单独使用 volatile 还不足以实现计数器、互斥锁
5. 使用条件: 对变量的写操作不依赖于当前值  该变量没有包含在具有其他变量的不变式中 所以volatile一般用于一个状态的标识
比如开服关服, 开销较低的读－写锁策略
*  threadtest15 Fork/Join框架来并行执行任务 
 fork/join框架的使用有一定的约束条件：
   1. 除了fork()  和  join()方法外，线程不得使用其他的同步工具。线程最好也不要sleep()
   2. 线程不得进行I/O操作
   3. 线程不得抛出checked exception
            