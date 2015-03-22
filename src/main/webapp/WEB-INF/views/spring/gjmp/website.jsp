<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
  <title>Gan Jianping Websites Manager</title>
  <%@ include file="/WEB-INF/views/spring/gjmp/common/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/spring/gjmp/common/menu.jsp" %>
<div id="content">
  <div class="container">
	<div class="panel panel-primary">
	  <div class="panel-heading" style="font-weight:bold;font-size:18px;">
	    <input id='check-all' name='check-all' type='checkbox' class='jp-check-box jp-hide' value='all'/> Websites <span id="total-number" class="badge"></span>
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
				<input id="search" name="search" type="text" class="form-control" placeholder="eg : and websiteName,websiteUrl,logoUrl,tags,roleIds,description,displayNo,lang "/>
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
<div id="website-modal" class="modal fade" website="dialog" aria-labelledby="website-title" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="website-title" class="modal-title text-center">Add Website</h4>
      </div>
      <div class="modal-body">
        <form id="website-form" method="POST">
          <div class="form-group">
            <label for="website-name" class="control-label">Website Name :</label>
            <input id="website-name" name="websiteName" type="text" class="form-control" placeholder="eg: Google" tabindex="2">
          </div>
          <div class="form-group">
            <label for="website-url" class="control-label">Website URL :</label>
            <input id="website-url" name="websiteUrl" type="text" class="form-control" placeholder="eg: http://www.google.com" tabindex="2">
          </div>
          <div class="form-group">
            <label for="tags" class="control-label">Tags :</label>
            <input id="tags" name="tags" type="text" class="form-control" placeholder="eg: popular,news" tabindex="2">
          </div>
          <div class="form-group">
            <label for="logo-url" class="control-label">Logo URL :</label>
            <input id="logo-url" name="logoUrl" type="text" class="form-control" placeholder="eg: http://www.google.com/fav.icon" tabindex="2">
          </div>
          <div class="form-group">
            <label for="display-no" class="control-label">Display No. :</label>
            <input id="display-no" name="displayNo" type="text" class="form-control" placeholder="eg: 9999" tabindex="2">
          </div>
          <div class="form-group">
            <label for="role-ids" class="control-label">Role Ids :</label>
            <input id="role-ids" name="roleIds" type="text" class="form-control" placeholder="eg: 1223232342342342342,1223232342342342343" tabindex="2">
          </div>
          <div class="form-group">
            <label for="lang" class="control-label">Lang :</label>
            <input id="lang" name="lang" type="text" class="form-control" placeholder="eg: en_SG/zh_CN" tabindex="2">
          </div>
          <div class="form-group">
            <label for="description" class="control-label">Description :</label>
            <textarea id="description" name="description" class="form-control"></textarea>
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
<%@ include file="/WEB-INF/views/spring/gjmp/common/footer.jsp" %>
<script> 
function loadDataList(paramJson) {
  $.getJSON("<c:url value='/spring/cm/websitePage'/>", paramJson, function(page) {
	$("#list-items").html("");
	$("#total-number").text(page.totalCount);
	var data = page.result;
	$("#list-group").html("");
	var index = 0;
	$.each(data, function(index, map) {
	  var listItemList = "<li class='list-group-item' style='min-height:48px;word-break: break-all;'><label class='checkbox-inline'>" +
	  		"<input name='uuid' type='checkbox' class='jp-check-box' value='" + map['websiteId'] + "' style='padding-right:10px;'/>";
	  if (!jp.isEmpty(map['websiteName'])) listItemList += "<b>" + map['websiteName'] + "</b>";
	  if (!jp.isEmpty(map['tags'])) listItemList += " (" + map['tags'] + ")";
	  listItemList += "<br/> <span style='margin-left:-20px;'>Create on " + jp.formateDateTimeStr(map["createDateTime"]) + ", Modify on " + jp.formateDateTimeStr(map["modifyTimestamp"]) + "</span>";
	  if (!jp.isEmpty(map['description'])) listItemList += "<br/>" + map['description'];
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
  //data-toggle="modal" data-target="#website-modal", keyboard: false, show:false, .modal('toggle'), .modal('show'), .modal('hide')
  $('#website-modal').modal({backdrop : 'static'});
  $("#website-title").html("Add Website");
  $("#website-name").val("");
  $("#website-url").val("");
  $("#tags").val("");
  $("#logo-url").val("");
  $("#display-no").val("");
  $("#role-ids").val("");
  $("#lang").val("");
  $("#description").val("");
  mIsAdd = true;
}

function edit() {
  if (!jp.isEmpty(mSelUuids) && mSelUuids.length==32) {
	  $.getJSON(mRootUrl+"spring/cm/website/" + mSelUuids, function(data) {
		  $("#website-cd").val(data["websiteCd"]);
		  $("#website-name").val(data["websiteName"]);
		  $("#website-url").val(data["websiteUrl"]);
		  $("#tags").val(data["tags"]);
		  $("#logo-url").val(data["logoUrl"]);
		  $("#display-no").val(data["displayNo"]);
		  $("#role-ids").val(data["roleIds"]);
		  $("#lang").val(data["lang"]);
		  $("#description").val(data["description"]);
		  $('#website-modal').modal({backdrop : 'static'});
	  });
	  $("#website-title").html("Edit Website");
  } else {
	  alert("Sorry for you must select only one first.");
  }
  mIsAdd = false;
}

function save() {
  var saveUrl = "<c:url value='/spring/cm/website'/>";
  if (mIsAdd == false) {
	  saveUrl = mRootUrl + "spring/cm/website/" + mSelUuids;
  }
  $("#website-form").ajaxSubmit({
    dataType:  'json',
    url: saveUrl,
	success: function(data) {
	  if (data.result=="success") {
		$("#edit-btn").hide();
		$("#delete-btn").hide();
		search(1);
		$('#website-modal').modal('hide');
	  } else {
		alert("Fail");
	  }
    }
  });
}

function del() {
  var urlStr = "<c:url value='/spring/cm/website/delete'/>";
  $.ajax({type:"POST", url:urlStr, data: {websiteIds:mSelUuids}, async:true, dataType:'json', 
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
  $("#websites").show();
  $("#website").hide();
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
});

</script>
</body>
</html>