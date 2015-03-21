/**
 * $Id: CmImageServiceImpl.java,v 1.0 2015/01/28 09:45:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.cm.service.impl;

import java.io.File;
import java.util.List;

import javax.transaction.Transactional;

import org.ganjp.gcore.util.FileUtil;
import org.ganjp.gone.cm.dao.CmImageDao;
import org.ganjp.gone.cm.model.CmImage;
import org.ganjp.gone.cm.service.CmImageService;
import org.ganjp.gone.common.dao.Operations;
import org.ganjp.gone.common.model.Page;
import org.ganjp.gone.common.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



/**
 * <p>CmImageServiceImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Service
public class CmImageServiceImpl extends AbstractService<CmImage> implements CmImageService {

    @Autowired
    private CmImageDao dao;

    public CmImageServiceImpl() {
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
	 * <p>batchDelete</p>
	 * 
	 * @param pks
	 */
    @Transactional
   	public void batchDelete(final String basePath, final String pks) {
    	List<CmImage> cmImages= dao.getCmImagesByUuids(pks);
    	for (CmImage cmImage : cmImages) {
    		String imageUrl = cmImage.getImageUrl();
    		if (imageUrl.indexOf("resources/upload")!=-1) {
    			String filePath = basePath + "/" + imageUrl.substring(imageUrl.indexOf("resources"));
    			FileUtil.delete(new File(filePath));
    		}
    	}
    	dao.batchDelete(pks);
    }
    
    /**
     * <p>getCmImagePage</p>
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
	public Page<CmImage> getCmImagePage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		return dao.getCmImagePage(search, startDate, endDate, dataStates, pageNo, pageSize, orderBy);
	}

	/**
	 * <p>getCmImages</p>
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
	public Page<CmImage> getCmImagePage(String keywords, String field, final String lang, final String startDate, final String endDate, final int pageNo,
			final int pageSize, String ownRoleIds, String orderBy) {
		return dao.getCmImagePage(keywords, field, lang, startDate, endDate, pageNo, pageSize, ownRoleIds, orderBy);
	}
    @Override
    protected Operations<CmImage> getDao() {
        return dao;
    }

}