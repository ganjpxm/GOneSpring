/**
 * $Id: MmpController.java,v 1.0 2015/01/29 20:35:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.subsystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>GdemoController : GDemo Website Controller</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/gdemo")
public class GdemoController extends BaseController {
	// ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="", method=RequestMethod.GET)
	public String goToUserPage(HttpServletRequest request) {
		initValue(request);
		return "gdemo/home";
	}
	
	@RequestMapping(value="/html5/basic", method=RequestMethod.GET)
	public String goToHtml5js(HttpServletRequest request) {
		initValue(request);
		return "gdemo/html5/basic";
	}
	
	@RequestMapping(value="/angularjs/basic", method=RequestMethod.GET)
	public String goToAngularjs(HttpServletRequest request) {
		initValue(request);
		return "gdemo/angularjs/basic";
	}
	
	private void initValue(HttpServletRequest request) {
		AmUser amUserLogin = super.getLoginUser(request);
		if (amUserLogin!=null) {
			String subsystemId = request.getParameter("subsystemId");
			if (StringUtil.hasText(subsystemId)) amUserLogin.setCurrentSubsystemId(subsystemId);
			String subsystemName = request.getParameter("subsystemName");
			if (StringUtil.hasText(subsystemName)) amUserLogin.setCurrentSubsystemName(subsystemName);
		}
	}
}
