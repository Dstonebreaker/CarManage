package com.car.action.car;

import com.car.entity.car.TCarMaintenance;
import com.car.entity.car.TCarModelMaintenance;
import com.car.entity.car.VCarModelMaintenance;
import com.car.entity.insurance.TCarClaims;
import com.car.entity.insurance.TCarInsurance;
import com.car.service.car.ICarMaintenanceService;
import com.car.service.car.ICarModelMaintenanceService;
import com.car.service.car.IVCarModelMaintenanceService;
import com.framework.util.ConfigUtil;
import com.framework.util.HqlFilter;
import com.framework.util.WebMsgUtil;
import com.system.action.base.BaseAction;
import com.system.entity.easyui.Grid;
import com.system.entity.easyui.Json;
import com.system.entity.maintain.SessionInfo;
import com.system.entity.maintain.SysUser;
import com.system.entity.maintain.VSysUser;
import com.system.service.maintain.IVSysUserService;
import com.system.service.maintain.SysUserServiceI;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Namespace("/car")
@Action("carmaintenance")
public class CarMaintenanceAction extends BaseAction<TCarMaintenance> {

    /**
     *
     */
    private static final long serialVersionUID = -5528199332073156081L;
    private TCarMaintenance carMaintenance;
    @Autowired
    private IVCarModelMaintenanceService carModelMaintenanceService;
    @Autowired
    private IVSysUserService userServiceI;
    private String mmainId;
    @Autowired
    private ICarModelMaintenanceService modelMaintenanceService;

    @Autowired
    public void setService(ICarMaintenanceService service) {
        // TODO Auto-generated method stub
        super.setService(service);
    }

    /**
     * 获取维保模型
     */
    public void doNotNeedSecurity_modelGrid() {
        Grid grid = new Grid();
        HqlFilter hqlFilter = new HqlFilter(getRequest());
        grid.setTotal(carModelMaintenanceService.countByFilter(hqlFilter));
        grid.setRows(carModelMaintenanceService.findByFilter(hqlFilter, page, rows));
        writeJson(grid);
    }

    /**
     * 保存
     */
    public void save() {
        Json json = new Json();
        try {
            SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
            carMaintenance.setUserIdCreate(sessionInfo.getUser().getUserId());
            carMaintenance.setTimeCreate(new Date());
            carMaintenance.setDictIdFlag(WebMsgUtil.YOUXIAO);
            boolean result=((ICarMaintenanceService) service).saveM(carMaintenance);
            if (result){
                json.setSuccess(true);
                json.setMsg("保存成功");
            }else {
                json.setSuccess(false);
                json.setMsg("保存失败");
            }

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();

        }
        writeJson(json);
    }

    /**
     * 获取当前单位下用户信息
     */
    public void doNotNeedSecurity_getUserList() {
    	 SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
    	  HqlFilter hqlFilter = new HqlFilter();
          hqlFilter.addFilter("QUERY_t#orgId_S_EQ", sessionInfo.getOrganization().getOrgId());
          List<VSysUser> l = new ArrayList<VSysUser>();
          List<VSysUser> list = userServiceI.findByFilter(hqlFilter);
          for(VSysUser user :list){
        	  user.setUserSign(null);
        	  l.add(user);
          }
    	  writeJson(l);
       // String[] include = {"userId", "userName"};
      //  writeJsonByIncludesProperties(userServiceI.find(), include);
    }

    /**
     * 更新
     */
    public void update() {
        Json json = new Json();
        try {
            SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
            carMaintenance.setDictIdFlag(WebMsgUtil.YOUXIAO);
            carMaintenance.setUserIdUpdate(sessionInfo.getUser().getUserId());
            carMaintenance.setTimeUpdate(new Date());
            boolean result= ((ICarMaintenanceService) service).updateM(carMaintenance);
            if (result){
                json.setSuccess(true);
                json.setMsg("保存成功");
            }else {
                json.setSuccess(false);
                json.setMsg("保存失败");
            }
            json.setSuccess(true);
            json.setMsg("保存成功");
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.setSuccess(false);
            json.setMsg("保存失败");
        }
        writeJson(json);

    }

    /*
     * 删除
     */
    public void delete() {

        Json json = new Json();
        try {
            SessionInfo sessionInfo = (SessionInfo) getSession().getAttribute(ConfigUtil.getSessionInfoName());
            TCarMaintenance carMaintenance = service.getById(id);
            carMaintenance.setDictIdFlag(WebMsgUtil.WUXIAO);
            carMaintenance.setUserIdUpdate(sessionInfo.getUser().getUserId());
            carMaintenance.setTimeUpdate(new Date());
            service.update(carMaintenance);
            json.setMsg("删除成功");
            json.setSuccess(true);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            json.setMsg("删除失败");
            json.setSuccess(false);
        }
        writeJson(json);
    }


    public TCarMaintenance getCarMaintenance() {
        return carMaintenance;
    }

    public void setCarMaintenance(TCarMaintenance carMaintenance) {
        this.carMaintenance = carMaintenance;
    }

    public String getMmainId() {
        return mmainId;
    }

    public void setMmainId(String mmainId) {
        this.mmainId = mmainId;
    }
}
