/**
 * jp javascript Library 1.0.0
 * 
 * localStorage.setItem('name', 'value');
 * localStorage.getItem('name');
 * localStorage.removeItem('name');
 * localStorage.clear();
 * sessionStorage.length
 * 
 * Copyright (c) 2014 Gan Jianping. All rights reserved
 */
jp = {
  version: "1.0.0",
  author: "Gan Jianping",
  versionDetail: {
    major: 1,
    minor: 0,
    patch: 0
  },
  resourceRoot : "../../resources",
  keyLang : "jping-lang",
  keyRoleIdNames : "jping-role-id-names",
  KeyRoleIds : "jping-role-id-names"
};
var jpingRoleOptionsHtml = getRoleOptionsHtml();
var jpingRoleIds = getRoleIds();
var currentLanguage = getCurrentLanguage();

function getCurrentLanguage() {
  var lang = getStorageValue(jp.keyLang);
  if (!lang) {
	var navLang = navigator.language;
	if (navLang.indexOf("zh")==0) {
	  lang = "zh_CN";
	} else {
	  lang = "en_SG";
	}
  }
  return lang;
}

function setCurrentLanguage(language) {
  localStorage.setItem(jp.keyLang, language);
  currentLanguage = language;
}

function getRoleIds() {
  var roleIds = "empty";
  var jpingRoleIdNames = getStorageValue(jp.keyRoleIdNames);
  if (jpingRoleIdNames!=null && jpingRoleIdNames.length>32) {
	var roleIdNames = jpingRoleIdNames.split("_");
	for (var i=0; i<roleIdNames.length; i++) {
	  var roleIdNameArr = roleIdNames[i].split(",");
      if (i==0) {
    	  roleIds = roleIdNameArr[0];
      } else {
    	  roleIds += "," + roleIdNameArr[0];
      }
	} 
  }
  return roleIds;
}

function getRoleOptionsHtml() {
  var roleOptionsHtml = "<option value=''>All</option>";
  var jpingRoleIdNames = getStorageValue(jp.keyRoleIdNames);
  if (jpingRoleIdNames!=null) {
	var roleIdNames = jpingRoleIdNames.split("_");
	for (var i=0; i<roleIdNames.length; i++) {
	  var roleIdNameArr = roleIdNames[i].split(",");
      roleOptionsHtml += "<option value='" + roleIdNameArr[0] + "'>";
      if (roleIdNameArr.length>=2 && currentLanguage=="zh_CN") {
    	roleOptionsHtml += roleIdNameArr[2];
      } else {
    	roleOptionsHtml += roleIdNameArr[1];
      }
      roleOptionsHtml += "</option>";
    }
  }
  return roleOptionsHtml;
}

function setCookie(cname, cvalue, exdays) {
  var d = new Date();
  d.setTime(d.getTime() + (exdays*24*60*60*1000));
  var expires = "expires="+d.toGMTString();
  document.cookie = cname + "=" + cvalue + "; " + expires;
}
function getCookie(cname) {
  var name = cname + "=";
  var ca = document.cookie.split(';');
  for(var i=0; i<ca.length; i++) {
    var c = ca[i];
    while (c.charAt(0)==' ') c = c.substring(1);
    if (c.indexOf(name) != -1) return c.substring(name.length,c.length);
  }
  return "";
}
function deleteCookie(name) {
  document.cookie = name + '=; expires=Thu, 01 Jan 1970 00:00:01 GMT;';
}
function supportsLocalStorage() {
  try {
    return 'localStorage' in window && window['localStorage'] !== null;
  } catch (e) {
    return false;
  }
}
function getStorageValue(name) {
  var value = sessionStorage.getItem(name);
  if (!value) {
	  value = localStorage.getItem(name);
  }
  return value;
}


/**
 * <p>Load css file names with comma</p>
 * 
 * @param fileNames
 * @returns
 */
function loadCss(fileNames) {
  if ("all"==fileNames) {
	fileNames = "jqueryUiCss,bootstrapCss,fontAwesomeCss,chosenCss,fileUploadCss,magnificPopupCss,jpCss";
  }
  var fileNameArr = fileNames.split(",");
  for (var index in fileNameArr) {
	var fileName = fileNameArr[index];
	var cssTag = document.getElementById(fileName);
	var headEl = document.getElementsByTagName('head').item(0);
	if (cssTag) head.removeChild(cssTag);
	var linkEl = document.createElement('link');
	switch(fileName) {
	  case "jqueryUiCss":
		  linkEl.href = jp.resourceRoot + "/framework/jquery-ui/v1.11/jquery-ui.css"; break;
	  case "bootstrapCss":
		  linkEl.href = jp.resourceRoot + "/framework/bootstrap/v3.2/bootstrap.css"; break;
	  case "fontAwesomeCss":
		  linkEl.href = jp.resourceRoot + "/css/font-awesome/v4.2/font-awesome.css"; break;
	  case "chosenCss":
		  linkEl.href = jp.resourceRoot + "/component/chosen/v1.1/chosen.css"; break;
	  case "fileUploadCss":
		  linkEl.href = jp.resourceRoot + "/component/fileupload/v5.41/fileupload.css"; break;
	  case "magnificPopupCss":
		  linkEl.href = jp.resourceRoot + "/component/magnific-popup/v0.9/magnific-popup.css"; break;
	  case "jpCss":
		  linkEl.href = jp.resourceRoot + "/framework/jp/jp.css"; break;
	  default:
	      continue;
	} 
	linkEl.rel = 'stylesheet';
	linkEl.type = 'text/css';
	linkEl.id = fileName;
	headEl.appendChild(linkEl);
  } 
}

/**
 * <p>Load js file names with comma</p>
 * 
 * @param fileNames
 * @returns
 */
function loadJs(fileNames) {
  if ("all"==fileNames) {
	fileNames = "jqueryJs,jqueryUiJs,bootstrapJs,chosenJs,fileUploadJs,magnificPopupJs,jqueryFormJs,jqueryValidateJs,jpJs";
  }
  var fileNameArr = fileNames.split(",");
  for (var index in fileNameArr) {
	var fileName = fileNameArr[index];
	var jsTag = document.getElementById(fileName);
	var headEl = document.getElementsByTagName('head').item(0);
	if (jsTag) headEl.removeChild(jsTag);
	if (fileName=="i18nZhCnJs") {
		var enEl = document.getElementById("i18nEnSgJs");
		if (enEl) {
			headEl.removeChild(enEl);
		}
	} else if (fileName=="i18nEnSgJs") {
		var zhEl = document.getElementById("i18nZhCnJs");
		if (zhEl) {
			headEl.removeChild(zhEl);
		}
	}
	var scriptEl = document.createElement('script');
	scriptEl.id = fileName;
	scriptEl.type = "text/javascript";
	switch(fileName) {
	  case "jqueryJs":
		  scriptEl.src = jp.resourceRoot + "/js/jquery/jquery-1.11.1.js"; break;
	  case "jqueryUiJs":
		  scriptEl.src = jp.resourceRoot + "/framework/jquery-ui/v1.11/jquery-ui.js"; break;
	  case "bootstrapJs":
		  scriptEl.src = jp.resourceRoot + "/framework/bootstrap/v3.2/bootstrap.js"; break;
	  case "chosen":
		  scriptEl.src = jp.resourceRoot + "/component/chosen/v1.1/jquery.chosen.js"; break;
	  case "fileUploadJs":
		  scriptEl.src = jp.resourceRoot + "/component/fileupload/v5.41/jquery.fileupload.js"; break;
	  case "magnificPopupJs":
		  scriptEl.src = jp.resourceRoot + "/component/magnific-popup/v0.9/jquery.magnific-popup.js"; break;
	  case "jqueryFormJs":
		  scriptEl.src = jp.resourceRoot + "/js/jquery/jquery.form-3.51.js"; break;
	  case "jqueryValidateJs":
		  scriptEl.src = jp.resourceRoot + "/js/jquery/jquery.validate-1.13.js"; break;
	  case "i18nEnSgJs":
		  scriptEl.src = jp.resourceRoot + "/jp/i18n/gone_i18n_en_SG.js"; break;
	  case "i18nZhCnJs":
		  scriptEl.src = jp.resourceRoot + "/jp/i18n/gone_i18n_zh_CN.js"; break;
	  case "jpJs":
		  scriptEl.src = jp.resourceRoot + "/framework/jp/jp.js"; break;
	  default:
	      continue;
	}
	headEl.appendChild(scriptEl);
  }
}