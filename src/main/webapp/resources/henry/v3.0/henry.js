/*
 * Henry HTML editor | (c) 2013, dicrurus.com | dicrurus.com/license
*/
;
(function ($) {
	var path = $('script').last()[0].src.split('/').slice(0, -1).join('/') + "/";
	var option = {
		lang: "en",
		tools: ["src", "format", "alignment", "color", "background", "bold", "italic", "underline", "strikeout",
			"superscript", "subscript", "horizontalrule", "indent", "outdent",
			"numberedlist", "bulletedlist", "link", "image", "youtube", "code", "about"],
		content: "<p><br></p>"
	};

	var Henry = $.Henry = {
		tool: {}
	};

	var absTool = {
		isActive: function () {
			return false;
		}
	};

	$.fn.henry = function (options) {
		$.extend(option, options);	
		$.ajaxSetup({
			cache: true,
			async: false
		});
		//$.getScript(path + "tools.js");
		//$.getScript(path + "lang/" + option.lang + ".js");
		Henry.field = this;
		$(Henry.field).hide();
		// form submit
		Henry.form = $(Henry.field).parents("form");
		$(Henry.form).submit(function () {
			$(Henry.field).val($(Henry.paper).html());
		});

		// editor
		Henry.editor = $("<div id='henry'>");
		$($(Henry.field).parent()).append(Henry.editor);

		// toolbar
		Henry.toolbar = $("<div id='toolbar'>");
		$(Henry.editor).append(Henry.toolbar);

		// put tools
		$.each(option.tools, function (i, n) {
			var t = Henry.tool[n];
			if (t == undefined) {
				return;
			}
			Henry.tool[n] = t = $.extend(true, {}, absTool, t);
			t.button = createButton(n);
			$.each(t, function (k, v) {
				switch (k) {
					case "init":
						t.init();
						break;
					case "mouseenter":
						$(t.button).on("mouseenter", function (e) {
							v(t, e);
						});
						break;
					case "mouseleave":
						$(t.button).on("mouseleave", function (e) {
							v(t, e);
						});
						break;
					case "click":
						$(t.button).on("click", function (e) {
							v(t, e);
						});
						break;
				}
			});
			$(t.button).on("mouseleave", Henry.activeStyle);
			$(Henry.toolbar).append(t.button);
		});
		// paper
		Henry.paper = $("<div id='paper'>");
		$(Henry.editor).append(Henry.paper);
		$(Henry.paper).attr("contentEditable", true);
		if (this.val() != "") {
			$(Henry.paper).html(this.val());
		} else {
			$(Henry.paper).html(option.content);
		}
		$(Henry.paper).on("keyup", Henry.activeStyle);
		$(Henry.paper).on("mouseup", Henry.activeStyle);
		return this;
	};

	function createButton(n) {
		var e = $("<a>");
		$(e).attr("id", n);
		$(e).attr("title", Henry.lang[n]);
		$(e).attr("href", "javascript:;");
		return e;
	}

	Henry.openPopupWindow = function (e) {
		var m = Henry.popupWindow = $("<div id='popupWindow'>");
		$(m).css("top", Henry.toolbar[0].offsetTop + Henry.toolbar[0].offsetHeight);
		$(m).css("left", e[0].offsetLeft - 3);
		return m;
	};

	Henry.closePopupWindow = function () {
		if (Henry.popupWindow != undefined) {
			Henry.popupWindow.remove();
			Henry.popupWindow = undefined;
		}
	};

	Henry.activeStyle = function () {
		$.each(Henry.tool, function (k, v) {
			$(v.button).removeClass();
		});
		var sel = document.getSelection();
		var n = sel.anchorNode;
		if (n == null) {
			return false;
		}
		var c = $(n).parents().get().reverse();
		if (n.nodeType != 3) {
			c.push(n);
		}
		$(c).each(function (i, e) {
			if (e.id == "paper") {
				c.splice(0, i + 1);
				$(c).each(function (i, e) {
					$.each(Henry.tool, function (k, v) {
						if (v.button != undefined && !$(v.button).hasClass("active") && v.isActive(v, e)) {
							$(v.button).addClass("active");
						}
					});
				});
				return false;
			}
			return true;
		});
		return true;
	};

	Henry.selection = function () {
		var sel;
		if (document.getSelection) {
			sel = document.getSelection();
			sel.types = sel.type;
			if (sel.types != "None") {
				sel.node = sel.anchorNode;
				sel.range = sel.getRangeAt(0);
				sel.text = sel.range.cloneContents().textContent;
			}
		} else {
			sel = document.selection;
			if (document.selection.type == "Text") {
				sel.text = sel.createRange().text;
			}
		}
		sel.mark = function mark() {
			sel.removeAllRanges();
			sel.extend(sel.node, sel.range.startOffset);
			sel.extend(sel.node, sel.range.endOffset);
		};
		sel.markParent = function markParent() {
			sel.removeAllRanges();
			sel.extend(sel.node.parentNode);
			sel.extend(sel.node.parentNode, 1);
		};
		sel.newText = function newText(text) {
			var s = sel.range.startOffset;
			console.log(s);
			var e = sel.range.endOffset;
			console.log(e);
			var p = sel.node.parentNode;
			var t = $(p).text();
			$(p).text(t.substring(0, s) + text + t.substring(e, t.length));
			sel.removeAllRanges();
			sel.extend(p.childNodes[0], s);
			sel.extend(p.childNodes[0], e - (e - s - text.length));
		};
		return sel;
	};
}(jQuery));
