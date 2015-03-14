<%@ include file="/WEB-INF/views/jsp/smp/common/jspInfo.jsp" %>
<html>
<head>
  <title>Org Manager</title>
  <%@ include file="/WEB-INF/views/jsp/smp/common/head.jsp" %>
</head>
<body>
<%@ include file="/WEB-INF/views/jsp/smp/common/menu.jsp" %>
<div id="content">
  <div class="container">
	<div class="panel panel-primary">
	  <div class="panel-heading" style="font-weight:bold;font-size:18px;">
	    <input id='check-all' name='check-all' type='checkbox' class='jp-check-box jp-hide' value='all'/> Orgs <span id="total-number" class="badge"></span>
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
				<input id="search" name="search" type="text" class="form-control" placeholder="eg : and orgCd='finance'"/>
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
<div id="org-modal" class="modal fade" role="dialog" aria-labelledby="org-title" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header bg-primary">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 id="org-title" class="modal-title text-center">Add Org</h4>
      </div>
      <div class="modal-body">
        <form id="org-form" method="POST">
          <div class="form-group">
            <label for="org-cd" class="control-label">Org CD :</label>
            <input id="org-cd" name="orgCd" type="text" class="form-control" placeholder="eg: smc" tabindex="1">
          </div>
          <div class="form-group">
            <label for="org-name" class="control-label">Org Name :</label>
            <input id="org-name" name="orgName" type="text" class="form-control" placeholder="eg: System Manager Console" tabindex="2">
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
<%@ include file="/WEB-INF/views/jsp/smp/common/footer.jsp" %>
<script> 
function loadDataList(paramJson) {
  $.getJSON("<c:url value='/spring/am/orgPage'/>", paramJson, function(page) {
	$("#list-items").html("");
	$("#total-number").text(page.totalCount);
	var data = page.result;
	$("#list-group").html("");
	var index = 0;
	$.each(data, function(index, map) {
	  var listItemList = "<li class='list-group-item' style='min-height:48px;'><label class='checkbox-inline'>" +
	  		"<input name='uuid' type='checkbox' class='jp-check-box' value='" + map['orgId'] + "' style='padding-right:10px;'/>";
	  if (!jp.isEmpty(map['orgName'])) listItemList += "<b>" + map['orgName'] + "</b>";
	  if (!jp.isEmpty(map['orgCd'])) listItemList += " (" + map['orgCd'] + ")";
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
  //data-toggle="modal" data-target="#org-modal", keyboard: false, show:false, .modal('toggle'), .modal('show'), .modal('hide')
  $('#org-modal').modal({backdrop : 'static'});
  $("#org-title").html("Add Org");
  $("#org-cd").val("");
  $("#org-name").val("");
  $("#description").val("");
  mIsAdd = true;
}

function edit() {
  if (!jp.isEmpty(mSelUuids) && mSelUuids.length==32) {
	  $.getJSON(mRootUrl+"spring/am/org/" + mSelUuids, function(data) {
		  $("#org-cd").val(data["orgCd"]);
		  $("#org-name").val(data["orgName"]);
		  $("#description").val(data["description"]);
		  $('#org-modal').modal({backdrop : 'static'});
	  });
	  $("#org-title").html("Edit Org");
  } else {
	  alert("Sorry for you must select only one first.");
  }
  mIsAdd = false;
}

function save() {
  var saveUrl = "<c:url value='/spring/am/org'/>";
  if (mIsAdd == false) {
	  saveUrl = mRootUrl + "spring/am/org/" + mSelUuids;
  }
  $("#org-form").ajaxSubmit({
    dataType:  'json',
    url: saveUrl,
	success: function(data) {
	  if (data.result=="success") {
		$("#edit-btn").hide();
		$("#delete-btn").hide();
		search(1);
		$('#org-modal').modal('hide');
	  } else {
		alert("Fail");
	  }
    }
  });
}

function del() {
  var urlStr = "<c:url value='/spring/am/org/delete'/>";
  $.ajax({type:"POST", url:urlStr, data: {orgIds:mSelUuids}, async:true, dataType:'json', 
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
  $("#orgs").show();
  $("#org").hide();
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