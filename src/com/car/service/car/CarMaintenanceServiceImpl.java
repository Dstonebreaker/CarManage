package com.car.service.car;

import com.car.entity.car.TCarMaintenance;
import com.car.entity.car.TCarModel;
import com.car.entity.car.TCarModelMaintenance;
import com.framework.util.HqlFilter;
import com.system.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Mrkin on 2016/8/18.
 */
@Service
public class CarMaintenanceServiceImpl extends BaseServiceImpl<TCarMaintenance> implements ICarMaintenanceService {

    @Autowired
    private ICarModelMaintenanceService modelMaintenanceService;
    @Autowired
    private ICarModelService carModelService;

    @Override
    public boolean saveM(TCarMaintenance carMaintenance) {
        TCarModelMaintenance modelMaintenance = modelMaintenanceService.getById(carMaintenance.getMmainId());
        if (modelMaintenance != null) {
            carMaintenance.setMainCondition(modelMaintenance.getMmainInfo());
            HqlFilter hqlFilter = new HqlFilter();
            hqlFilter.addFilter("QUERY_t#mmainMileage_I_GT", modelMaintenance.getMmainMileage().toString());
            hqlFilter.addOrder("asc");
            hqlFilter.addSort("mmainMileage");
            TCarModelMaintenance nextmodelMaintenance = modelMaintenanceService.getByFilter(hqlFilter);
            if (nextmodelMaintenance != null) {
                carMaintenance.setMainNextMileage(nextmodelMaintenance.getMmainMileage());
            }

            TCarModel tCarModel = carModelService.getById(modelMaintenance.getModelId());
            if (tCarModel != null) {
                try {
                    Date date = addMonth(carMaintenance.getMainTime(), tCarModel.getModelMaintenanceMonth());
                    carMaintenance.setMainNextTime(date);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                super.save(carMaintenance);
            } else {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean updateM(TCarMaintenance carMaintenance) {
        TCarModelMaintenance modelMaintenance = modelMaintenanceService.getById(carMaintenance.getMmainId());
        if (modelMaintenance != null) {
            carMaintenance.setMainCondition(modelMaintenance.getMmainInfo());
            HqlFilter hqlFilter = new HqlFilter();
            hqlFilter.addFilter("QUERY_t#mmainMileage_I_GT", modelMaintenance.getMmainMileage().toString());
            hqlFilter.addOrder("asc");
            hqlFilter.addSort("mmainMileage");
            TCarModelMaintenance nextmodelMaintenance = modelMaintenanceService.getByFilter(hqlFilter);
            if (nextmodelMaintenance != null) {
                carMaintenance.setMainNextMileage(nextmodelMaintenance.getMmainMileage());
            }

            TCarModel tCarModel = carModelService.getById(modelMaintenance.getModelId());
            if (tCarModel != null) {
                try {
                    Date date = addMonth(carMaintenance.getMainTime(), tCarModel.getModelMaintenanceMonth());
                    carMaintenance.setMainNextTime(date);
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
                super.update(carMaintenance);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * @param date  时间值
     * @param month 增加的时间
     * @return
     * @throws ParseException
     */
    public Date addMonth(Date date, int month) throws ParseException {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        Date dt = sdf.parse(date);
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.MONTH, month);
        Date dt1 = rightNow.getTime();
        return dt1;
    }
}
