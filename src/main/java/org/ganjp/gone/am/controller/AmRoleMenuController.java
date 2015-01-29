/**
 * $Id: AmRoleMenuController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
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
import org.ganjp.gone.am.model.AmRoleMenu;
import org.ganjp.gone.am.service.AmRoleMenuService;
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
 * <p>AmRoleMenuController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/am")
public class AmRoleMenuController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/roleMenu", method=RequestMethod.GET)
	public ModelAndView goToRoleMenuPage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "roleMenuId,roleId,menuId,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("am/amRoleMenu");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/roleMenus", method=RequestMethod.GET)
    public @ResponseBody List<AmRoleMenu> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<AmRoleMenu> amRoleMenus = amRoleMenuService.findAll();
        return amRoleMenus;
    }
    @RequestMapping(value="/roleMenuPage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<AmRoleMenu> getRoleMenuPageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getRoleMenuPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/roleMenuPage", method=RequestMethod.GET)
    public @ResponseBody Page<AmRoleMenu> getRoleMenuPage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<AmRoleMenu> page = amRoleMenuService.getAmRoleMenuPage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/roleMenu/{roleMenuId}", method=RequestMethod.GET)
    public @ResponseBody AmRoleMenu getAmRoleMenu(@PathVariable String roleMenuId, HttpServletRequest request, HttpServletResponse response) {
        AmRoleMenu amRoleMenu = amRoleMenuService.findOne(roleMenuId);
        return amRoleMenu;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/roleMenu", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmRoleMenu amRoleMenu = new AmRoleMenu();
    		super.setValue(request, amRoleMenu);
    		amRoleMenuService.create(amRoleMenu);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/roleMenu/{roleMenuId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String roleMenuId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmRoleMenu amRoleMenu = amRoleMenuService.findOne(roleMenuId);
    		super.setValue(request, amRoleMenu);
    		amRoleMenuService.update(amRoleMenu);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/roleMenu/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String roleMenuIds = request.getParameter("roleMenuIds");
    		if (StringUtil.hasText(roleMenuIds)) {
    			amRoleMenuService.batchDelete(roleMenuIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private AmRoleMenuService amRoleMenuService;
}