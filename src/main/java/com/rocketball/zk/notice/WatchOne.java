package com.rocketball.zk.notice;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

public class WatchOne extends BaseZooKeeper{

	/**
	 * 当节点值改变之后,ZooKeeper通知客户端 并且增加watcher
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public String getZnodeChanged(String path) throws Exception
	{
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
	 * 关注某一节点的数值 是否变化
	 * @param path
	 * @throws Exception
	 */
	public void triggerValue(String path) throws Exception
	{
		String result = "";
		
		byte[] byteArray = zk.getData(path, null, new Stat());
		result = new String(byteArray);
		
		logger.info("*********watcher after triggerValue result : "+result);
			
	}
	/**
	 * 
	 * @Description:
	 * 1	初始化ZK的多个操作
	 * 		1.1	建立ZK的链接
	 * 		1.2	创建/atguigu节点并赋值
	 * 		1.3	获得该节点的值
	 * 
	 * 2	watch
	 * 		2.1	获得值之后(getZnode方法被调用后)设置一个观察者watcher，如果/atguigu该节点的值发生了变化，(A-->B)
	 * 			要求通知Client端eclipse，一次性通知
	 * @author zzyy
	 * @throws Exception 
	 * @date 2018年3月21日
	 */
	public static void main(String[] args) throws Exception {

		WatchOne  one =new WatchOne();
		one.setZk(one.StartZk());
		
		if(one.getZk().exists(PATH, false) == null) {
			one.createZnode(PATH, "AAA");
			String result = one.getZnodeChanged(PATH);
			logger.info("*********main init result : "+result);
		}else {
			logger.info("This node is exists......");
		}
	
	Thread.sleep(Long.MAX_VALUE);
		
	}

}
