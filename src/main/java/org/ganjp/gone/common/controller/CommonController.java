/**
 * $Id: FreeController.java,v 1.0 2015/01/29 20:35:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.common.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.am.service.AmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>FreeController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
public class CommonController extends BaseController {
	// ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String goToLoginPage(HttpServletRequest request) {
		return "common/login";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/spring/login";
	}
	
	// ------------------------------- Json -----------------------------------------------
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public @ResponseBody Map<String,String> login(HttpServletRequest request) {
		Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String userCdOrEmailOrMobileNumber = request.getParameter("userCdOrEmailOrMobileNumber");
    		String password = request.getParameter("password");
    		if (StringUtil.hasText(userCdOrEmailOrMobileNumber) && StringUtil.hasText(password)) {
    			AmUser amUser = amService.getAmUserWithRoleSubsystemIds(userCdOrEmailOrMobileNumber, password);
    			if (amUser!=null && StringUtil.hasText(amUser.getRoleIds()) && StringUtil.hasText(amUser.getSubsystemIds()) 
    						&& StringUtil.hasText(amUser.getDefaultSubsystemId())) {
    				HttpSession session = request.getSession();
    				synchronized (session) {
    					session.setAttribute(Const.KEY_USER, amUser);
    					session.setAttribute(Const.LANGUAGE, Const.LANGUAGE_EN_SG);
    					String url = amUser.getDefaultSubsystemHomeUrl();
    					if (url.indexOf("http")==-1) {
    						url = super.getBasePath(request) + url; 
    					}
    					map.put("url", url);
    				}
    				map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    			}
    		}
		} catch(Exception ex) {
			log.error(ex.getMessage());
		}
		return map;
	}
	
	@Autowired
	private AmService amService;
}
