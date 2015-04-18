/**
 * $Id: CmFileServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.cm.service.impl;

import javax.transaction.Transactional;

import org.ganjp.gone.cm.dao.CmFileDao;
import org.ganjp.gone.cm.model.CmFile;
import org.ganjp.gone.cm.service.CmFileService;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>CmFileServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class CmFileServiceImpl extends AbstractService<CmFile> implements CmFileService {

    @Autowired
    private CmFileDao dao;

    public CmFileServiceImpl() {
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
     * <p>getCmFilePage</p>
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
	public Page<CmFile> getCmFilePage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getCmFilePage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}

	/**
	 * <p>getCmFiles</p>
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
	public Page<CmFile> getCmFilePage(String keywords, String field, final String lang, final String startDate, final String endDate, final int pageNo,
			final int pageSize, String ownRoleIds, String orderBy) {
		return dao.getCmFilePage(keywords, field, lang, startDate, endDate, pageNo, pageSize, ownRoleIds, orderBy);
	}
	
    @Override
    protected Operations<CmFile> getDao() {
        return dao;
    }

}