<div id="del-modal" class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="del-title" aria-hidden="true">
  <div class="modal-dialog modal-sm">
    <div class="modal-content">
      <div class="modal-body">
        <h4 id="del-title" class="modal-title text-center" style="padding:20px;">Are you confirm to delete?</h4>
      </div>
      <div class="modal-footer jp-center">
        <button type="button" class="btn btn-default" data-dismiss="modal" tabindex="11"><i class="fa fa-close"></i> No</button>
        <button type="button" class="btn btn-primary" tabindex="10" onclick="del();"><i class="fa fa-check"></i> Yes</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript" src="<c:url value="/resources/jquery/jquery-1.11.1.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryform/jquery.form-3.51.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryvalidation/1.13.1/jquery.validate.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jqueryMaskedinput/jquery.maskedinput-1.4.0.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap/v3.3.2/js/bootstrap.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/bootstrap-datepicker/js/bootstrap-datepicker.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/intl-tel-input/js/intlTelInput.min.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/intl-tel-input/js/utils.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/other/modalLoading.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/json/json2.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/jp/jp.js" />"></script>
 
<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
  <script src="http://cdn.bootcss.com/html5shiv/3.7.0/html5shiv.min.js"></script>
  <script src="http://cdn.bootcss.com/respond.js/1.3.0/respond.min.js"></script>
<![endif]-->