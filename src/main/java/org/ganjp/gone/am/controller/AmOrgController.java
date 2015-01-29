/**
 * $Id: AmOrgController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.model.AmOrg;
import org.ganjp.gone.am.service.AmOrgService;
import org.ganjp.gone.common.controller.BaseController;
import org.ganjp.gone.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * <p>AmOrgController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/am")
public class AmOrgController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/org", method=RequestMethod.GET)
	public ModelAndView goToOrgPage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "orgId,orgCd,orgName,description,displayNo,displayLevel,endFlag,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("am/amOrg");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/orgs", method=RequestMethod.GET)
    public @ResponseBody List<AmOrg> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<AmOrg> amOrgs = amOrgService.findAll();
        return amOrgs;
    }
    @RequestMapping(value="/orgPage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<AmOrg> getOrgPageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getOrgPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/orgPage", method=RequestMethod.GET)
    public @ResponseBody Page<AmOrg> getOrgPage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<AmOrg> page = amOrgService.getAmOrgPage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/org/{orgId}", method=RequestMethod.GET)
    public @ResponseBody AmOrg getAmOrg(@PathVariable String orgId, HttpServletRequest request, HttpServletResponse response) {
        AmOrg amOrg = amOrgService.findOne(orgId);
        return amOrg;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/org", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmOrg amOrg = new AmOrg();
    		super.setValue(request, amOrg);
    		amOrgService.create(amOrg);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/org/{orgId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String orgId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmOrg amOrg = amOrgService.findOne(orgId);
    		super.setValue(request, amOrg);
    		amOrgService.update(amOrg);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/org/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String orgIds = request.getParameter("orgIds");
    		if (StringUtil.hasText(orgIds)) {
    			amOrgService.batchDelete(orgIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private AmOrgService amOrgService;
}