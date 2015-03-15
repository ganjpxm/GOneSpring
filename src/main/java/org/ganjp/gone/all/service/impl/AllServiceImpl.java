/**
 * $Id: AllServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.all.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ganjp.gone.all.service.AllService;
import org.ganjp.gone.am.dao.AmDao;
import org.ganjp.gone.am.service.AmSubsystemService;
import org.ganjp.gone.bm.model.BmConfig;
import org.ganjp.gone.bm.service.BmConfigService;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.BaseModel;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>AllServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class AllServiceImpl extends AbstractService<BaseModel> implements AllService {

    /**
     * <p>getBmConfigPageWithSubsystemNameSections</p>
     * 
     * @param search
     * @param startDate
     * @param endDate
     * @param dataStates
     * @param pageNo
     * @param pageSize
     * @param orderBy
     * @return
     */
    public Page<BmConfig> getBmConfigPageWithSubsystemNameSections(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
    	Page<BmConfig> page = bmConfigService.getBmConfigPage(search, startDate, endDate, "", pageNo, pageSize, "");
    	List<BmConfig> bmConfigs = page.getResult();
    	Map<String,List<BmConfig>> subsystemNameAndBmConfigs = new LinkedHashMap<String,List<BmConfig>>();
    	Map<String,String> subsystemIdAndNames = amSubsystemService.getSubsystemIdAndNames();
		for (BmConfig bmConfig : bmConfigs) {
			String subsystemName = subsystemIdAndNames.get(bmConfig.getSubsystemId());
			if (subsystemNameAndBmConfigs.containsKey(subsystemName)) {
				subsystemNameAndBmConfigs.get(subsystemName).add(bmConfig);
			} else {
				List<BmConfig> bmConfigList = new ArrayList<BmConfig>(); 
				bmConfigList.add(bmConfig);
				subsystemNameAndBmConfigs.put(subsystemName, bmConfigList);
			}
		}
		page.setResultMap(subsystemNameAndBmConfigs);
    	return page;
    }
    
    @Autowired
	private BmConfigService bmConfigService;
	@Autowired
	private AmSubsystemService amSubsystemService;
    
	@Autowired
	private AmDao dao;
	 
    @Override
    protected Operations<BaseModel> getDao() {
        return dao;
    }
}