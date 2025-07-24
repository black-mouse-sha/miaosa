package com.shop.service.mqclient.test;

import com.shop.service.mqclient.MqClientApplication;
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
@SpringBootTest(classes={MqClientApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class RedisLockHelperTests {
    @Autowired
    RedisLockHelper redisHelper;
    @Test
    public void testRedis() throws InterruptedException {
        int totalThreads = 100;
        ExecutorService executorService = Executors.newFixedThreadPool(totalThreads);

        CountDownLatch countDownLatch = new CountDownLatch(totalThreads);
        for(int i=0; i<totalThreads; i++) {
            String threadId = String.valueOf(i);
            executorService.execute( () -> {
                try {
                    testLock("lalala", threadId);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        // After all thread done, acquire again, expect to be successful.
        testLock("lalala", "final success");
    }

    public void testLock(String key, String threadId) throws InterruptedException {
        boolean lock = redisHelper.lock(key);
        while (lock ==false){
            lock = redisHelper.lock(key);
        }
        if (lock) {
            System.out.println(lock);
            System.out.println("Successfully got lock - " + threadId);
            Thread.sleep(100);
            redisHelper.delete(key);

        } else {
            System.out.println("Failed to obtain lock - " + threadId);
        }
    }
}