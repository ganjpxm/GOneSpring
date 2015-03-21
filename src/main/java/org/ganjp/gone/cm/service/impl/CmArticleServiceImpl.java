/**
 * $Id: CmArticleServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.cm.service.impl;

import javax.transaction.Transactional;

import org.ganjp.gone.cm.model.CmArticle;
import org.ganjp.gone.cm.service.CmArticleService;
import org.ganjp.gone.cm.dao.CmArticleDao;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>CmArticleServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class CmArticleServiceImpl extends AbstractService<CmArticle> implements CmArticleService {

    @Autowired
    private CmArticleDao dao;

    public CmArticleServiceImpl() {
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
     * <p>getCmArticlePage</p>
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
	public Page<CmArticle> getCmArticlePage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getCmArticlePage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}
	
	/**
	 * <p>getCmArticlePage</p>
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
	@Transactional
	public Page<CmArticle> getCmArticlePage(String keywords, String field, final String lang, final String startDate, final String endDate, final int pageNo,
			final int pageSize, String ownRoleIds, String orderBy) {
		return dao.getCmArticlePage(keywords, field, lang, startDate, endDate, pageNo, pageSize, ownRoleIds, orderBy);
	}

    @Override
    protected Operations<CmArticle> getDao() {
        return dao;
    }

}