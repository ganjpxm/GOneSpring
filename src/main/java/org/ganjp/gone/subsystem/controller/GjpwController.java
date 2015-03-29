/**
 * $Id: GanJianpingController.java,v 1.0 2013/02/06 23:34:49 GanJianping Exp $
 *
 * Copyright (c) 2015 Gan Jianping. All rights reserved
 * Jpw Project
 *
 */
package org.ganjp.gone.subsystem.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ganjp.gcore.Const;
import org.ganjp.gcore.util.CollectionUtil;
import org.ganjp.gcore.util.DateUtil;
import org.ganjp.gcore.util.FileUtil;
import org.ganjp.gcore.util.StringUtil;
import org.ganjp.gone.am.model.AmUser;
import org.ganjp.gone.am.service.AmRoleService;
import org.ganjp.gone.am.service.AmService;
import org.ganjp.gone.am.service.AmUserRoleService;
import org.ganjp.gone.am.service.AmUserService;
import org.ganjp.gone.bm.model.BmConfig;
import org.ganjp.gone.bm.service.BmConfigService;
import org.ganjp.gone.cm.model.CmArticle;
import org.ganjp.gone.cm.model.CmImage;
import org.ganjp.gone.cm.model.CmWebsite;
import org.ganjp.gone.cm.service.CmArticleService;
import org.ganjp.gone.cm.service.CmImageService;
import org.ganjp.gone.cm.service.CmWebsiteService;
import org.ganjp.gone.common.controller.BaseController;
import org.ganjp.gone.common.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>The Gan Jianping Personal Website Controller class implements an public restful URL that will return json data</p>
 * 
 * @author GanJianping
 * @since v1.0
 */
@Controller
@RequestMapping("/gjpw")
public class GjpwController extends BaseController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String toHome(HttpServletRequest request) {
        return "gjpw/home";
	}
	
	/**
	 * <p>User login and return json data with user info (include roleIdNames)</p>
	 * 
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public  @ResponseBody Map<String,Object> login(HttpServletRequest request, HttpServletResponse response) {
		super.setCORS(response);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "fail");
		String userCdOrEmailOrMobileNumber = request.getParameter("userCd");
		String password = request.getParameter("password");
		String subsystemId = request.getParameter("subsystemId");
		
		List<String> roleIdNames = null;
		try {
			//String newPassword = EncryptUtil.encryptByBase64(password);
			roleIdNames = amService.getRoleIdNames(userCdOrEmailOrMobileNumber, password, subsystemId);
			if (roleIdNames != null && !roleIdNames.isEmpty()) {
				AmUser amUser = amUserService.getAmUser(userCdOrEmailOrMobileNumber, password);
				
				Map<String,String> dataMap = new LinkedHashMap<String,String>();
				dataMap.put("userId", amUser.getUserId());
				dataMap.put("userCd", amUser.getUserCd());
				dataMap.put("userName", amUser.getUserName());
				dataMap.put("mobileNumber", amUser.getMobileNumber());
				dataMap.put("email", amUser.getEmail());
				dataMap.put("photoUrl", amUser.getPhotoUrl());
				dataMap.put("roleIdNames", CollectionUtil.getStringWithSplit(roleIdNames, "_"));
				
				map.put("data", dataMap);
				map.put("result", "success");
			} else {
				map.put("result", "noRole");
			}
		} catch (Exception ex) {
			log.error(ex.getMessage());
			map.put("result", "error");
		}
		return map;
	}
	
	/** -----------------------------------------------------Basic value : bmconfig for tags ----------------------------------------------
	/**
	 * <p>Get json data with config value by configCd and lang</p>
	 * <pre>
	 *   http://localhost:8080/gone/spring/gjpw/tags/configValue/websiteTags/en_SG ==> {"result":"success/fail","data":"Popular,News"}
	 *   http://localhost:8080/gone/web/configValue/websiteTags/zh_CN ==> {"result":"success/fail","data":"流行网站,新闻"}
	 * </pre>
	 * 
	 * @param configCd 
	 * @param lang
	 * @param request
	 * 
	 * @return json data
	 */
	@RequestMapping(value = "/tags/{tagCds}/{lang}", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getJsonMapWithTags(@PathVariable String tagCds, @PathVariable String lang, HttpServletRequest request, 
			HttpServletResponse response) {
		super.setCORS(response);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "fail");
		try {
			if (StringUtil.hasText(lang) && !"all".equalsIgnoreCase(lang)) {
				map.put("data", bmConfigService.getConfigCdAndInfos(tagCds, lang));
			} else {
				map.put("data", bmConfigService.getConfigCdLangAndInfos(tagCds, lang));
			}
			
			map.put("result", "success");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
        return map;
	}
	
	/**
	 * <p>save tags</p>
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/tags/save", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveTag(HttpServletRequest request, HttpServletResponse response) {
		super.setCORS(response);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "fail");
		try {
			String uuid = request.getParameter("uuid");
			String tags = request.getParameter("tags");
			if (StringUtil.hasText(uuid) && uuid.length()==32 && StringUtil.hasText(tags)) {
				BmConfig bmConfig = bmConfigService.findOne(uuid);
				bmConfig.setModifyTimestamp(DateUtil.getNowTimstamp());
				if (StringUtil.hasText(tags)) {
					bmConfig.setConfigValue(tags);
				}
				bmConfigService.update(bmConfig);
			}
			map.put("result", "success");
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return map;
	}
	
	/** -----------------------------------------------------Website info : CmWebsite for get and save ----------------------------------------------
	/**
	 * <p>Get json data with websiteinfos by tags, roleIds and lang</p>
	 * <pre>
	 *   http://198.101.207.178:8080/jping/web/websiteInfos/all/empty/en_SG ==> {"result":"success","data":[{"uuid":"8a3321b6481533bb014815368f4900e3",
	 *   	"websiteName":"Google","websiteUrl":"http://www.google.com","websiteLogoUrl":"http://198.101.207.178:8080/jping/resources/ic_logo_google.png",
	 *   	"tags":"Popular","displayNo":"1","roleIds":"","lang":"en_SG"}
	 *   http://localhost:8080/jp/web/websiteInfos/Popular/zh_CN ==> {"result":"success/fail","data":"新闻,网上购物"}
	 * </pre>
	 * 
	 * @param tags all/Popular/News 
	 * @param roleIds empty/8a3321b6481533bb014815368f4900e1,8a3321b6481533bb014815368f4900e2
	 * @param lang en_SG/zh_CN
	 * @param request 
	 * @param response
	 * 
	 * @return json data
	 */
	@RequestMapping(value = "/websiteInfos/{keywords}/{field}/{roleIds}/{lang}/{orderBy}", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getMapWithDataWebsiteInfos(@PathVariable String keywords, @PathVariable String field, @PathVariable String roleIds,
				@PathVariable String lang, @PathVariable String orderBy, HttpServletRequest request, HttpServletResponse response) {
		super.setCORS(response);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "fail");
		try {
			keywords = new String(keywords.getBytes("ISO-8859-1"), "UTF-8");
			List<Map<String,String>> websiteInfos = cmWebsiteService.getWebsiteInfos(keywords, field, roleIds, lang, orderBy);
			map.put("data", websiteInfos);
			map.put("result", "success");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
        return map;
	}
	
	/**
	 * <p>save website info<p>
	 * <pre>
	 *   http://198.101.207.178:8080/jping/web/saveWebsiteInfo
	 * </pre>
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/websiteInfo/save", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveWebsiteInfo(HttpServletRequest request, HttpServletResponse response) {
		super.setCORS(response);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "fail");
		try {
			String uuid = request.getParameter("uuid");
			boolean isNew = false;
			CmWebsite cmWebsite = null;
			if (StringUtil.hasText(uuid) && uuid.length()==32) {
				cmWebsite = cmWebsiteService.findOne(uuid);
				cmWebsite.setModifyTimestamp(DateUtil.getNowTimstamp());
			} else {
				cmWebsite = new CmWebsite();
				isNew = true;
			}
			
			cmWebsite.setWebsiteName(request.getParameter("websiteName"));
			cmWebsite.setWebsiteUrl(request.getParameter("websiteUrl"));
			cmWebsite.setLogoUrl(request.getParameter("websiteLogoUrl"));
			cmWebsite.setTags(request.getParameter("websiteTags"));
			int displayNo = StringUtil.hasText(request.getParameter("displayNo"))?Integer.parseInt(request.getParameter("displayNo")):9999;
			cmWebsite.setDisplayNo(displayNo);
			cmWebsite.setLang(request.getParameter("lang"));
			String roleIds = request.getParameter("roleIds");
			if (StringUtil.hasText(roleIds)) {
				cmWebsite.setRoleIds(roleIds);
			} else {
				cmWebsite.setRoleIds(null);
			}
			if (isNew) {
				cmWebsiteService.create(cmWebsite);
			} else {
				cmWebsiteService.update(cmWebsite);
			}
			
			Map<String,String> websiteInfo = new HashMap<String,String>();
			websiteInfo.put("uuid", cmWebsite.getWebsiteId());
			websiteInfo.put("websiteName", cmWebsite.getWebsiteName());
			websiteInfo.put("websiteUrl", cmWebsite.getWebsiteUrl());
			websiteInfo.put("websiteLogoUrl", cmWebsite.getLogoUrl());
			websiteInfo.put("tags", cmWebsite.getTags());
			websiteInfo.put("displayNo", StringUtil.toString(cmWebsite.getDisplayNo()));
			websiteInfo.put("roleIds", cmWebsite.getRoleIds());
			
			map.put("data", websiteInfo);
			map.put("result", "success");
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return map;
	}
	
	/** -----------------------------------------------------Article for get and save ---------------------------------------------*/
	@RequestMapping(value = "/articles/{keywords}/{field}/{roleIds}/{lang}/{pageNo}/{pageSize}/{orderBy}", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getMapWithDataArticles(@PathVariable String keywords, @PathVariable String field, @PathVariable String roleIds,
				@PathVariable String lang, @PathVariable Integer pageNo, @PathVariable Integer pageSize, @PathVariable String orderBy, 
				HttpServletRequest request, HttpServletResponse response) {
		super.setCORS(response);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "fail");
		try {
			keywords = new String(keywords.getBytes("ISO-8859-1"), "UTF-8");
			Page<CmArticle> cmArticlePage = cmArticleService.getCmArticlePage(keywords, field, lang, "", "", pageNo, pageSize, roleIds, orderBy);
			List<Map<String,String>> articles = new ArrayList<Map<String,String>>();
			for (CmArticle cmArticle : cmArticlePage.getResult()) {
				Map<String,String> articleMap = new LinkedHashMap<String,String>();
				articleMap.put("uuid", cmArticle.getArticleId());
				articleMap.put("cd", cmArticle.getArticleCd());
				articleMap.put("title", cmArticle.getTitle());
				articleMap.put("content", cmArticle.getContent());
				articleMap.put("imageUrls", cmArticle.getImageUrl());
				articleMap.put("originUrl", cmArticle.getOriginUrl());
				articleMap.put("tags", cmArticle.getTags());
				articleMap.put("displayNo", StringUtil.toString(cmArticle.getDisplayNo()));
				articleMap.put("roleIds", cmArticle.getRoleIds());
				articleMap.put("lang", cmArticle.getLang());
				articles.add(articleMap);
			}
			this.setPageInfo(cmArticlePage.getTotalPages(), cmArticlePage.getTotalCount(), pageNo, pageSize, map);
			map.put("data", articles);
			map.put("result", "success");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
        return map;
	}
	
	@RequestMapping(value = "/article/save", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveArticle(HttpServletRequest request, HttpServletResponse response) {
		super.setCORS(response);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "fail");
		try {
			String uuid = request.getParameter("uuid");
			CmArticle cmArticle = null;
			boolean isNew = false;
			if (StringUtil.hasText(uuid) && uuid.length()==32) {
				cmArticle = cmArticleService.findOne(uuid);
				cmArticle.setModifyTimestamp(DateUtil.getNowTimstamp());
			} else {
				cmArticle = new CmArticle();
				isNew = true;
			}
			String imageUrls = request.getParameter("imageUrls");
			String lang = request.getParameter("lang");
			String roleIds = request.getParameter("roleIds");
			String articleCd = request.getParameter("articleCd");
			String originUrl = request.getParameter("originUrl");
			String tags = request.getParameter("tags");
			cmArticle.setTitle(request.getParameter("title"));
			cmArticle.setContent(request.getParameter("content"));
			cmArticle.setTags(tags);
			if (StringUtil.hasText(imageUrls)) {
				cmArticle.setImageUrl(imageUrls);
			} else {
				cmArticle.setImageUrl(null);
			}
			if (StringUtil.hasText(articleCd)) {
				cmArticle.setArticleCd(articleCd);
			} else {
				cmArticle.setArticleCd(null);
			}
			if (StringUtil.hasText(originUrl)) {
				cmArticle.setOriginUrl(originUrl);
			} else {
				cmArticle.setOriginUrl(null);
			}
			int displayNo = StringUtil.hasText(request.getParameter("displayNo"))?Integer.parseInt(request.getParameter("displayNo")):9999;
			cmArticle.setDisplayNo(displayNo);
			cmArticle.setLang(lang);
			if (StringUtil.hasText(roleIds)) {
				cmArticle.setRoleIds(roleIds);
			} else {
				cmArticle.setRoleIds(null);
			}
			if (isNew) {
				cmArticleService.create(cmArticle);
			} else {
				cmArticleService.update(cmArticle);
			}
			
			if (StringUtil.hasText(imageUrls)) {
				String[] imageUrlArr = imageUrls.split(",");
				for (String imageUrl : imageUrlArr) {
					List<CmImage> cmImages = cmImageService.findByField(CmImage.class, "imageUrl", imageUrl);
					if (cmImages==null || cmImages.isEmpty()) {
						CmImage cmImage = new CmImage();
						cmImage.setLang(lang);
						cmImage.setDisplayNo(displayNo);
						if (StringUtil.hasText(imageUrl)) {
							String imageName = imageUrl.substring(imageUrl.lastIndexOf("/")+1, imageUrl.lastIndexOf("."));
							imageName = imageName.replaceAll("-", " ");
							imageName = imageName.replaceAll("_", " ");
							cmImage.setImageName(imageName);
							cmImage.setImageUrl(imageUrl);
						}
						if (StringUtil.hasText(roleIds)) cmImage.setRoleIds(roleIds);
						if (Const.LANGUAGE_ZH_CN.equals(lang)) {
							cmImage.setTags("其他,博文");
						} else {
							cmImage.setTags("Other,Article");
						}
						cmImageService.create(cmImage);
					} else {
						for (CmImage cmImage :  cmImages) {
							if (StringUtil.hasText(roleIds)) cmImage.setRoleIds(roleIds);
							if (Const.LANGUAGE_ZH_CN.equals(lang)) {
								cmImage.setTags("其他,博文");
							} else {
								cmImage.setTags("Other,Article");
							}
							cmImageService.update(cmImage);
						}
					}
				}
			}
			
			Map<String,String> articleMap = new LinkedHashMap<String,String>();
			articleMap.put("uuid", cmArticle.getArticleId());
			articleMap.put("cd", cmArticle.getArticleCd());
			articleMap.put("title", cmArticle.getTitle());
			articleMap.put("content", cmArticle.getContent());
			articleMap.put("imageUrls", cmArticle.getImageUrl());
			articleMap.put("tags", cmArticle.getTags());
			articleMap.put("displayNo", StringUtil.toString(cmArticle.getDisplayNo()));
			articleMap.put("roleIds", cmArticle.getRoleIds());
			
			map.put("data", articleMap);
			map.put("result", "success");
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return map;
	}
	
	/** -----------------------------------------------------Image for get and save ---------------------------------------------*/
	@RequestMapping(value = "/images/{keywords}/{field}/{roleIds}/{lang}/{pageNo}/{pageSize}/{orderBy}", method = RequestMethod.GET)
	public @ResponseBody Map<String,Object> getMapWithDataImages(@PathVariable String keywords, @PathVariable String field, @PathVariable String roleIds,
				@PathVariable String lang, @PathVariable Integer pageNo, @PathVariable Integer pageSize, @PathVariable String orderBy, 
				HttpServletRequest request, HttpServletResponse response) {
		super.setCORS(response);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "fail");
		try {
			keywords = new String(keywords.getBytes("ISO-8859-1"), "UTF-8");
			Page<CmImage> cmImagePage = cmImageService.getCmImagePage(keywords, field, lang, "", "", pageNo, pageSize, roleIds, orderBy);
			List<Map<String,String>> articles = new ArrayList<Map<String,String>>();
			for (CmImage cmImage : cmImagePage.getResult()) {
				Map<String,String> articleMap = new LinkedHashMap<String,String>();
				articleMap.put("uuid", cmImage.getImageId());
				articleMap.put("name", cmImage.getImageName());
				articleMap.put("content", cmImage.getDescription());
				articleMap.put("contentHtml", StringUtil.convertForHtml(cmImage.getDescription()));
				articleMap.put("url", cmImage.getImageUrl());
				articleMap.put("originUrl", cmImage.getOriginUrl());
				articleMap.put("tags", cmImage.getTags());
				articleMap.put("displayNo", StringUtil.toString(cmImage.getDisplayNo()));
				articleMap.put("roleIds", cmImage.getRoleIds());
				articleMap.put("lang", cmImage.getLang());
				articles.add(articleMap);
			}
			this.setPageInfo(cmImagePage.getTotalPages(), cmImagePage.getTotalCount(), pageNo, pageSize, map);
			map.put("data", articles);
			map.put("result", "success");
		} catch (Exception e) {
			log.error(e.getMessage());
		}
        return map;
	}
	
	@RequestMapping(value = "/image/save", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> saveImage(HttpServletRequest request, HttpServletResponse response) {
		super.setCORS(response);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "fail");
		try {
			String uuid = request.getParameter("uuid");
			CmImage cmImage = null;
			boolean isNew = false;
			if (StringUtil.hasText(uuid) && uuid.length()==32) {
				cmImage = cmImageService.findOne(uuid);
				cmImage.setModifyTimestamp(DateUtil.getNowTimstamp());
			} else {
				cmImage = new CmImage();
				isNew = true;
			}
			cmImage.setImageName(request.getParameter("name"));
			cmImage.setDescription(request.getParameter("content"));
			cmImage.setImageUrl(request.getParameter("url"));
			cmImage.setOriginUrl(request.getParameter("originUrl"));
			cmImage.setLang(request.getParameter("lang"));
			
			String tags = request.getParameter("tags");
			if (StringUtil.hasText(tags)) {
				cmImage.setTags(tags);
			} else {
				cmImage.setTags(null);
			}
			String roleIds = request.getParameter("roleIds");
			if (StringUtil.hasText(roleIds)) {
				cmImage.setRoleIds(roleIds);
			} else {
				cmImage.setRoleIds(null);
			}
			
			int displayNo = StringUtil.hasText(request.getParameter("displayNo"))?Integer.parseInt(request.getParameter("displayNo")):9999;
			cmImage.setDisplayNo(displayNo);
			
			if (isNew) {
				cmImageService.create(cmImage);
			} else {
				cmImageService.update(cmImage);
			}
			
			Map<String,String> articleMap = new LinkedHashMap<String,String>();
			articleMap.put("uuid", cmImage.getImageId());
			articleMap.put("name", cmImage.getImageName());
			articleMap.put("content", cmImage.getDescription());
			articleMap.put("url", cmImage.getImageUrl());
			articleMap.put("tags", cmImage.getTags());
			articleMap.put("displayNo", StringUtil.toString(cmImage.getDisplayNo()));
			articleMap.put("roleIds", cmImage.getRoleIds());
			
			map.put("data", articleMap);
			map.put("result", "success");
		} catch (Exception ex) {
			log.error(ex.getMessage());
		}
		return map;
	}
	
	private void setPageInfo(long totalPages, long totalCount, long pageNo, long pageSize, Map<String,Object> map) {
		long nextPageNo = pageNo;
		long prePageNo = pageNo-1;
		long showCount = pageNo * pageSize;
		if (totalPages<=pageNo) {
			nextPageNo = 0;
			showCount = totalCount;
		} else {
			nextPageNo++;
		}
		if (prePageNo<1) {
			prePageNo = 1;
		}
		long beginPageNo = 1;
		long endPageNo = totalPages;
		if (pageNo+9<endPageNo) {
			endPageNo = pageNo + 9;
		}
		if (endPageNo-beginPageNo>9) {
			beginPageNo = endPageNo - 10;
		}
		map.put("nextPageNo", nextPageNo);
		map.put("pageNo", pageNo);
		map.put("pageSize", pageSize);
		map.put("totalCount", totalCount);
		map.put("showCount", showCount);
		map.put("totalPages", totalPages);
		map.put("prePageNo", prePageNo);
		map.put("beginPageNo", beginPageNo);
		map.put("endPageNo", endPageNo);
	}
	
	/**---------------------------------------------------- File upload -----------------------------------------------------*/
	@RequestMapping(value="/uploadFile", method=RequestMethod.POST)
	public @ResponseBody Map<String,String> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
		super.setCORS(response);
		
		String folder = request.getParameter("folder");
		String lang = request.getParameter("lang");
		String fileFullName = "";
		Map<String,String> map = new HashMap<String,String>();
		map.put("result", "success");
		try {
			String fileName = new String(file.getOriginalFilename().getBytes("ISO-8859-1"), "UTF-8");// DateUtil.getNowYyyyMmDmHhMmSs() + 
			if ("image.jpg".equals(fileFullName))  {
				fileFullName = DateUtil.getNowYyyyMmDmHhMmSs() + ".jpg";
			} else {
				fileFullName = fileName;
			}
			String saveFullPath = FileUtil.getPath(request.getRealPath(""), "resources", "upload", folder, fileFullName);
			if (file.getSize() > 200000000) {
				map.put("result", fileFullName + " size is more than 200M");
			} else {
				String saveUrl = "/resources/upload/"+folder+"/" + fileFullName;
				fileName = fileName.substring(0, fileName.indexOf("."));
				fileName = fileName.replace("_Colors", "").replace("_1280_800", "").replace("_0", "").replace("_1", "").replace("_2", "").replace("_3", "").replace("_4", "").replace("_5", "").replace("_6", "")
						.replace("_7", "").replace("_8", "").replace("_9", "").replace("_", " ");
				map.put("saveUrl", saveUrl);
				map.put("fileName", fileName);
				map.put("fileFullName", fileFullName);
				if (new File(saveFullPath).exists()) {
					map.put("result", fileFullName + " has been exist!");
				} else {
					if (fileFullName.endsWith("png") || fileFullName.endsWith("jpg") || fileFullName.endsWith("jpeg")) { 
						
						String save = request.getParameter("save");
						if (!(StringUtil.hasText(save) && "no".equalsIgnoreCase(save))) {
							CmImage cmImage = new CmImage();
							cmImage.setImageName(fileName);
							cmImage.setImageUrl(saveUrl);
							cmImage.setLang(lang);
							cmImage.setDisplayNo(9999);
							if (Const.LANGUAGE_ZH_CN.equals(lang)) {
								cmImage.setTags("其他");
							} else {
								cmImage.setTags("Other");
							}
							cmImageService.create(cmImage);
						}
					}
					FileUtil.copy(file.getInputStream(), new File(saveFullPath));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			map.put("result", fileFullName + " upload fail");
			return map;
		}
		
		return map;
	}
	
	@Autowired
	private BmConfigService bmConfigService;
	
	@Autowired
	private AmRoleService amRoleService;
	
	@Autowired
	private AmUserRoleService amUserRoleService;
	
	@Autowired
	private AmUserService amUserService;
	
	@Autowired
	private CmArticleService cmArticleService;
	
	@Autowired
	private CmImageService cmImageService;
	
	@Autowired
	private CmWebsiteService cmWebsiteService;
	
	@Autowired
	private AmService amService;
	
}