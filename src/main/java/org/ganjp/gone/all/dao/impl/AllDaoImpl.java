/**
 * $Id: AllDaoImpl.java, v1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.all.dao.impl;

import org.ganjp.gone.all.dao.AllDao;
import org.ganjp.gone.common.dao.impl.AbstractHibernateDao;
import org.ganjp.gone.common.model.BaseModel;
import org.springframework.stereotype.Repository;

/**
 * <p>AllDaoImpl</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Repository
public class AllDaoImpl extends AbstractHibernateDao<BaseModel> implements AllDao {
	
    public AllDaoImpl() {
        super();
        setClazz(BaseModel.class);
    }

}