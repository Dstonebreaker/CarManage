package com.framework.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.car.service.car.MessageListener;

/**
 * @author GJ
 *   监听tomcat  shutdown 关闭应用中的线程
 */
public class DestoryListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		try {
			if(MessageListener.sendThread.isAlive())
				MessageListener.sendThread.stop();
			if (MessageListener.rereceiveThread.isAlive()) {
				MessageListener.rereceiveThread.stop();
			}
			System.out.println("短信服务已注销");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
