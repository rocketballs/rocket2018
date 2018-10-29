package com.rocketball.zk.notice;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.data.Stat;


public class WatcherMore extends BaseZooKeeper {

    private String newValue = "";
    private String oldValue = "";

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    /**
     * 当节点值改变之后,ZooKeeper通知客户端 并且增加watcher
     *
     * @param path
     * @return
     * @throws Exception
     */
    public String getZnodeChanged(String path) throws Exception {
        String result = "";

        byte[] byteArray = zk.getData(path, new Watcher() {

            public void process(WatchedEvent event) {
                try {
                    triggerValue(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Stat());
        result = new String(byteArray);

        return result;
    }

    /**
     * 目标的数值有所更改
     *
     * @param path
     * @throws Exception
     */
    public boolean triggerValue(String path) throws Exception {
        String result = "";

        byte[] byteArray = zk.getData(path, new Watcher() {

            @Override
            public void process(WatchedEvent event) {
                try {
                    triggerValue(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Stat());
        result = new String(byteArray);
        newValue = result;

        if (newValue.equals(oldValue)) {

            logger.info("the value no changed!");
            return false;
        } else {
            logger.info("the value has changed oldValue: " + oldValue + "\tnewValue : " + newValue);
            oldValue = newValue;
            return true;
        }

    }

    /**
     * @throws Exception
     * @Description: 1    初始化ZK的多个操作
     * 1.1	建立ZK的链接
     * 1.2	创建/atguigu节点并赋值
     * 1.3	获得该节点的值
     * <p>
     * 2	watchmore
     * 2.1	获得值之后设置一个观察者watcher，如果/atguigu该节点的值发生了变化，要求通知Client端，一次性通知
     * <p>
     * 3	watchMore
     * 3.1	获得值之后设置一个观察者watcher，如果/atguigu该节点的值发生了变化，要求通知Client端,继续观察
     * 3.2	又再次获得新的值的同时再新设置一个观察者，继续观察并获得值
     * 3.3	又再次获得新的值的同时再新设置一个观察者，继续观察并获得值.。。。。。重复上述过程
     * @author zzyy
     * @date 2018年3月21日
     */
    public static void main(String[] args) throws Exception {

        WatcherMore watchMore = new WatcherMore();
        watchMore.setZk(watchMore.StartZk());

        if (watchMore.getZk().exists(PATH, false) == null) {
            watchMore.createZnode(PATH, "AAAA");
            String result = watchMore.getZnodeChanged(PATH);
            if (logger.isInfoEnabled()) {
                logger.info("main(String[]) --------init String result=" + result);
            }
        } else {
            logger.info("this node has already exists!!!!");
        }

        Thread.sleep(Long.MAX_VALUE);
    }


}
