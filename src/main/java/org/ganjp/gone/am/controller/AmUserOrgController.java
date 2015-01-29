/**
 * $Id: AmUserOrgController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
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
import org.ganjp.gone.am.model.AmUserOrg;
import org.ganjp.gone.am.service.AmUserOrgService;
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
 * <p>AmUserOrgController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/am")
public class AmUserOrgController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/userOrg", method=RequestMethod.GET)
	public ModelAndView goToUserOrgPage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "userOrgId,orgId,userId,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("am/amUserOrg");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/userOrgs", method=RequestMethod.GET)
    public @ResponseBody List<AmUserOrg> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<AmUserOrg> amUserOrgs = amUserOrgService.findAll();
        return amUserOrgs;
    }
    @RequestMapping(value="/userOrgPage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<AmUserOrg> getUserOrgPageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getUserOrgPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/userOrgPage", method=RequestMethod.GET)
    public @ResponseBody Page<AmUserOrg> getUserOrgPage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<AmUserOrg> page = amUserOrgService.getAmUserOrgPage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/userOrg/{userOrgId}", method=RequestMethod.GET)
    public @ResponseBody AmUserOrg getAmUserOrg(@PathVariable String userOrgId, HttpServletRequest request, HttpServletResponse response) {
        AmUserOrg amUserOrg = amUserOrgService.findOne(userOrgId);
        return amUserOrg;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/userOrg", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmUserOrg amUserOrg = new AmUserOrg();
    		super.setValue(request, amUserOrg);
    		amUserOrgService.create(amUserOrg);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/userOrg/{userOrgId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String userOrgId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmUserOrg amUserOrg = amUserOrgService.findOne(userOrgId);
    		super.setValue(request, amUserOrg);
    		amUserOrgService.update(amUserOrg);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/userOrg/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String userOrgIds = request.getParameter("userOrgIds");
    		if (StringUtil.hasText(userOrgIds)) {
    			amUserOrgService.batchDelete(userOrgIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private AmUserOrgService amUserOrgService;
}