package com.rocketball.zk.notice;


import org.apache.zookeeper.*;

import java.util.List;
import java.util.concurrent.CountDownLatch;



public class TestGetChildrenMethod implements Watcher {
    private static CountDownLatch countDownLatch = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;

    public static void main(String[] args) throws Exception {
        zooKeeper = new ZooKeeper("newsrec41.xs.163.org:2181,newsrec42.xs.163.org:2181,newsrec43.xs.163.org:2181,newsrec44.xs.163.org:2181,newsrec50.xs.163.org:2181", 5000, new TestGetChildrenMethod());
        countDownLatch.await();

        // 创建父节点/test
//        zooKeeper.create("/tomcat-online", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        zooKeeper.create("/tomcat-online-affair", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        // 在父节点/test下面创建a1节点
        zooKeeper.create("/test/a3", "ccc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        // 同步获得结果
        List<String> childrenList = zooKeeper.getChildren("/test", true);
        System.out.println("同步getChildren获得数据结果：" + childrenList);

        // 异步获得结果
//        zooKeeper.getChildren("/test", true, new MyChildren2Callback(), null);

        // 在父节点/test下面创建a2节点
        zooKeeper.create("/test2/a4", "ddd".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        zooKeeper.create("/test2/a5", "fff".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

        Thread.sleep(10000);

    }

    public void process(WatchedEvent event) {
        if (Event.KeeperState.SyncConnected == event.getState()) {
            if (Event.EventType.None == event.getType() && null == event.getPath()) { // 连接时的监听事件
                countDownLatch.countDown();
            } else if (event.getType() == Event.EventType.NodeChildrenChanged) { // 子节点变更时的监听
                try {
                    System.out.println("重新获得Children，并注册监听：" + zooKeeper.getChildren(event.getPath(), true));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

