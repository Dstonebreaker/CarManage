package com.system.service.base;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.util.HqlFilter;
import com.system.entity.maintain.SysSetting;


public class settValue extends BaseServiceImpl<SysSetting>{
   private static  settValue value;
   private Map<String, Integer>  valueMap = new HashMap<String, Integer>();
   
   
   private settValue(){   
      }
   public static settValue getSettValue(){
	    if(value==null){
	    value  = new settValue();
	     }	   
	   return value;
      }
   public int getValue(String key){
	  if(valueMap==null){
	  valueMap =  value.findByFilter(); 
	  } 
	  return valueMap.get(key);
	//   return 1;
   }
    public  Map<String, Integer> findByFilter(){
    HqlFilter hqlFilter = new HqlFilter();
 	   List<SysSetting>  list= value.findByFilter(hqlFilter);
 	   Map<String, Integer>  valueMaps = new HashMap<String, Integer>();
 	   for(SysSetting setting : list){
 		  valueMaps.put(setting.getSettId(), setting.getSettValue());
 	   }
    	return  valueMaps;
    }
}
