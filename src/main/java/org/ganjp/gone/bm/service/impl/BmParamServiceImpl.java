/**
 * $Id: BmParamServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.bm.service.impl;

import javax.transaction.Transactional;

import org.ganjp.gone.bm.model.BmParam;
import org.ganjp.gone.bm.service.BmParamService;
import org.ganjp.gone.bm.dao.BmParamDao;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>BmParamServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class BmParamServiceImpl extends AbstractService<BmParam> implements BmParamService {

    @Autowired
    private BmParamDao dao;

    public BmParamServiceImpl() {
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
     * <p>getBmParamPage</p>
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
	public Page<BmParam> getBmParamPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getBmParamPage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}

    @Override
    protected Operations<BmParam> getDao() {
        return dao;
    }

}