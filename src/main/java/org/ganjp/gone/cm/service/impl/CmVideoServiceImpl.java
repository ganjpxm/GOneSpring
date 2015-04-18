/**
 * $Id: CmVideoServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.cm.service.impl;

import javax.transaction.Transactional;

import org.ganjp.gone.cm.dao.CmVideoDao;
import org.ganjp.gone.cm.model.CmVideo;
import org.ganjp.gone.cm.service.CmVideoService;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>CmVideoServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class CmVideoServiceImpl extends AbstractService<CmVideo> implements CmVideoService {

    @Autowired
    private CmVideoDao dao;

    public CmVideoServiceImpl() {
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
     * <p>getCmVideoPage</p>
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
	public Page<CmVideo> getCmVideoPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getCmVideoPage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}

	/**
	 * <p>getCmVideos</p>
	 * 
	 * @param keywords
	 * @param field
	 * @param lang
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @param ownRoleIds
	 * @param orderBy
	 * @return
	 */
	public Page<CmVideo> getCmVideoPage(String keywords, String field, final String lang, final String startDate, final String endDate, final int pageNo,
			final int pageSize, String ownRoleIds, String orderBy) {
		return dao.getCmVideoPage(keywords, field, lang, startDate, endDate, pageNo, pageSize, ownRoleIds, orderBy);
	}
	
    @Override
    protected Operations<CmVideo> getDao() {
        return dao;
    }

}