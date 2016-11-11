package com.car.service.insurance;

import com.framework.util.WebMsgUtil;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Service;

import com.car.entity.insurance.TCarInsurance;
import com.system.service.base.BaseServiceI;
import com.system.service.base.BaseServiceImpl;

import java.util.List;
import java.util.Map;

@Service
public class CarInsuranceServiceImpl extends BaseServiceImpl<TCarInsurance>
		implements CarInsuranceServiceI {

	@Override
	public String getMaxTime(String carId, String typeId, String id) {
		String sql = "";
		Map<String, Object> param = new HashedMap();

		param.put("typeId", typeId);
		param.put("dictIdFlag", WebMsgUtil.YOUXIAO);
		if (id != null && !id.equals("")) {
			TCarInsurance tCarInsurance = super.getById(id);
			sql = "SELECT MAX(tcii.insuOverDate) as lastTime FROM t_car_insurance tcii WHERE "
					+ "tcii.dictIdInsuranceType = :typeId "
					+ "AND tcii.carId = :carId AND dictIdFlag=:dictIdFlag AND insuOverDate<:insuOverDate";
			param.put("carId", tCarInsurance.getCarId());
			param.put("insuOverDate", tCarInsurance.getInsuOverDate());
		} else {
			sql = "SELECT MAX(tcii.insuOverDate) as lastTime FROM t_car_insurance tcii WHERE "
					+ "tcii.dictIdInsuranceType = :typeId "
					+ "AND tcii.carId = :carId AND dictIdFlag=:dictIdFlag";
			param.put("carId", carId);
		}

		List<Map<String, Object>> list = super.findBySql(sql, param);
		if (list.size() > 0) {
			return list.get(0).get("lastTime") == null ? "null" : list.get(0)
					.get("lastTime").toString();
		}
		return "null";
	}
}
