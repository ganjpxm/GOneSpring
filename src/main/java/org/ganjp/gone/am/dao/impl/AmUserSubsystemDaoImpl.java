/**
 * $Id: AmUserSubsystemDaoImpl.java, v1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.ganjp.gcore.util.DateUtil;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.model.AmUserSubsystem;
import org.ganjp.gone.am.dao.AmUserSubsystemDao;
import org.ganjp.gone.common.dao.impl.AbstractHibernateDao;
import org.ganjp.gone.common.model.Page;
import org.springframework.stereotype.Repository;

/**
 * <p>AmUserSubsystemDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Repository
public class AmUserSubsystemDaoImpl extends AbstractHibernateDao<AmUserSubsystem> implements AmUserSubsystemDao {

    public AmUserSubsystemDaoImpl() {
        super();

        setClazz(AmUserSubsystem.class);
    }

    /**
   	 * <p>batchDelete</p>
   	 * 
   	 * @param pks
   	 */
    public void batchDelete(final String pks) {
    	String hql = "delete from AmUserSubsystem where userSubsystemId in (" + StringUtil.getStrWithQuote(pks) + ")";
		batchExecute(hql);
    }
    
    /**
     * <p>getAmUserSubsystemPage</p>
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
	public Page<AmUserSubsystem> getAmUserSubsystemPage(final String search, final String startDate, final String endDate, final String dataStates,
			 final int pageNo, final int pageSize, final String orderBy) {
		String hql = "select a from AmUserSubsystem a where 1=1 ";
		List<Object> paramList = new ArrayList<Object>();
		
		if (StringUtil.isNotEmpty(startDate)) {
			hql += " and a.modifyTimestamp>=? ";
			paramList.add(DateUtil.parseDateOrDateTime(startDate));
		}
		if (StringUtil.isNotEmpty(endDate)) {
			hql += " and a.modifyTimestamp<=? ";
			paramList.add(DateUtil.parseDateOrDateTime(endDate));
		}
		
		if (StringUtil.hasText(dataStates)) {
			String[] dataStateArr = dataStates.split(",");
			hql += " and ( ";
			for (int i = 0; i < dataStateArr.length; i++) {
				if (i > 0) {
					hql += " or ";
				} else {
					hql += " a.dataStatus = ? ";
				}
				paramList.add(dataStateArr[i]);
			}
			hql += " ) ";
		}
		
		if (StringUtil.hasText(search) && search.indexOf("and ")!=-1) {
			hql += search;
		}
		
		if (StringUtil.hasText(orderBy)) {
			hql += " order by " + orderBy;
		} else {
			hql += " order by modifyTimestamp desc ";
		}
		
		return fetchPageByHql(pageNo, pageSize, hql, paramList.toArray());
	}
	
	/**
	 * <p>getUserIdsBySubsystemId</p>
	 * 
	 * @param subsystemId
	 * @return
	 */
	public List<String> getUserIdsBySubsystemId(final String subsystemId) {
		String hql = "select userId from AmUserSubsystem where subsystemId = ?";
		return findByHql(hql, subsystemId);
	}
	
	/**
	 * <p>getSubsystemIdsByUserId</p>
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> getSubsystemIdsByUserId(final String userId) {
		String hql = "select subsystem from AmUserSubsystem where userId = ?";
		return findByHql(hql, userId);
	}

}