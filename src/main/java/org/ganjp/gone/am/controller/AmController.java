/**
 * $Id: FreeController.java,v 1.0 2015/01/29 20:35:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.am.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.model.AmSubsystem;
import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.am.service.AmRoleService;
import org.ganjp.gone.am.service.AmRoleSubsystemService;
import org.ganjp.gone.am.service.AmService;
import org.ganjp.gone.am.service.AmSubsystemService;
import org.ganjp.gone.am.service.AmUserService;
import org.ganjp.gone.common.controller.BaseController;
import org.ganjp.gone.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>AdminController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/am")
public class AmController extends BaseController {
	// ------------------------------- Search -----------------------------------------------
	// ------ subsystem
	@RequestMapping(value="/subsystemWithRoleIds/{subsystemId}", method=RequestMethod.GET)
    public @ResponseBody AmSubsystem getAmSubsystem(@PathVariable String subsystemId, HttpServletRequest request, HttpServletResponse response) {
        return amService.getAmSubsystemWithRoleIds(subsystemId);
    }
	@RequestMapping(value="/subsystemPageWithRoleNames", method=RequestMethod.GET)
    public @ResponseBody Page<AmSubsystem> subsystemPageWithRoleNames(HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	int pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	int pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<AmSubsystem> page = amService.getAmSubsystemPageWithRoleNames(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
	// ------ user
	@RequestMapping(value="/userWithRoleIds/{userId}", method=RequestMethod.GET)
    public @ResponseBody AmUser getAmUser(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) {
        return amService.getAmUserWithRoleIds(userId);
    }
	@RequestMapping(value="/userPageWithRoleSubsystemNames", method=RequestMethod.GET)
    public @ResponseBody Page<AmUser> userPageWithRoleSubsystemNames(HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	int pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	int pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<AmUser> page = amService.getAmUserPageWithRoleSubsystemNames(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
	
	 // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/subsystem/role", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> createSubsystemAndRole(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmSubsystem amSubsystem = new AmSubsystem();
    		super.setValue(request, amSubsystem);
    		amService.saveSubsystemRole(amSubsystem, request.getParameter("roleIds"));
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    @RequestMapping(value="/user/role", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> creatUserAndRole(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmUser amUser = new AmUser();
    		super.setValue(request, amUser);
    		amService.saveUserRole(amUser, request.getParameter("roleIds"));
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/subsystem/role/{subsystemId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> updateSubsystemAndRole(@PathVariable String subsystemId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmSubsystem amSubsystem = amSubsystemService.findOne(subsystemId);
    		super.setValue(request, amSubsystem);
    		amService.updateSubsystemRole(amSubsystem, request.getParameter("roleIds"));
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    @RequestMapping(value="/user/role/{userId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> updateUserAndRole(@PathVariable String userId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmUser amUser = amUserService.findOne(userId);
    		super.setValue(request, amUser);
    		amService.updateUserRole(amUser, request.getParameter("roleIds"));
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/subsystem/deleteWithRelation", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> deleteSubsystemWithRelation(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String subsystemIds = request.getParameter("subsystemIds");
    		if (StringUtil.hasText(subsystemIds)) {
    			amService.batchDeleteAmSubsystemWithRelation(subsystemIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    @RequestMapping(value="/user/deleteWithRelation", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> deleteUserWithRelation(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String userIds = request.getParameter("userIds");
    		if (StringUtil.hasText(userIds)) {
    			amService.batchDeleteAmUserWithRelation(userIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
    @Autowired
	private AmUserService amUserService;
    @Autowired
	private AmSubsystemService amSubsystemService;
    @Autowired
	private AmRoleService amRoleService;
    @Autowired
	private AmRoleSubsystemService amRoleSubsystemService;
    
    @Autowired
	private AmService amService;
}
