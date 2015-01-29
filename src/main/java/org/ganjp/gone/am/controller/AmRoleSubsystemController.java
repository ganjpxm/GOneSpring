/**
 * $Id: AmRoleSubsystemController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
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
import org.ganjp.gone.am.model.AmRoleSubsystem;
import org.ganjp.gone.am.service.AmRoleSubsystemService;
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
 * <p>AmRoleSubsystemController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/am")
public class AmRoleSubsystemController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/roleSubsystem", method=RequestMethod.GET)
	public ModelAndView goToRoleSubsystemPage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "amRoleSusbsystemId,subsystemId,roleId,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("am/amRoleSubsystem");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/roleSubsystems", method=RequestMethod.GET)
    public @ResponseBody List<AmRoleSubsystem> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<AmRoleSubsystem> amRoleSubsystems = amRoleSubsystemService.findAll();
        return amRoleSubsystems;
    }
    @RequestMapping(value="/roleSubsystemPage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<AmRoleSubsystem> getRoleSubsystemPageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getRoleSubsystemPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/roleSubsystemPage", method=RequestMethod.GET)
    public @ResponseBody Page<AmRoleSubsystem> getRoleSubsystemPage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<AmRoleSubsystem> page = amRoleSubsystemService.getAmRoleSubsystemPage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/roleSubsystem/{roleSubsystemId}", method=RequestMethod.GET)
    public @ResponseBody AmRoleSubsystem getAmRoleSubsystem(@PathVariable String roleSubsystemId, HttpServletRequest request, HttpServletResponse response) {
        AmRoleSubsystem amRoleSubsystem = amRoleSubsystemService.findOne(roleSubsystemId);
        return amRoleSubsystem;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/roleSubsystem", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmRoleSubsystem amRoleSubsystem = new AmRoleSubsystem();
    		super.setValue(request, amRoleSubsystem);
    		amRoleSubsystemService.create(amRoleSubsystem);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/roleSubsystem/{roleSubsystemId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String roleSubsystemId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmRoleSubsystem amRoleSubsystem = amRoleSubsystemService.findOne(roleSubsystemId);
    		super.setValue(request, amRoleSubsystem);
    		amRoleSubsystemService.update(amRoleSubsystem);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/roleSubsystem/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String roleSubsystemIds = request.getParameter("roleSubsystemIds");
    		if (StringUtil.hasText(roleSubsystemIds)) {
    			amRoleSubsystemService.batchDelete(roleSubsystemIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private AmRoleSubsystemService amRoleSubsystemService;
}