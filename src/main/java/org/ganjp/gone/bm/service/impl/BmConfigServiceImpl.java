/**
 * $Id: BmConfigServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.bm.service.impl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.ganjp.gone.bm.dao.BmConfigDao;
import org.ganjp.gone.bm.model.BmConfig;
import org.ganjp.gone.bm.service.BmConfigService;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>BmConfigServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class BmConfigServiceImpl extends AbstractService<BmConfig> implements BmConfigService {

    @Autowired
    private BmConfigDao dao;

    public BmConfigServiceImpl() {
        super();
    }

    /**
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
    @Transactional
   	public void batchDelete(final String pks) {
    	dao.batchDelete(pks);
    }
    
    /**
     * <p>getBmConfigPage</p>
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
	public Page<BmConfig> getBmConfigPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getBmConfigPage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}

	/**
	 * <p>getConfigCdAndInfos</p>
	 * 
	 * @param configCds
	 * @param lang
	 * @return
	 */
	@Transactional
	public Map<String, Map<String,String>> getConfigCdAndInfos(final String configCds, final String lang) {
		List<Map<String,String>> configInfos = dao.getConfigInfos(configCds, lang);
		Map<String, Map<String,String>> configCdAndInfos = new LinkedHashMap<String, Map<String,String>>();
		for (Map<String,String> configInfo : configInfos) {
			configCdAndInfos.put(configInfo.get("cd"), configInfo);
		}
		return configCdAndInfos;
	}
	
	/**
	 * <p>getConfigCdLangAndInfos</p>
	 * 
	 * @param configCds
	 * @param lang
	 * @return
	 */
	@Transactional
	public Map<String, Map<String,String>> getConfigCdLangAndInfos(final String configCds, final String lang) {
		List<Map<String,String>> configInfos = dao.getConfigInfos(configCds, lang);
		Map<String, Map<String,String>> configCdLangAndInfos = new LinkedHashMap<String, Map<String,String>>();
		for (Map<String,String> configInfo : configInfos) {
			configCdLangAndInfos.put(configInfo.get("cd")+configInfo.get("lang"), configInfo);
		}
		return configCdLangAndInfos;
	}
	
    @Override
    protected Operations<BmConfig> getDao() {
        return dao;
    }

}