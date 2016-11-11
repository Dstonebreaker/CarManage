package com.car.service.insurance;

import com.car.entity.insurance.TCarInsurance;
import com.system.service.base.BaseServiceI;

import java.util.Date;

public interface CarInsuranceServiceI extends BaseServiceI<TCarInsurance> {
    public String getMaxTime(String carId,String typeId,String id);
}
