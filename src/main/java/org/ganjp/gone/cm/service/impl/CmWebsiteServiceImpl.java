/**
 * $Id: CmWebsiteServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.cm.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.ganjp.gone.cm.dao.CmWebsiteDao;
import org.ganjp.gone.cm.model.CmWebsite;
import org.ganjp.gone.cm.service.CmWebsiteService;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>CmWebsiteServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class CmWebsiteServiceImpl extends AbstractService<CmWebsite> implements CmWebsiteService {

    @Autowired
    private CmWebsiteDao dao;

    public CmWebsiteServiceImpl() {
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
     * <p>getCmWebsitePage</p>
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
	public Page<CmWebsite> getCmWebsitePage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getCmWebsitePage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}

	/**
	 * <p>getWebsiteInfos</p>
	 * 
	 * @param keywords
	 * @param field
	 * @param roleIds
	 * @param lang
	 * @param orderBy
	 * @return
	 */
	@Transactional
	public List<Map<String,String>> getWebsiteInfos(String keywords, String field, String roleIds, String lang, String orderBy) {
		List<Map<String,String>> websiteInfos = new ArrayList<Map<String,String>>();
		List<CmWebsite> cmWebsites = dao.getCmWebsites(keywords, field, roleIds, lang, orderBy);
		for (CmWebsite cmWebsite : cmWebsites) {
			Map<String,String> map = new LinkedHashMap<String,String>();
			map.put("uuid", cmWebsite.getWebsiteId());
			map.put("websiteName", cmWebsite.getWebsiteName());
			map.put("websiteUrl",  cmWebsite.getWebsiteUrl());
			map.put("websiteLogoUrl",  cmWebsite.getLogoUrl());
			map.put("tags",  cmWebsite.getTags());
			map.put("displayNo",  cmWebsite.getDisplayNo() + "");
			map.put("roleIds", cmWebsite.getRoleIds());
			map.put("lang", cmWebsite.getLang());
			websiteInfos.add(map);
		}
		return websiteInfos;
	}
	
    @Override
    protected Operations<CmWebsite> getDao() {
        return dao;
    }

}