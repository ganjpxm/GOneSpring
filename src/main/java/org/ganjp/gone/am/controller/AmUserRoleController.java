/**
 * $Id: AmUserRoleController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
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
import org.ganjp.gone.am.model.AmUserRole;
import org.ganjp.gone.am.service.AmUserRoleService;
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
 * <p>AmUserRoleController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/am")
public class AmUserRoleController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/userRole", method=RequestMethod.GET)
	public ModelAndView goToUserRolePage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "userRoleId,userId,roleId,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("am/amUserRole");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/userRoles", method=RequestMethod.GET)
    public @ResponseBody List<AmUserRole> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<AmUserRole> amUserRoles = amUserRoleService.findAll();
        return amUserRoles;
    }
    @RequestMapping(value="/userRolePage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<AmUserRole> getUserRolePageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getUserRolePage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/userRolePage", method=RequestMethod.GET)
    public @ResponseBody Page<AmUserRole> getUserRolePage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<AmUserRole> page = amUserRoleService.getAmUserRolePage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/userRole/{userRoleId}", method=RequestMethod.GET)
    public @ResponseBody AmUserRole getAmUserRole(@PathVariable String userRoleId, HttpServletRequest request, HttpServletResponse response) {
        AmUserRole amUserRole = amUserRoleService.findOne(userRoleId);
        return amUserRole;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/userRole", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmUserRole amUserRole = new AmUserRole();
    		super.setValue(request, amUserRole);
    		amUserRoleService.create(amUserRole);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/userRole/{userRoleId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String userRoleId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmUserRole amUserRole = amUserRoleService.findOne(userRoleId);
    		super.setValue(request, amUserRole);
    		amUserRoleService.update(amUserRole);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/userRole/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String userRoleIds = request.getParameter("userRoleIds");
    		if (StringUtil.hasText(userRoleIds)) {
    			amUserRoleService.batchDelete(userRoleIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private AmUserRoleService amUserRoleService;
}