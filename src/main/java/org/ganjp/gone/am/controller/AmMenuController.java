/**
 * $Id: AmMenuController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
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
import org.ganjp.gone.am.model.AmMenu;
import org.ganjp.gone.am.service.AmMenuService;
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
 * <p>AmMenuController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/am")
public class AmMenuController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/menu", method=RequestMethod.GET)
	public ModelAndView goToMenuPage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "menuId,menuCd,menuName,url,imageUrl,displayNo,displayLevel,endFlag,lang,operatorId,operatorName,createDateTime,modifyTimestamp,dataStatus,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("am/amMenu");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/menus", method=RequestMethod.GET)
    public @ResponseBody List<AmMenu> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<AmMenu> amMenus = amMenuService.findAll();
        return amMenus;
    }
    @RequestMapping(value="/menuPage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<AmMenu> getMenuPageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getMenuPage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/menuPage", method=RequestMethod.GET)
    public @ResponseBody Page<AmMenu> getMenuPage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<AmMenu> page = amMenuService.getAmMenuPage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/menu/{menuId}", method=RequestMethod.GET)
    public @ResponseBody AmMenu getAmMenu(@PathVariable String menuId, HttpServletRequest request, HttpServletResponse response) {
        AmMenu amMenu = amMenuService.findOne(menuId);
        return amMenu;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/menu", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmMenu amMenu = new AmMenu();
    		super.setValue(request, amMenu);
    		amMenuService.create(amMenu);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/menu/{menuId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String menuId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		AmMenu amMenu = amMenuService.findOne(menuId);
    		super.setValue(request, amMenu);
    		amMenuService.update(amMenu);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/menu/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String menuIds = request.getParameter("menuIds");
    		if (StringUtil.hasText(menuIds)) {
    			amMenuService.batchDelete(menuIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private AmMenuService amMenuService;
}