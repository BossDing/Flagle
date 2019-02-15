package com.hrabbit.admin.core.common.ftp.pool;

import com.hrabbit.admin.core.common.ftp.factory.FTPClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.pool2.ObjectPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 自定义实现ftp连接池
 *
 * @Auther: hrabbit
 * @Date: 2018-12-03 3:40 PM
 * @Description:
 */
@Slf4j
@SuppressWarnings("all")
public class FTPClientPool implements ObjectPool<FTPClient> {

    private static final int DEFAULT_POOL_SIZE = 10;

    public BlockingQueue<FTPClient> blockingQueue;

    private FTPClientFactory factory;

    public FTPClientPool(FTPClientFactory factory) throws Exception {
        this(DEFAULT_POOL_SIZE, factory);
    }

    public FTPClientPool(int poolSize, FTPClientFactory factory) throws Exception {
        this.factory = factory;
        this.blockingQueue = new ArrayBlockingQueue<FTPClient>(poolSize);
        initPool(poolSize);
    }
    /**
     * 初始化连接池
     *
     * @param maxPoolSize 最大连接数
     * @throws Exception
     */
    private void initPool(int maxPoolSize) throws Exception {
        int count = 0;
        while (count < maxPoolSize) {
            this.addObject();
            count++;
        }
    }

    /**
     * 从连接池中获取对象
     */
    @Override
    public FTPClient borrowObject() throws Exception {
        FTPClient client = blockingQueue.take();
        if (client == null) {
            client = factory.makeObject();
        } else if (!factory.validateObject(client)) {
            invalidateObject(client);
            client = factory.makeObject();
        }
        return client;
    }

    /**
     * 返还一个对象(链接)
     */
    @Override
    public void returnObject(FTPClient client) throws Exception {
        if ((client != null) && !blockingQueue.offer(client, 2, TimeUnit.MINUTES)) {
            try {
                factory.destroyObject(client);
            } catch (Exception e) {
                throw e;
            }
        }
    }

    /**
     * 移除无效的对象(FTP客户端)
     */
    @Override
    public void invalidateObject(FTPClient client) throws Exception {
        blockingQueue.remove(client);
    }

    /**
     * 增加一个新的链接，超时失效
     */
    @Override
    public void addObject() throws Exception {
        blockingQueue.offer(factory.makeObject(), 2, TimeUnit.MINUTES);
    }

    /**
     * 重新连接
     */
    public FTPClient reconnect() throws Exception {
        return factory.makeObject();
    }

    /**
     * 获取空闲链接数(这里暂不实现)
     */
    @Override
    public int getNumIdle() {
        return blockingQueue.size();
    }

    /**
     * 获取正在被使用的链接数
     */
    @Override
    public int getNumActive() {
        return DEFAULT_POOL_SIZE - getNumIdle();
    }

    @Override
    public void clear() throws Exception {

    }

    /**
     * 关闭连接池
     */
    @Override
    public void close() {
        try {
            while (blockingQueue.iterator().hasNext()) {
                FTPClient client = blockingQueue.take();
                factory.destroyObject(client);
            }
        } catch (Exception e) {
            log.error("close ftp client pool failed...{}", e);
        }
    }

    /**
     * 增加一个新的链接，超时失效
     */
    public void addObject(FTPClient ftpClient) throws Exception {
        blockingQueue.put(ftpClient);
    }
}

