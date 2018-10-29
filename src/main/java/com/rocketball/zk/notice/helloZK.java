package com.rocketball.zk.notice;


import org.apache.zookeeper.ZooKeeper;


public class helloZK extends BaseZooKeeper {

	/**
	 * 
	 * @Description:eclipse此处为Client端，CentOS为ZooKeeper的Server端
	 * 
	 * 1	通过java程序，新建链接zk，类似jdbc的connection，open.session
	 * 2	新建一个znode节点/atguigu并设置为hello1018	等同于create /atguigu hello1018
	 * 3	获得当前节点/atguigu的最新值get /atguigu
	 * 4	关闭链接
	 * 
	 * @author zzyy
	 * @date 2018年3月21日
	 */
	/**
	 * Logger for this class
	 */
	
	public static void main(String[] args) throws Exception {

		helloZK hello = new helloZK();
		ZooKeeper zk = hello.StartZk();
		
		if(zk.exists(PATH, false) == null) {
			hello.createZnode(PATH, "Java_hello1018");
			
			String result = hello.getZnode( PATH);
			
			if (logger.isInfoEnabled()) {
				logger.info("*****************result" + result); //$NON-NLS-1$
			}
		}else {
			logger.info("it's already exists!"); //$NON-NLS-1$
		}
		
		hello.StopZK();
		
	}

}
