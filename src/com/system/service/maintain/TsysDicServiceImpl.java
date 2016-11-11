package com.system.service.maintain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.entity.maintain.TsysDictionary;
import com.system.service.base.BaseServiceImpl;

@Service
public class TsysDicServiceImpl extends BaseServiceImpl<TsysDictionary> implements ITsysDicService {

	@Autowired
	private SysUserServiceI userDao;

	@Override
	public List<Map> treeData(HttpServletRequest request) {
		// TODO Auto-generated method stub
		String flag = request.getParameter("flag");
		String sql = "select * from TSysDictionary where dictId='" + flag + "'";
		List<Map> list = this.findBySql(sql);
		return list;
	}

	

	@Override
	public void saveDicData(TsysDictionary dictionary, String userId) {
		dictionary.setTimeCreate(new Timestamp(new Date().getTime()));
		dictionary.setUserIdCreate(userId);
		dictionary.setDictFlag(WebMsgUtil.YOUXIAO);
		save(dictionary);
	}

}
