/**
 * $Id: MtpController.java,v 1.0 2015/01/29 20:35:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.subsystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * <p>MtpController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/mtp")
public class MtpController {
	// ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String goToHome(HttpServletRequest request) {
		return "mtp/home";
	}
	
}
