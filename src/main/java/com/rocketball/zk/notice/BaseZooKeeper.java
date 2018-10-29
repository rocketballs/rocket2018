package com.rocketball.zk.notice;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

import lombok.Getter;
import lombok.Setter;


public class BaseZooKeeper {

    //实例常量
    public static final String CONNECTSTRING = "newsrec41.xs.163.org:2181";
    public static final int SESSION_TIMEOUT = 20 * 1000;
    public static final String PATH = "/atguigu";

    protected static final Logger logger = LogManager.getLogger(helloZK.class.getName());
    //实例变量
    public ZooKeeper zk;

    public ZooKeeper getZk() {
        return zk;
    }

    public void setZk(ZooKeeper zk) {
        this.zk = zk;
    }

    /**
     * 创建ZooKeeper的实例
     *
     * @return
     * @throws IOException
     */
    public ZooKeeper StartZk() throws IOException {

        return new ZooKeeper(CONNECTSTRING, SESSION_TIMEOUT, new Watcher() {

            public void process(WatchedEvent event) {

            }
        });
    }

    /**
     * 创建一份节点对象
     *
     * @param zk
     * @param path
     * @param data
     * @throws Exception
     */
    public void createZnode(String path, String data) throws Exception {
        zk.create(path, data.getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    /**
     * 获取结点数据
     *
     * @param zk
     * @param path
     * @return
     * @throws Exception
     */
    public String getZnode(String path) throws Exception {

        String result = "";
        byte[] data = zk.getData(PATH, false, new Stat());
        result = new String(data);
        return result;
    }

    /**
     * 关闭连接
     *
     * @param zk
     * @throws Exception
     */
    public void StopZK() throws Exception {
        if (null != zk)
            zk.close();
    }


}
