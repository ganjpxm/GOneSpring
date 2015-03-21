<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>Subsystem Manager</title>
  <%@ include file="/WEB-INF/views/spring/smp/common/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/spring/smp/common/menu.jsp" %>
<div id="content">
  <div class="container">
	<div class="panel panel-primary">
	  <!-- Tool Bar -->
	  <div class="panel-heading" style="font-weight:bold;font-size:18px;">
	    <input id='check-all' name='check-all' type='checkbox' class='jp-check-box jp-hide' value='all'/> Subsystems <span id="total-number" class="badge"></span>
		<div class="pull-right">
		  <button id="add-btn" type="button" class="btn btn-primary" style="margin-top:-4px;" onclick="add();"><i class="fa fa-plus"></i>&nbsp;Add</button>
		  <button id="edit-btn" type="button" class="btn btn-primary jp-hide" style="margin-top:-4px;" onclick="edit();"><i class="fa fa-edit"></i>&nbsp;Edit</button>
		  <button id="delete-btn" type="button" class="btn btn-primary jp-hide" style="margin-top:-4px;" onclick="popupDelDialog();"><i class="fa fa-edit"></i>&nbsp;Delete</button>
        </div>
	  </div>
	  <!-- Search -->
	  <div id="search-panel" class="panel-body">
	    <div class="row" style="margin:5px 0px 5px 0px;">
			<div class="col-md-12">
			  <div class="input-icon">
				<i class="fa fa-search" style="cursor:pointer;" onClick="search(1);"></i>
				<input id="search" name="search" type="text" class="form-control" placeholder="eg : and subsystemCd='smc'"/>
			  </div>
			</div>
		</div>
	  </div>
	  <!-- data list -->
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
<!-- add and edit Modal -->
<div id="subsystem-modal" class="modal fade" role="dialog" aria-labelledby="subsystem-title" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="subsystem-title" class="modal-title text-center">Add Subsystem</h4>
      </div>
      <div class="modal-body">
        <form id="subsystem-form" method="POST">
          <input id="role-ids" name="roleIds" type="hidden"/>
          <div class="form-group">
            <label for="subsystem-cd" class="control-label">Subsystem CD :</label>
            <input id="subsystem-cd" name="subsystemCd" type="text" class="form-control" placeholder="eg: smp" tabindex="1">
          </div>
          <div class="form-group">
            <label for="subsystem-name" class="control-label">Subsystem Name :</label>
            <input id="subsystem-name" name="subsystemName" type="text" class="form-control" placeholder="eg: System Manager Platform" tabindex="2">
          </div>
          <div class="form-group">
            <label for="home_url" class="control-label">Home URL :</label>
            <input id="home_url" name="homeUrl" type="text" class="form-control" placeholder="eg: /mmp/home" tabindex="2">
          </div>
          <div class="form-group">
            <label for="role-id" class="control-label">Roles :</label>
            <select id="role-id" name="roleId" data-placeholder="- Select roles -" class="form-control chosen-select" multiple tabindex="3"></select>
          </div>
          <div class="form-group">
            <label for="description" class="control-label">Description :</label>
            <textarea id="description" name="description" class="form-control" tabindex="4"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer bg-primary jp-center">
        <button type="button" class="btn btn-primary" data-dismiss="modal" tabindex="11"><i class="fa fa-close"></i> Cancel</button>
        <button type="button" class="btn btn-primary btn-lg" tabindex="10" onclick="save();"><i class="fa fa-save"></i> Save</button>
      </div>
    </div>
  </div>
</div>
<%@ include file="/WEB-INF/views/spring/smp/common/footer.jsp" %>
<script> 
function loadDataList(paramJson) {
  $.getJSON("<c:url value='/spring/am/subsystemPageWithRoleNames'/>", paramJson, function(page) {
	$("#list-items").html("");
	$("#total-number").text(page.totalCount);
	var data = page.result;
	$("#list-group").html("");
	var index = 0;
	$.each(data, function(index, map) {
	  var listItemList = "<li class='list-group-item' style='min-height:48px;word-break: break-all;'><label class='checkbox-inline'>" +
	  		"<input name='uuid' type='checkbox' class='jp-check-box' value='" + map['subsystemId'] + "' style='padding-right:10px;'/>";
	  if (!jp.isEmpty(map['subsystemName'])) listItemList += "<b>" + map['subsystemName'] + "</b>";
	  if (!jp.isEmpty(map['subsystemCd'])) listItemList += " (" + map['subsystemCd'] + ")";
	  if (!jp.isEmpty(map['homeUrl'])) listItemList += "<br/><i class='fa fa-link fa-fw' style='margin-left:-20px;'></i> Manage Console URL : " + map['homeUrl'];
	  if (!jp.isEmpty(map['roleNames'])) listItemList += "<br/><i class='fa fa-user-secret fa-fw' style='margin-left:-20px;'></i> Roles : " + map['roleNames'];
	  listItemList += "<br/><i class='fa fa-calendar fa-fw' style='margin-left:-20px;'></i> Create on " + jp.formateDateTimeStr(map["createDateTime"]) + ", Modify on " + jp.formateDateTimeStr(map["modifyTimestamp"]);
	  if (!jp.isEmpty(map['description'])) listItemList += "<br/> <i class='fa fa-comment fa-fw' style='margin-left:-20px;'></i> " + map['description'];
	  $("#list-group").append(listItemList);
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
  //data-toggle="modal" data-target="#subsystem-modal", keyboard: false, show:false, .modal('toggle'), .modal('show'), .modal('hide')
  
  $("#subsystem-title").html("Add Subsystem");
  $("#subsystem-cd").val("");
  $("#subsystem-name").val("");
  $("#home_url").val("");
  $("#description").val("");
  
  $("#role-id").val("");
  $('#subsystem-modal').modal({backdrop : 'static'});
  $("#role-id").trigger("chosen:updated");
  mIsAdd = true;
}

function edit() {
  if (!jp.isEmpty(mSelUuids) && mSelUuids.length==32) {
	  $.getJSON(mRootUrl+"spring/am/subsystemWithRoleIds/" + mSelUuids, function(data) {
		  $("#subsystem-cd").val(data["subsystemCd"]);
		  $("#subsystem-name").val(data["subsystemName"]);
		  $("#home_url").val(data["homeUrl"]);
		  $("#description").val(data["description"]);
		  
		  var roleIds = data["roleIds"];
		  if (jp.isEmpty(roleIds)) {
			  $("#role-id").val("");
		  } else {
			  $("#role-id").val(roleIds.split(","));
		  }
		  $('#subsystem-modal').modal({backdrop : 'static'});
		  $("#role-id").trigger("chosen:updated");
	  });
	  $("#subsystem-title").html("Edit Subsystem");
  } else {
	  alert("Sorry for you must select only one first.");
  }
  mIsAdd = false;
}

function save() {
  $("#role-ids").val($("#role-id").val());
  var saveUrl = "<c:url value='/spring/am/subsystem/role'/>";
  if (mIsAdd == false) {
	  saveUrl = mRootUrl + "spring/am/subsystem/role/" + mSelUuids;
  }
  $("#subsystem-form").ajaxSubmit({
    dataType:  'json',
    url: saveUrl,
	success: function(data) {
	  if (data.result=="success") {
		$("#edit-btn").hide();
		$("#delete-btn").hide();
		search(1);
		$('#subsystem-modal').modal('hide');
	  } else {
		alert("Fail");
	  }
    }
  });
}

function del() {
  var urlStr = "<c:url value='/spring/am/subsystem/deleteWithRelation'/>";
  $.ajax({type:"POST", url:urlStr, data: {subsystemIds:mSelUuids}, async:true, dataType:'json', 
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
  $("#subsystems").show();
  $("#subsystem").hide();
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
 
  var birthdayEl = $('#birthday').datepicker({
    onRender: function(date) {
	  return date.valueOf() > new Date().valueOf() ? 'disabled' : '';
	},
	format: 'dd/mm/yyyy'
  }).on('changeDate', function(ev) {
	birthdayEl.hide();
  }).data('datepicker');
  
  $('#mobile-number, #female, #male, #last-name').focus(function() {
	birthdayEl.hide();
  });
  
  $("#birthday").mask("99/99/9999", {placeholder:"dd/mm/yyyy"});
  $("#mobile-number").intlTelInput({preferredCountries: [ "sg", "cn", "hk", "my" ], defaultCountry: "sg"});
  
  $.getJSON("<c:url value='/spring/am/roles'/>", function(data) {
    var roleOptions = "";
	  $.each(data, function(index, map) {
	  roleOptions += "<option value='" + map['roleId'] + "'>" + map['roleName'] + "</option>";
	});
	$("#role-id").html(roleOptions);
	$("#role-id").chosen({search_contains:false, width:"100%"});
  });
});

</script>
</body>
</html>