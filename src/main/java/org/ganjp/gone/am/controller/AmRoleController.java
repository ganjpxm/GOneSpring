/**
 * $Id: AmRoleController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
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
import org.ganjp.gone.am.model.AmRole;
import org.ganjp.gone.am.service.AmRoleService;
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
 * <p>AmRoleController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/am")
public class AmRoleController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/role", method=RequestMethod.GET)
	public ModelAndView goToRolePage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "roleId,roleCd,roleName,displayNo,description,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("am/amRole");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/roles", method=RequestMethod.GET)
    public @ResponseBody List<AmRole> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<AmRole> amRoles = amRoleService.findAll();
        return amRoles;
    }
    @RequestMapping(value="/roleIdNames", method=RequestMethod.GET)
    public @ResponseBody Map<String,String> findRoleIdNames(HttpServletRequest request, HttpServletResponse response) {
        return amRoleService.getRoleIdAndNames();
    }
    @RequestMapping(value="/rolePage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<AmRole> getRolePageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getRolePage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/rolePage", method=RequestMethod.GET)
    public @ResponseBody Page<AmRole> getRolePage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<AmRole> page = amRoleService.getAmRolePage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/role/{roleId}", method=RequestMethod.GET)
    public @ResponseBody AmRole getAmRole(@PathVariable String roleId, HttpServletRequest request, HttpServletResponse response) {
        AmRole amRole = amRoleService.findOne(roleId);
        return amRole;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/role", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmRole amRole = new AmRole();
    		super.setValue(request, amRole);
    		amRoleService.create(amRole);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/role/{roleId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String roleId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmRole amRole = amRoleService.findOne(roleId);
    		super.setValue(request, amRole);
    		amRoleService.update(amRole);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/role/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String roleIds = request.getParameter("roleIds");
    		if (StringUtil.hasText(roleIds)) {
    			amRoleService.batchDelete(roleIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private AmRoleService amRoleService;
}