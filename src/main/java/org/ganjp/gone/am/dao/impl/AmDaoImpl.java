/**
 * $Id: AmMenuDaoImpl.java, v1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.dao.impl;

import org.ganjp.gone.am.dao.AmDao;
import org.ganjp.gone.common.dao.impl.AbstractHibernateDao;
import org.ganjp.gone.common.model.BaseModel;
import org.springframework.stereotype.Repository;

/**
 * <p>AmMenuDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Repository
public class AmDaoImpl extends AbstractHibernateDao<BaseModel> implements AmDao {
	
    public AmDaoImpl() {
        super();
        setClazz(BaseModel.class);
    }

}