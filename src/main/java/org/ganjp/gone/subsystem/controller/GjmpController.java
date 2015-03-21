/**
 * $Id: MtpController.java,v 1.0 2015/01/29 20:35:49 GanJianping Exp $
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
 * <p>Gan Jianping Management Platform Controller</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/gjmp")
public class GjmpController extends BaseController {
	// ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="", method=RequestMethod.GET)
	public String goToHome(HttpServletRequest request) {
		AmUser amUserLogin = super.getLoginUser(request);
		if (amUserLogin!=null) {
			String subsystemId = request.getParameter("subsystemId");
			if (StringUtil.hasText(subsystemId)) amUserLogin.setCurrentSubsystemId(subsystemId);
			String subsystemName = request.getParameter("subsystemName");
			if (StringUtil.hasText(subsystemName)) amUserLogin.setCurrentSubsystemName(subsystemName);
		}
		return "gjpmp/home";
	}
	
}
