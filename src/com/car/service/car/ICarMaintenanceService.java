package com.car.service.car;

import com.car.entity.car.TCarMaintenance;
import com.system.service.base.BaseServiceI;

public interface ICarMaintenanceService extends BaseServiceI<TCarMaintenance> {
    /**
     * @param tCarMaintenance
     * @param mmainId 模型id
     * @return
     */
    public boolean saveM(TCarMaintenance tCarMaintenance);

    /**
     * @param tCarMaintenance
     * @param mmainId 模型id
     * @return
     */
    public boolean updateM(TCarMaintenance tCarMaintenance);
}
