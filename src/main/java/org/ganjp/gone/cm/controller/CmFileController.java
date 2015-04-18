/**
 * $Id: CmFileController.java,v 1.0 2014/12/18 22:17:49 GanJianping Exp $
 *
 * Copyright (c) 2012 Gan Jianping. All rights reserved
 * GOne Project
 *
 */
package org.ganjp.gone.cm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.cm.model.CmFile;
import org.ganjp.gone.cm.service.CmFileService;
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
 * <p>CmFileController</p>
 * 
 * @author GanJianping
 * @since 1.0
 */
@Controller
@RequestMapping("/cm")
public class CmFileController extends BaseController {

    // ------------------------------- Go to page -----------------------------------------------
	@RequestMapping(value="/file", method=RequestMethod.GET)
	public ModelAndView goToFilePage(HttpServletRequest request) {
		
		request.setAttribute("fieldNames", "fileId,fileCd,fileName,fileUrl,tags,roleIds,lang,operatorId,operatorName,dataStatus,createDateTime,modifyTimestamp,displayNo,sendStatus,sendDateTime,receiveDateTime");
		ModelAndView modelAndView = new ModelAndView("cm/cmFile");
		
		request.setAttribute("pageNo", StringUtil.isEmpty(request.getParameter("pageNo"))?1:request.getParameter("pageNo"));
		request.setAttribute("pageSize",  StringUtil.isEmpty(request.getParameter("pageSize"))?100:request.getParameter("pageSize"));
		return modelAndView;
	}
	
	// ------------------------------- search to get json data -----------------------------------------------
    @RequestMapping(value="/files", method=RequestMethod.GET)
    public @ResponseBody List<CmFile> findAll(HttpServletRequest request, HttpServletResponse response) {
        List<CmFile> cmFiles = cmFileService.findAll();
        return cmFiles;
    }
    @RequestMapping(value="/filePage/{pageNo}/{pageSize}", method=RequestMethod.GET)
    public @ResponseBody Page<CmFile> getFilePageWithParam(@PathVariable String pageNo, @PathVariable String pageSize, 
    		HttpServletRequest request, HttpServletResponse response) {
    	return this.getFilePage(Integer.valueOf(pageNo), Integer.valueOf(pageSize), request, response);
    }
    @RequestMapping(value="/filePage", method=RequestMethod.GET)
    public @ResponseBody Page<CmFile> getFilePage(Integer pageNo, Integer pageSize, HttpServletRequest request, HttpServletResponse response) {
    	String search = request.getParameter("search");
    	if (pageNo==null) pageNo = StringUtil.isEmpty(request.getParameter("pageNo"))?1:Integer.parseInt(request.getParameter("pageNo"));
    	if (pageSize==null) pageSize = StringUtil.isEmpty(request.getParameter("pageSize"))?100:Integer.parseInt(request.getParameter("pageSize"));
		String startDate = StringUtil.isEmpty(request.getParameter("startDate"))?null:request.getParameter("startDate");
		String endDate = StringUtil.isEmpty(request.getParameter("endDate"))?null:request.getParameter("endDate");
		
    	Page<CmFile> page = cmFileService.getCmFilePage(search, startDate, endDate, "", pageNo, pageSize, "");
        return page;
    }
    @RequestMapping(value="/file/{fileId}", method=RequestMethod.GET)
    public @ResponseBody CmFile getCmFile(@PathVariable String fileId, HttpServletRequest request, HttpServletResponse response) {
        CmFile cmFile = cmFileService.findOne(fileId);
        return cmFile;
    }
    
    // ------------------------------- Create -----------------------------------------------
    @RequestMapping(value="/file", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> create(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		CmFile cmFile = new CmFile();
    		super.setValue(request, cmFile);
    		cmFileService.create(cmFile);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Update -----------------------------------------------
    @RequestMapping(value="/file/{fileId}", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> update(@PathVariable String fileId, HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	try {
    		CmFile cmFile = cmFileService.findOne(fileId);
    		super.setValue(request, cmFile);
    		cmFileService.update(cmFile);
    		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    	} catch(Exception ex) {
    		map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	}
    	return map;
    }
    
    // ------------------------------- Delete -----------------------------------------------
    @RequestMapping(value="/file/delete", method = RequestMethod.POST)
    public @ResponseBody Map<String,String> delete(HttpServletRequest request, HttpServletResponse response) {
    	Map<String,String> map = new HashMap<String,String>();
    	map.put(Const.KEY_RESULT, Const.VALUE_FAIL);
    	try {
    		String fileIds = request.getParameter("fileIds");
    		if (StringUtil.hasText(fileIds)) {
    			cmFileService.batchDelete(fileIds);
        		map.put(Const.KEY_RESULT, Const.VALUE_SUCCESS);
    		}
    	} catch(Exception ex) {
    		log.error(ex.getMessage());
    	}
    	return map;
    }
    
	@Autowired
	private CmFileService cmFileService;
}