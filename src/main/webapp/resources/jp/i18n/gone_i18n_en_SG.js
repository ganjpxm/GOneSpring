/**
 *@class I18N
 *description:js multi language class,should be loaded before others,singleton.
 *author:ganjp
 *date:2010-8-16
 */
var I18N = null;
if(!I18N){
I18N = {
    brandName: "Gan Jianping",
    home: "Home",
    websites: "Websites",
    articles: "Articles",
    images: "Images",
    audios: "Audios",
    videos: "Videos",
    download: "Download",
    
    carouselTitle1: "Gan Jianping",
    carouselContent1: "The website is a personal website that work on desktop, laptop, tablet and smartphone.",
    carouselTitle2: "Websites",
    carouselContent2: "The website provide many website urls that include news, online shopping and software etc.",
    carouselTitle3: "Mobile Apps",
    carouselContent3: "The website provide many apps that include iOS, Android and windows phone etc.",
    
    loginTitle: "Login",
    loginWay: "User Name / Mobile No. / Email",
    password: "Password",
    addWebsiteTitle: "Website",
    wesiteName: "Website Name",
    websiteUrl: "Website URL",
    websiteLogoUrl: "Website Logo URL",
    chooseTags: "Choose the tags...",
    displayNo: "Display No.",
    chooseRoles: "Choose the roles...",
    chooseTagsError: "Please choose the tags",
    imageUrl: "Image Url",
    
    addArticleTitle: "Article",
    articleCd: "Article CD",
    articleTitle: "Article Title",
    articleContent: "Article Content",
    articleOriginUrl: "Article Origin Url",
    
    addImageTitle: "Image",
    imageName: "Name",
    imageContent: "Description",
    imageOriginUrl: "Origin Url",
    
	login: "Login",
	logout: "Logout",
	save: "Save",
	reset: "Reset",	
	close: "Close",
	confirm: "Confirm",
	cancel: "Cancel",
	yes: "Yes",
	no: "No",
	refresh: "Refresh",
	loading: "Loading...",
	processing: "Processing...",
	choose: "Please choose...",
	promp: "Promp",
	add: "Add",
	edit: "Edit",
	del: "Delete",
	search: "Search",
	view: "View",
	fail: "Fail",
	upload: "Upload",
	more: "More",
	
	msg_save_sucess: "Save Success",
	msg_save_fail: "Save Fail",
	msg_del_sucess: "Delete success",
	msg_del_confirm: "Are you sure to delete?",
	msg_copy_confirm: "Are you sure to copy?",
	msg_no_sel: "Please make a choice",
	msg_single_record: "Please select only one record",
	msg_saving: "Saving",
	msg_system_error: "System error",
	msg_set_form_id: "Please set form ID:",
	msg_pwd_not_match: "Passwords do not match",
	msg_cd_format_error: "This field should only contain letters, numbers, - and _",
	msg_chinese_format_error: "This field should only contain chinese",
	msg_no_image_del: "There is not image to delete.",
	msg_sign_up_success: "Sign Up Success",
	msg_sign_up_fail: "Sign Up Fail",
	msg_exist: " has been exist.",
	msg_error_user_cd_password: "User ID or Password is error, please try again.",
	msg_error_mobile_number_password: "Mobile Number or Password is error, please try again.",
	msg_delete_link_title: "Delete the selected link?",
	msg_delete_article_title: "Delete the selected article?",
	msg_delete_photo_title: "Delete the selected photo?",
	msg_delete_video_title: "Delete the selected video?",
	msg_delete_content: "Are you sure that delete the {0} ?",
	
	zeroRecords: "No matching records found",
	emptyTable: "No data available in table",
	displayRecords: "Showing record {0} - {1} of {2}",
	infoFiltered: "filtered from {0} total entries",
	firstPage: "first",
	previousPage: "previous",
	nextPage: "next",
	lastPage: "last",
	pageSize: "30",
		
	uuid_get_fail: "get uuid fail"
};
};