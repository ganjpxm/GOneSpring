<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>Config</title>
  <%@ include file="/WEB-INF/views/spring/smp/common/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/spring/smp/common/menu.jsp" %>
<div id="content">
  <div class="container">
	<div class="panel panel-primary">
	  <div class="panel-heading" style="font-weight:bold;font-size:18px;">
	    <input id='check-all' name='check-all' type='checkbox' class='jp-check-box jp-hide' value='all'/> System Parameters <span id="total-number" class="badge"></span>
		<div class="pull-right">
		  <button id="add-btn" type="button" class="btn btn-primary" style="margin-top:-4px;" onclick="add();"><i class="fa fa-plus"></i>&nbsp;Add</button>
		  <button id="edit-btn" type="button" class="btn btn-primary jp-hide" style="margin-top:-4px;" onclick="edit();"><i class="fa fa-edit"></i>&nbsp;Edit</button>
		  <button id="delete-btn" type="button" class="btn btn-primary jp-hide" style="margin-top:-4px;" onclick="popupDelDialog();"><i class="fa fa-edit"></i>&nbsp;Delete</button>
        </div>
	  </div>
	  <div id="search-panel" class="panel-body">
	    <div class="row" style="margin:5px 0px 5px 0px;">
			<div class="col-md-12">
			  <div class="input-icon">
				<i class="fa fa-search" style="cursor:pointer;" onClick="search(1);"></i>
				<input id="search" name="search" type="text" class="form-control" placeholder="eg : and configCd='tag'"/>
			  </div>
			</div>
		</div>
	  </div>
	  <!-- List group -->
	  <ul id="list-group" class="list-group">
	    <li class="list-group-item text-center">No record</li>
	  </ul>
	  <div id="pager-panel" class="panel-footer jp-hide jp-center">
	    <ul id="pager-ul" class="pagination" style="margin-top:1px;margin-bottom:1px;"> 
	      
	    </ul>
	  </div>
    </div>
  </div>
</div>
<!-- Modal -->
<div id="config-modal" class="modal fade" role="dialog" aria-labelledby="config-title" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="config-title" class="modal-title text-center">Add Config</h4>
      </div>
      <div class="modal-body">
        <form id="config-form" method="POST">
          <input id="role-ids" name="roleIds" type="hidden"/>
          <div class="form-group">
            <label for="config-cd" class="control-label">Config CD :</label>
            <input id="config-cd" name="configCd" type="text" class="form-control" placeholder="eg: tags" tabindex="1">
          </div>
          <div class="form-group">
            <label for="config-name" class="control-label">Config Name :</label>
            <input id="config-name" name="configName" type="text" class="form-control" placeholder="eg: Tags" tabindex="2">
          </div>
          <div class="form-group">
            <label for="config-value" class="control-label">Value :</label>
            <textarea id="config-value" name="configValue" class="form-control" tabindex="3"></textarea>
          </div>
          <div class="form-group">
            <label for="subsystem-id" class="control-label">Subsystem :</label>
            <select id="subsystem-id" name="subsystemId" data-placeholder="Select..." class="form-control" tabindex="4" ></select>
          </div>
          <div class="form-group">
            <label for="lang" class="control-label">Lang :</label>
            <div id="lang">
	            <label class="radio-inline">
				  <input type="radio" id="lang_en" name="lang" value="en_SG" checked  tabindex="5"> English
				</label>
				<label class="radio-inline">
				  <input type="radio" id="lang_zh" name="lang" value="zh_CN" tabindex="5"> 中文
				</label>
			</div>
          </div>
          
          <div class="form-group">
            <label for="display-no" class="control-label">Display No. :</label>
            <input id="display-no" name="displayNo" type="number" class="form-control" tabindex="6">
          </div>
        </form>
      </div>
      <div class="modal-footer bg-primary jp-center">
        <button type="button" class="btn btn-primary" data-dismiss="modal" tabindex="7"><i class="fa fa-close"></i> Cancel</button>
        <button type="button" class="btn btn-primary btn-lg" tabindex="8" onclick="save();"><i class="fa fa-save"></i> Save</button>
      </div>
    </div>
  </div>
</div>
<%@ include file="/WEB-INF/views/spring/smp/common/footer.jsp" %>
<script> 
function loadDataList(paramJson) {
  $.getJSON("<c:url value='/spring/all/configPageWithSubsystemNameSections'/>", paramJson, function(page) {
	$("#list-items").html("");
	$("#total-number").text(page.totalCount);
	var subsystemNameAndBmConfigs = page.resultMap;
	if (page.totalCount > 0) {
		$("#list-group").html("");
	}
	var index = 0;
	
	$.map(subsystemNameAndBmConfigs, function(value, key) {
		var subsystemName = key;
		var bmConfigs = value;
		$("#list-group").append("<li class='list-group-item' style='min-height:32px;background-color:#ECEFF1;font-weight:bold;'>" + key + "</li>");
		$.each(bmConfigs, function(index, map) {
		  var listItemList = "<li class='list-group-item' style='min-height:48px;word-break: break-all;'><label class='checkbox-inline'>" +
		  		"<input name='uuid' type='checkbox' class='jp-check-box' value='" + map['configId'] + "' style='padding-right:10px;'/>";
		  if (!jp.isEmpty(map['configName'])) listItemList += "<b>" + map['configName'] + "</b>";
		  if (!jp.isEmpty(map['configCd'])) listItemList += " (" + map['configCd'] + ")";
		  if (!jp.isEmpty(map['configCd']) || !jp.isEmpty(map['configName'])) listItemList += "<br/>";
		  if (!jp.isEmpty(map['configValue'])) listItemList += "<i class='fa fa-cube fa-fw' style='margin-left:-20px;'></i>" + map['configValue'] + "<br/>";
		  var lang = map['lang'];
		  var createOn = "Create on ";
		  var modifyOn = "modify on ";
		  if (lang=="en_SG") {
			  listItemList += "<i class='fa fa-language fa-fw' style='margin-left:-20px;'></i> English<br/>";
		  } else if (lang=="zh_CN") {
			  listItemList += "<i class='fa fa-language fa-fw' style='margin-left:-20px;'></i> 中文<br/>";
			  createOn = "创建于";
			  modifyOn = "修改于";
		  }
		  listItemList += "<i class='fa fa-calendar fa-fw' style='margin-left:-20px;'></i> " + createOn + jp.formateDateTimeStr(map["createDateTime"]) + ", " + modifyOn + " " + jp.formateDateTimeStr(map["modifyTimestamp"]);
		  if (!jp.isEmpty(map['description'])) listItemList += "<br/><i class='fa fa-comment fa-fw' style='margin-left:-20px;'></i>  " + map['description'];
		  $("#list-group").append(listItemList).append("</li>");
		});
	});
	
	$("#pager-panel").hide();
	$("#pager-ul").html("");
	var totalPages = page.totalPages-0;
	if (totalPages>1) {
	  $("#pager-ul").append("<li><a href='javascript:search(1);'>&lt;&lt;</a></li>");
	  $("#pager-ul").append("<li><a href='javascript:search(" + page.prePage + ");'>&lt;</a></li>");
	  for (var i=1; i<=totalPages; i++) {
		var liHtml = "<li><a href='javascript:search(" + i + ");'";
		if (i==mPageNo) {
			liHtml += " style='background-color:#009688;color:white;'";
		}
		liHtml +=">" + i + "</a></li>";
		$("#pager-ul").append(liHtml);
	  }
	  $("#pager-ul").append("<li><a href='javascript:search(" + page.nextPage + ");'>&gt;</a></li>");
	  $("#pager-ul").append("<li><a href='javascript:search(" + totalPages + ");'>&gt;&gt;</a></li>");
	  $("#pager-panel").show();
	}
	
	$("input[name='uuid']").change(function(){
		mSelUuids = jp.getCheckValues("uuid");
		if (jp.isEmpty(mSelUuids)) {
			$("#edit-btn").hide();
			$("#delete-btn").hide();
			$("#check-all").hide();
		} else {
			$("#edit-btn").show();
			$("#delete-btn").show();
			$("#check-all").show();
		}
		if (mSelUuids.length > 40) {
			$("#edit-btn").hide();
		}
	});
	$("#check-all").change(function(){
		var value = jp.getCheckValues("check-all");
		$("#edit-btn").hide();
		if (jp.isEmpty(value)) {
			$("#delete-btn").hide();
		} else {
			$("#delete-btn").show();
		}
		jp.checkAll(this,'uuid');
		mSelUuids = jp.getCheckValues("uuid");
	});
  });
}

function add() {
  $('#config-modal').modal({backdrop : 'static'});
  $("#config-title").html("Add Config");
  $("#config-cd").val("");
  $("#config-name").val("");
  $("#config-value").val("");
  $("#lang").val("");
  $("#display-no").val("");
  mIsAdd = true;
}

function edit() {
  if (!jp.isEmpty(mSelUuids) && mSelUuids.length==32) {
	  $.getJSON(mRootUrl+"spring/bm/config/" + mSelUuids, function(data) {
		  $("#config-cd").val(data["configCd"]);
		  $("#config-name").val(data["configName"]);
		  $("#config-value").val(data["configValue"]);
		  $("#subsystem-id").val(data["subsystemId"]);
		  jp.selectRadio("lang", data["lang"]);
		  $("#display-no").val(data["displayNo"]);
		  
		  $('#config-modal').modal({backdrop : 'static'});
	  });
	  $("#config-title").html("Edit Config");
  } else {
	  alert("Sorry for you must select only one config.");
  }
  mIsAdd = false;
}

function save() {
  var saveUrl = "<c:url value='/spring/bm/config'/>";
  if (mIsAdd == false) {
	  saveUrl = mRootUrl + "spring/bm/config/" + mSelUuids;
  }
  $("#config-form").ajaxSubmit({
    dataType:  'json',
    url: saveUrl,
	success: function(data) {
	  if (data.result=="success") {
		$("#edit-btn").hide();
		$("#delete-btn").hide();
		search(1);
		$('#config-modal').modal('hide');
	  } else {
		alert("Fail");
	  }
    }
  });
}

function del() {
  var urlStr = "<c:url value='/spring/bm/config/delete'/>";
  $.ajax({type:"POST", url:urlStr, data: {configIds:mSelUuids}, async:true, dataType:'json', 
	success : function(data) {
	  if (data.result=="success") {
		search(mPageNo);
		$("#del-modal").modal("hide");
	  } else {
		alert("Fail");
      }
	} 
  });
}

function back() {
  $("#configs").show();
  $("#config").hide();
}

$(document).ready(function() {
  search(mPageNo);
  
  $('#search').focus( function() {
	 $("#search-btn").show();
  });
  $('#search').blur( function() {
	 if (jp.isEmpty($(this).val())) {
		 $("#search-btn").hide();
	 } else {
		 $("#search-btn").show();
	 }
  });
  
  $.getJSON("<c:url value='/spring/am/subsystemIdNames'/>", function(data) {
    var subsystemOptions = "<option value='null'>- Select a subsystem -</option>";
	$.map(data, function(value, key) {
		subsystemOptions += "<option value='" + key + "'>" + value + "</option>";
	});
	$("#subsystem-id").html(subsystemOptions);
  });
  
  $('textarea').autosize();
});

</script>
</body>
</html>