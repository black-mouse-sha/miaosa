package com.shop.service.shop;

import com.shop.service.module.util.RedisLockHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(
    classes = {ShopApplication.class},
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class TestLock {

    //注入redis排他锁对象
    @Autowired
    private RedisLockHelper redisLockHelper;
    //设置库存为10
    private static int count = 10;

    @Test
    public void test() throws InterruptedException {
        //初始化线程数
        int threadCount = 100;
        //初始化线程池数量为threadCount的数量
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        //设置线程等待对象等待数量为threadCount的数量
        CountDownLatch countDown = new CountDownLatch(threadCount);
        //根据线程数创建线程
        for(int i = 0 ;i < threadCount; i++){
            //保存当前线程的序号
            int finalI = i;
            //启动线程
            executorService.execute(() -> {
                //抢锁
                boolean lock = redisLockHelper.lock("test");
                int awaitCount = 5;
                //做重试
                while (lock == false && awaitCount>0){
                    lock = redisLockHelper.lock("test");
                    awaitCount--;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                //如果lock为true代表抢锁成功
                if(lock){
                    //获取当前的库存
                    int c = count;
                    //输出当前库存
                    System.out.println("线程"+finalI+"成功获取锁，并得到count为："+c);
                    //如果库存未清空
                    if(c > 0){
                        //扣减库存
                        count = c - 1;
                        //输出扣减后的库存
                        System.out.println("线程"+finalI+"执行扣减，此时count为："+count);
                    }else{
                        //如果库存请求就返回抢购完毕
                        System.out.println("线程"+finalI+"发现库存已为0，无法抢购");
                    }

                }else{
                    //如果未抢到锁，就输出结果
                    System.err.println("线程"+finalI+"抢锁失败，抢购失败");
                }
                //释放锁
                redisLockHelper.delete("test");
                //让监控线程对象知道当前线程执行完毕
                countDown.countDown();
            });
        }
        //等待所有监控对象完成监控
        countDown.await();
        //关闭所有线程
        executorService.shutdown();
        //输出最后剩余的库存
        System.out.println(threadCount+"个并发抢购之后还剩的库存为:"+count);
    }
}
