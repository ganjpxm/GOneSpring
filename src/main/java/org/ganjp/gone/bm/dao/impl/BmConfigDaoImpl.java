/**
 * $Id: BmConfigDaoImpl.java, v1.0 2014/12/17 11:31:49 GanJianping Exp $
 *
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.bm.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.DateUtil;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.bm.dao.BmConfigDao;
import org.ganjp.gone.bm.model.BmConfig;
import org.ganjp.gone.common.dao.impl.AbstractHibernateDao;
import org.ganjp.gone.common.model.Page;
import org.springframework.stereotype.Repository;

/**
 * <p>BmConfigDao</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Repository
public class BmConfigDaoImpl extends AbstractHibernateDao<BmConfig> implements BmConfigDao {

    public BmConfigDaoImpl() {
        super();

        setClazz(BmConfig.class);
    }

    /**
   	 * <p>batchDelete</p>
   	 * 
   	 * @param pks
   	 */
    public void batchDelete(final String pks) {
    	String hql = "delete from BmConfig where configId in (" + StringUtil.getStrWithQuote(pks) + ")";
		batchExecute(hql);
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
		String hql = "select a from BmConfig a where 1=1 ";
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
	 * <p>getConfigCdAndInfos</p>
	 * 
	 * @param configCds : all, tags
	 * @param lang
	 * @return
	 */
	public List<Map<String,String>> getConfigInfos(final String configCds, final String lang) {
		List<Map<String,String>> configInfos = new ArrayList<Map<String,String>>();
		String hql = "select configId, configCd, configValue, lang from BmConfig where dataStatus=? ";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(Const.DB_DATASTATE_NORMAL);
		if (StringUtil.hasText(lang) && !"all".equalsIgnoreCase(lang)) {
			hql += " and lang = ? ";
			paramList.add(lang);
		}
		
		if (StringUtil.hasText(configCds)) {
			if (!"all".equals(configCds)) {
				String[] configCdArr = configCds.split(",");
				hql +=" and (";
				for (int i=0; i<configCdArr.length; i++) {
					if (i>0) {
						hql +=" or ";
					}
					hql +=" configCd = ? ";
					paramList.add(configCdArr[i]);
				}
				hql +=") ";
			}
		} else {
			return configInfos;
		}
		
		List<Object[]> configInfoArrs = findByHql(hql, paramList.toArray());
		for (Object[] configInfo : configInfoArrs) {
			Map<String,String> map = new LinkedHashMap<String,String>();
			String value = StringUtil.toString(configInfo[2]);
			map.put("uuid", StringUtil.toString(configInfo[0]));
			map.put("cd", StringUtil.toString(configInfo[1]));
			map.put("value", value);
			map.put("lang", StringUtil.toString(configInfo[3]));
			String category = "";
			if (value.indexOf(":")!=-1 && value.indexOf(";")!=-1 ) {
				String[] valueArr = value.split(";");
				for (String val : valueArr) {
					if (StringUtil.hasText(category)) {
						category += "," + val.split(":")[0];
					} else {
						category = val.split(":")[0];
					}
				}
			}
			map.put("category", category);
			configInfos.add(map);
		}
		return configInfos;
	}

}