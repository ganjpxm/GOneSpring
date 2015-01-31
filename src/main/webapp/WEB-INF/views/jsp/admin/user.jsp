<%@ include file="/WEB-INF/views/jsp/admin/common/jspInfo.jsp" %>
<html>
<head>
  <title>User Manager</title>
  <%@ include file="/WEB-INF/views/jsp/admin/common/head.jsp" %>
</head>
<body>
<div style="background-color:white;">
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">System Manage Console</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#">Subsystem</a></li>
        <li><a href="#">Orgnization</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Authorize <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">Role Manager</a></li>
            <li><a href="#">User Manager</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Setting <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="#">System Config</a></li>
            <li><a href="#">Param</a></li>
          </ul>
        </li>
        <li><a href="#">Logout</a></li>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
</div>

<div id="content">
  <div class="container">
	<div class="panel panel-primary">
	  <div class="panel-heading" style="font-weight:bold;font-size:18px;">
	    <input id='check-all' name='check-all' type='checkbox' class='jp-check-box jp-hide' value='all'/> Users <span id="total-number" class="badge"></span>
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
				<input id="search" name="search" type="text" class="form-control" placeholder="eg : and userCd='admin'"/>
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
<div id="user-modal" class="modal fade" role="dialog" aria-labelledby="user-title" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="user-title" class="modal-title text-center">Add User</h4>
      </div>
      <div class="modal-body">
        <form id="user-form" method="POST">
          <div class="form-group">
            <label for="user-cd" class="control-label">User CD :</label>
            <input id="user-cd" name="userCd" type="text" class="form-control" placeholder="eg: ganjp" tabindex="1">
          </div>
          <div class="form-group">
            <label for="first-name" class="control-label">First Name :</label>
            <input id="first-name" name="firstName" type="text" class="form-control" placeholder="eg: Jianping" tabindex="2">
          </div>
          <div class="form-group">
            <label for="last-name" class="control-label">Last Name :</label>
            <input id="last-name" name="lastName" type="text" class="form-control" placeholder="eg: Gan"  tabindex="3">
          </div>
          <div class="form-group">
            <label for="gender" class="control-label">Gender :</label>
            <div id="gender">
	            <label class="radio-inline">
				  <input type="radio" id="male" name="gender" value="male" checked  tabindex="4"> Male
				</label>
				<label class="radio-inline">
				  <input type="radio" id="female" name="gender" value="female" tabindex="4"> Female
				</label>
			</div>
          </div>
          
          <div class="form-group">
            <label for="birthday" class="control-label">Birthday :</label>
            <input id="birthday" name="birthday" type="text" class="form-control" placeholder="dd/mm/yyyy"  tabindex="5">
          </div>
          
          <div class="form-group">
            <label for="mobile-number" class="control-label">Mobile Number :</label>
            <input id="mobile-number" name="mobileNumber" type="tel" class="form-control" tabindex="6">
          </div>
          <div class="form-group">
            <label for="email" class="control-label">Email :</label>
            <input id="email" name="email" type="email" class="form-control" tabindex="7">
          </div>
          <div class="form-group">
            <label for="password" class="control-label">Password :</label>
            <input id="password" name="password" type="text" class="form-control" tabindex="8">
          </div>
          <div class="form-group">
            <label for="description" class="control-label">Descrpition :</label>
            <textarea id="description" name="description" class="form-control" tabindex="9"></textarea>
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
<%@ include file="/WEB-INF/views/jsp/admin/common/footer.jsp" %>
<script> 
var mFieldNames = "${fieldNames}".split(",");
var mSelUuids = "";
var mIsAdd = true;
var mRootUrl = "<c:url value='/'/>";
if (mRootUrl.indexOf(";")!=-1) {
  var mRootUrlArr = mRootUrl.split(";");
  mRootUrl = mRootUrlArr[0];
}
var mPageNo = "${pageNo}";
var mPageSize = "${pageSize}";

function search(pageNo) {
  if (!jp.isEmpty(pageNo)) {
	mPageNo = pageNo;
  }
  var paramJson = {pageNo:mPageNo, pageSize:mPageSize};
  var search = $("#search").val();
  if (!jp.isEmpty(search)) {
	  paramJson.search = search;	
  }
  loadUserList(paramJson);
}

function loadUserList(paramJson) {
  $.getJSON("<c:url value='/spring/am/userPage'/>", paramJson, function(page) {
	$("#list-items").html("");
	$("#total-number").text(page.totalCount);
	var data = page.result;
	$("#list-group").html("");
	var index = 0;
	$.each(data, function(index, map) {
	  var listItemList = "<li class='list-group-item' style='min-height:48px;'><label class='checkbox-inline'>" +
	  		"<input name='uuid' type='checkbox' class='jp-check-box' value='" + map['userId'] + "' style='padding-right:10px;'/>";
	  if (!jp.isEmpty(map['userCd'])) listItemList += "<b>" + map['userCd'] + "</b>";
	  if (!jp.isEmpty(map['userName'])) listItemList += " (" + map['userName'] + ")";
	  if (!jp.isEmpty(map['userCd']) || !jp.isEmpty(map['userCd'])) listItemList += "<br/> "
	  if (!jp.isEmpty(map['gender'])) listItemList += " " + map['gender'] + ", ";
	  if (!jp.isEmpty(map['birthday'])) listItemList += " birth on " + jp.formateDateStr(map["birthday"]) + ", ";
	  if (!jp.isEmpty(map['mobileNumber'])) listItemList += " " + map['mobileNumber'] + ", ";
	  if (!jp.isEmpty(map['email'])) listItemList += " " + map['email'];
	  listItemList += "<br/> create on " + jp.formateDateTimeStr(map["createDateTime"]) + ", modify on " + jp.formateDateTimeStr(map["modifyTimestamp"]);
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
  //data-toggle="modal" data-target="#user-modal", keyboard: false, show:false, .modal('toggle'), .modal('show'), .modal('hide')
  $('#user-modal').modal({backdrop : 'static'});
  $("#user-title").html("Add User");
  $("#user-cd").val("");
  $("#first-name").val("");
  $("#last-name").val("");
  $("#birthday").val("");
  $("#mobile-number").val("");
  $("#email").val("");
  $("#password").val("");
  $("#description").val("");
  mIsAdd = true;
}

function edit() {
  if (!jp.isEmpty(mSelUuids) && mSelUuids.length==32) {
	  $.getJSON(mRootUrl+"spring/am/user/" + mSelUuids, function(data) {
		  $("#user-cd").val(data["userCd"]);
		  $("#first-name").val(data["firstName"]);
		  $("#last-name").val(data["lastName"]);
		  jp.selectRadio("gender", data["gender"]);
		  $("#birthday").val(jp.formateDateStr(data["birthday"]));
		  $("#mobile-number").val(data["mobileNumber"]);
		  $("#email").val(data["email"]);
		  $("#password").val(data["password"]);
		  $("#description").val(data["description"]);
		  
		  $('#user-modal').modal({backdrop : 'static'});
	  });
	  $("#user-title").html("Edit User");
  } else {
	  alert("Sorry for you must select only one first.");
  }
  mIsAdd = false;
}

function save() {
  var saveUrl = "<c:url value='/spring/am/user'/>";
  if (mIsAdd == false) {
	  saveUrl = mRootUrl + "spring/am/user/" + mSelUuids;
  }
  $("#user-form").ajaxSubmit({
    dataType:  'json',
    url: saveUrl,
	success: function(data) {
	  if (data.result=="success") {
		$("#edit-btn").hide();
		$("#delete-btn").hide();
		search(1);
		$('#user-modal').modal('hide');
	  } else {
		alert("Fail");
	  }
    }
  });
}

function popupDelDialog() {
  $("#del-modal").modal('show');
}
function del() {
  var urlStr = "<c:url value='/spring/am/user/delete'/>";
  $.ajax({type:"POST", url:urlStr, data: {userIds:mSelUuids}, async:true, dataType:'json', 
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
  $("#users").show();
  $("#user").hide();
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
});

</script>
</body>
</html>