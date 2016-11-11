package com.car.service.car;

import javax.servlet.ServletContext;

import com.car.entity.car.TMessage;
import com.car.entity.message.Dxcxjg;
import com.car.entity.message.Dxhfnr;
import com.car.entity.message.MsgAlarm;
import com.car.entity.message.Dxcx;
import com.car.entity.message.Dxfsxx;

import oracle.net.aso.s;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import com.framework.util.BaseUtils;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.framework.util.XMLUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author 官京 短信服务启动
 * 
 */
@Component
public class MessageListener implements InitializingBean, ServletContextAware {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public final static String SERVICEURL = "http://10.117.14.5:8733/DS.AFP.Integration?wsdl";
	// public final static String SERVICEURL =
	// "http://192.168.0.84:8090/Device/webservice/devicemanager?wsdl";
	public final static String NAMESPACE = "http://tempuri.org/";

	@Autowired
	private IMessageSercice iMessageSercice;
	public static Thread sendThread;

	public static Thread rereceiveThread = new Thread(new Runnable() {

		@Override
		public void run() {
			// TODO Auto-generated method stub

		}
	});

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 当tomcat启动spring已经成功加载后执行这个方法 MessageService是一个短信服务类
	 */
	@Override
	public void setServletContext(ServletContext arg0) {

		sendThread = new Thread(new Runnable() {

			@Override
			public void run() {
				sendService();
			}
		});
		rereceiveThread = new Thread(new Runnable() {

			@Override
			public void run() {
				receiveService();
			}
		});
		sendThread.start();
		rereceiveThread.start();
	}

	/**
	 * 发送短信业务
	 */
	public void sendService() {
		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#messStatus_I_EQ", "1");
		hqlFilter.addFilter("QUERY_t#messType_I_EQ", "1");
		while (true) {
			try {
				List<TMessage> messageList = iMessageSercice.findByFilter(hqlFilter);
				int count = 0;
				for (TMessage message : messageList) {
					String[] phones = message.getMessPhones().split(";");
					for (int i = 0; i < phones.length; i++) {
						count++;
						Dxfsxx msgAlarm = new Dxfsxx();
						String id = message.getMessId().replace("-", "");
						id = id.substring(0, id.length() - 3) + count;
						msgAlarm.setLSH(id);
						msgAlarm.setDXLX(ConfigUtil.get(WebMsgUtil.DXLX));
						Date date = new Date(new Date().getTime() + 1000);
						msgAlarm.setTJSJ(sdf.format(new Date()));
						msgAlarm.setFSSJ(sdf.format(date));
						msgAlarm.setDHHM(phones[i]);
						msgAlarm.setDXNR(message.getMessInfo() + "(调度服务中心)");
						String result = send(msgAlarm);
						if (result.equals("-1")) {
							System.out.println("发送失败");
						} else if (result.equals("false") || result.equals("fail")) {
							System.out.println("访问网络超时！");
						} else {
							System.out.println("发送成功");
							message.setMessStatus(2);
							iMessageSercice.update(message);
						}
					}
				}
				Thread.sleep(Integer.valueOf(ConfigUtil.get("messageSleep")));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 发送短信
	 */
	public String send(Object object) {
		String SOAPACTION = "http://tempuri.org/IMonitorService/CKDXFS";
		String method = "CKDXFS";
		String str = XMLUtil.convertToXml(object);
		LinkedHashMap<String, Object> parameter = new LinkedHashMap<>();
		parameter.put("dxfsxml", str);
		String result = BaseUtils.callWebService(SERVICEURL, NAMESPACE, SOAPACTION, method, parameter);
		return result;
	}

	/**
	 * 接收短信业务
	 */
	public void receiveService() {

		HqlFilter hqlFilter = new HqlFilter();
		hqlFilter.addFilter("QUERY_t#messType_I_EQ", "2");

		while (true) {
			try {
				Dxcx selectMessage = new Dxcx();
				UUID uuid = UUID.randomUUID();
				String id = uuid.toString().replace("-", "");
				selectMessage.setLSH(id);
				selectMessage.setYHM(ConfigUtil.get(WebMsgUtil.DXYHM));
				selectMessage.setDXLX(ConfigUtil.get(WebMsgUtil.DXLX));
				String result = receive(selectMessage);
				if (result.equals("1")) {
					System.out.println("没有查询结果!");
				} else if (result.equals("false") || result.equals("fail")) {
					System.out.println("访问网络超时！");
				} else {
					Dxcxjg dxcxjg = XMLUtil.converyToJavaBean(result, Dxcxjg.class);// 解析数据
					List<Dxhfnr> dxhfnrs = new ArrayList<Dxhfnr>();
					dxhfnrs.addAll(dxcxjg.getDxhfnr());
					for (int i = 0; i < dxhfnrs.size(); i++) {
						String strnr = dxhfnrs.get(i).getDXNR();
						if (strnr != null && !strnr.equals("")) {// 判断短信内容是否为空
							String applyNo = strnr.substring(0, 14);
							// 判断短信内容是否包含YyNn
							if (applyNo.length() == 14 && applyNo.matches("[0-9]+")) {
								hqlFilter.addFilter("QUERY_t#messKey_S_EQ", applyNo);
								TMessage tMessage = iMessageSercice.getByFilter(hqlFilter);
								if (tMessage == null) {
									tMessage = new TMessage();
									tMessage.setMessId(UUID.randomUUID().toString());
									tMessage.setMessKey(applyNo);
									tMessage.setMessPhones(dxhfnrs.get(i).getDHHM());
									tMessage.setMessTime(new Date());
									tMessage.setMessStatus(1);
									tMessage.setMessInfo(dxhfnrs.get(i).getDXNR());
									tMessage.setDictIdMessageKind(WebMsgUtil.MESSAGE_KindDXJS);
									iMessageSercice.save(tMessage);
								}
							} else {
								Dxfsxx msgAlarm = new Dxfsxx();
								String lsh = UUID.randomUUID().toString().replace("-", "");
								msgAlarm.setLSH(lsh);
								msgAlarm.setDXLX(ConfigUtil.get(WebMsgUtil.DXLX));
								Date date = new Date(new Date().getTime() + 1000);
								msgAlarm.setTJSJ(sdf.format(new Date()));
								msgAlarm.setFSSJ(sdf.format(date));
								msgAlarm.setDHHM(dxhfnrs.get(i).getDHHM());
								msgAlarm.setDXNR("单号格式不正确请输入正确的14位单号." + "(调度服务中心)");
								send(msgAlarm);
							}
						}
					}
				}
				Thread.sleep(Integer.valueOf(ConfigUtil.get("messageSleep")));
			} catch (Exception e) {
				e.printStackTrace();
			}
<<<<<<< .mine
});
		//mythred.start();
=======
		}
>>>>>>> .r964
	}

	/**
	 * 接收短信查询
	 * 
	 * @param object
	 * @return
	 */
	public String receive(Object object) {
		String method = "CKDXHFCX";
		String SOAPACTION = "http://tempuri.org/IMonitorService/CKDXHFCX";
		String str = XMLUtil.convertToXml(object);
		LinkedHashMap<String, Object> parameter = new LinkedHashMap<>();
		parameter.put("dxxml", str);
		String result = BaseUtils.callWebService(SERVICEURL, NAMESPACE, SOAPACTION, method, parameter);
		return result;
	}
	/*
	 * HqlFilter hqlFilter=new HqlFilter();
	 * hqlFilter.addFilter("QUERY_t#messStatus_I_EQ","1"); while (true) { try {
	 * List<TMessage> messageList=iMessageSercice.findByFilter(hqlFilter); int
	 * count=0; for (TMessage message:messageList){ String[]
	 * phones=message.getMessPhones().split(";"); for (int i = 0; i <
	 * phones.length; i++) { count++; MsgAlarm msgAlarm=new MsgAlarm(); String
	 * id=message.getMessId().replace("-","");
	 * id=id.substring(0,id.length()-3)+count; msgAlarm.setRecordId(id);
	 * msgAlarm.setPlatecolor(12+""); Date date=new Date(new
	 * Date().getTime()+1000) ; msgAlarm.setPictime(date);
	 * msgAlarm.setCreatetime(date); msgAlarm.setManageResult(0+"");
	 * msgAlarm.setFlag(0+""); msgAlarm.setCaseLeaderTel(phones[i]); //
	 * msgAlarm.setCaseLeaderTel("13796999229");
	 * msgAlarm.setSmstext(message.getMessInfo()+"(调度服务中心)");
	 * omessageService.save(msgAlarm); message.setMessStatus(2);
	 * iMessageSercice.update(message); }
	 * 
	 * } Thread.sleep(Integer.valueOf(ConfigUtil.get("messageSleep"))); }catch
	 * (Exception e){ e.printStackTrace(); } }
	 */

}
