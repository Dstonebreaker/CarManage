package com.car.service.pgis;

import java.io.Serializable;

import org.springframework.stereotype.Service;

import com.car.entity.pgis.TCarFenceDefine;
import com.system.service.base.BaseServiceImpl;
@Service
public class CarFenceDefineServiceImpl extends BaseServiceImpl<TCarFenceDefine> implements ICarFenceDefineService {
@Override
public Serializable save(TCarFenceDefine o) {
	String fenceCenter=o.getFenceCenter()!=null?"pointfromtext('POINT("+o.getFenceCenter()+")')":"null";
	String points=o.getPoints()!=null?"polygonfromtext('POLYGON(("+o.getPoints()+"))')":"null";
	String sql="insert into t_car_fence_define (fenceCenter, fenceKind, fenceRadius, points, dictIdRegion) "
			+ "values ("+fenceCenter+", "+o.getFenceKind()+","+o.getFenceRadius()+","+points+", '"+o.getDictIdRegion()+"')";
	
	return super.executeSql(sql);
}
@Override
	public void update(TCarFenceDefine o) {
	String fenceCenter=o.getFenceCenter()!=null?"pointfromtext('POINT("+o.getFenceCenter()+")')":"null";
	String points=o.getPoints()!=null?"polygonfromtext('POLYGON(("+o.getPoints()+"))')":"null";
	String sql="update t_car_fence_define set fenceCenter="+fenceCenter+" , fenceKind"
			+ "="+o.getFenceKind()+", fenceRadius="+o.getFenceRadius()+", points="+o.getPoints()
			+" where dictIdRegion='"+o.getDictIdRegion()+"'";
	super.executeSql(sql);
	}

}
