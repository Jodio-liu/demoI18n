package com.example.demoi18n;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@AllArgsConstructor
@NoArgsConstructor
public class CusClassLoader extends ClassLoader {

    private Class<?> loadClass(){
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        return null;
    }

    Lock lock = new ReentrantLock();
    ReadWriteLock l = new ReentrantReadWriteLock();
    Map map = new ConcurrentHashMap();


}
