/*
 * Henry HTML editor | (c) 2013, dicrurus.com | dicrurus.com/license
 */
$.Henry.tool.bold = {
	click: function () {
		document.execCommand("bold");
	},
	isActive: function (t, n) {
		return n.nodeName == "B";
	}
};

$.Henry.tool.italic = {
	click: function () {
		document.execCommand("italic");
	},
	isActive: function (t, n) {
		return n.nodeName == "I";
	}
};

$.Henry.tool.underline = {
	click: function () {
		document.execCommand("underline");
	},
	isActive: function (t, n) {
		return n.nodeName == "U";
	}
};

$.Henry.tool.strikeout = {
	click: function () {
		document.execCommand("strikeThrough");
	},
	isActive: function (t, n) {
		return n.nodeName == "STRIKE";
	}
};

$.Henry.tool.superscript = {
	click: function () {
		document.execCommand("superscript");
	},
	isActive: function (t, n) {
		return n.nodeName == "SUP";
	}
};

$.Henry.tool.subscript = {
	click: function () {
		document.execCommand("subscript");
	},
	isActive: function (t, n) {
		return n.nodeName == "SUB";
	}
};

$.Henry.tool.horizontalrule = {
	click: function () {
		document.execCommand("insertHorizontalRule");
	}
};

$.Henry.tool.indent = {
	click: function () {
		document.execCommand("indent");
	},
	isActive: function (t, n) {
		return parseInt($(n).css("margin-left"), 10) < 0;
	}
};

$.Henry.tool.outdent = {
	click: function () {
		document.execCommand("outdent");
	},
	isActive: function (t, n) {
		return parseInt($(n).css("margin-right"), 10) > 0;
	}
};

$.Henry.tool.numberedlist = {
	click: function () {
		document.execCommand("insertOrderedList");
	},
	isActive: function (t, n) {
		return n.nodeName == "OL";
	}
};

$.Henry.tool.bulletedlist = {
	click: function () {
		document.execCommand("insertUnorderedList");
	},
	isActive: function (t, n) {
		return n.nodeName == "UL";
	}
};

$.Henry.tool.about = {
	click: function () {
		window.open("http://henry.dicrurus.com/");
	}
};

$.Henry.tool.link = {
	click: function (t) {
		if ($.Henry.popupWindow) {
			return false;
		}
		var sel = $.Henry.selection();
		if (sel.type == "None") {
			return false;
		}
		if (t.button.hasClass("active")) {
			sel.removeAllRanges();
			if (sel.types == "Caret") {
				sel.markParent();
			}
			document.execCommand("unlink");
			return false;
		}
		var w = $.Henry.openPopupWindow(t.button);
		var tp = $("<p>");
		tp.append($("<label for='text'>").text($.Henry.lang["text"]));
		w.append(tp);
		var tt = $("<input type='text' id='text'>");
		tp.append(tt);
		if (sel.text != undefined) {
			tt.val(sel.text);
		}
		var up = $("<p>");
		up.append($("<label for='url'>").text($.Henry.lang["url"]));
		w.append(up);
		var ut = $("<input type='text' id='url'>");
		up.append(ut);
		var b = $("<button>");
		b.text($.Henry.lang["add"]);
		w.append($("<p>").append(b));
		b.click(function () {
			if (ut.val() == "") {
				alert("url can not be empty");
			} else {
				sel.newText(tt.val());
				document.execCommand("createLink", false, ut.val());
				$.Henry.closePopupWindow();
			}
			return false;
		});
		t.button.append(w);
		ut.focus();
		return false;
	},
	mouseleave: function () {
		$.Henry.closePopupWindow();
	},
	isActive: function (t, n) {
		var a = n.nodeName == "A";
		if (a) {
			t.button.attr("title", $.Henry.lang["unlink"]);
		} else {
			t.button.attr("title", $.Henry.lang["link"]);
		}
		return a;
	}
};

$.Henry.tool.image = {
	click: function (t) {
		if ($.Henry.popupWindow) {
			return false;
		}
		var sel = $.Henry.selection();
		if (sel.type == "None") {
			return false;
		}
		var w = $.Henry.openPopupWindow(t.button);
		var up = $("<p>");
		up.append($("<label for='url'>").text($.Henry.lang["url"]));
		w.append(up);
		var ut = $("<input type='text' id='url'>");
		up.append(ut);
		var b = $("<button>");
		b.text($.Henry.lang["add"]);
		w.append($("<p>").append(b));
		b.click(function () {
			if (ut.val() == "") {
				alert("url can not be empty");
			} else {
				sel.mark();
				document.execCommand("insertimage", false, ut.val());
				$.Henry.closePopupWindow();
			}
			return false;
		});
		t.button.append(w);
		ut.focus();
		return false;
	},
	mouseleave: function () {
		$.Henry.closePopupWindow();
	}
};

$.Henry.tool.youtube = {
	click: function (t) {
		if ($.Henry.popupWindow) {
			return false;
		}
		var sel = $.Henry.selection();
		if (sel.type == "None") {
			return false;
		}
		var width = 640, height = 390;
		var w = $.Henry.openPopupWindow(t.button);
		var up = $("<p>");
		up.append($("<label for='url'>").text($.Henry.lang["url"]));
		w.append(up);
		var ut = $("<input type='text' id='url'>");
		up.append(ut);
		var sp = $("<p></p>");
		w.append(sp);
		var ht = $("<input type='text' id='height'>");
		ht.val(height);
		sp.append($("<label for='height'>").text($.Henry.lang["height"]));
		sp.append(ht);
		var wt = $("<input type='text' id='width'>");
		wt.val(width);
		sp.append($("<label for='width'>").text($.Henry.lang["width"]));
		sp.append(wt);
		var b = $("<button>");
		b.text($.Henry.lang["add"]);
		w.append($("<p>").append(b));
		b.click(function () {
			if (ut.val() == "") {
				alert($.Henry.lang["urlEmpty"]);
			} else {
				sel.mark();
				var m = ut.val().match(/(v\/|v=|.be\/)([^&]+)/);
				var src = m[m.length - 1];
				var html = "<iframe width='" + wt.val() + "' height='" + ht.val() + "' "
					+ "src='http://www.youtube.com/embed/" + src + "?enablejsapi=1' "
					+ "frameborder='0'></iframe>";
				document.execCommand("inserthtml", false, html);
				$.Henry.closePopupWindow();
			}
			return false;
		});
		t.button.append(w);
		ut.focus();
		return false;
	},
	mouseleave: function () {
		$.Henry.closePopupWindow();
	}
};

$.Henry.tool.code = {
	click: function () {
		document.execCommand("inserthtml", false, "<pre class='code'></pre>");
		var sel = $.Henry.selection();
		var c = $(sel.anchorNode);
		c.on("click", function () {
			var t = $("<textarea class='code'>").val(c.text());
			t.width(c.width());
			t.focus();
			$(c).after(t);
			c.hide();
			t.blur(function () {
				c.show();
				c.text(t.val());
				t.remove();
			});
		});
		return false;
	}
};

$.Henry.tool.format = {
	formats: ["h1", "h2", "h3", "h4", "h5", "h6", "p", "blockquote", "pre", "address"],
	mouseenter: function (t) {
		var w = $.Henry.openPopupWindow(t.button);
		$.each(t.formats, function (i, e) {
			if (!t.button.hasClass(e)) {
				var a = $("<a>");
				a.addClass(e);
				a.attr("title", $.Henry.lang[e]);
				a.attr("href", "javascript:;");
				a.click(function () {
					document.execCommand("formatblock", false, e);
					$.Henry.closePopupWindow();
					$.Henry.activeStyle();
				});
				w.append(a);
			}
		});
		t.button.append(w);
	},
	mouseleave: function () {
		$.Henry.closePopupWindow();
	},
	isActive: function (t, n) {
		t.button.removeClass();
		switch (n.nodeName) {
			case "P":
				t.button.addClass("p");
				return true;
			case "BLOCKQUOTE":
				t.button.addClass("blockquote");
				return true;
			case "PRE":
				t.button.addClass("pre");
				return true;
			case "ADDRESS":
				t.button.addClass("address");
				return true;
			case "H1":
				t.button.addClass("h1");
				return true;
			case "H2":
				t.button.addClass("h2");
				return true;
			case "H3":
				t.button.addClass("h3");
				return true;
			case "H4":
				t.button.addClass("h4");
				return true;
			case "H5":
				t.button.addClass("h5");
				return true;
			case "H6":
				t.button.addClass("h6");
				return true;
		}
		return false;
	}
};

var webColor = {
	colors: ["#000000", "#000033", "#000066", "#000099", "#0000CC", "#0000FF",
		"#003300", "#003333", "#003366", "#003399", "#0033CC", "#0033FF",
		"#006600", "#006633", "#006666", "#006699", "#0066CC", "#0066FF",
		"#009900", "#009933", "#009966", "#009999", "#0099CC", "#0099FF",
		"#00CC00", "#00CC33", "#00CC66", "#00CC99", "#00CCCC", "#00CCFF",
		"#00FF00", "#00FF33", "#00FF66", "#00FF99", "#00FFCC", "#00FFFF",
		"#330000", "#330033", "#330066", "#330099", "#3300CC", "#3300FF",
		"#333300", "#333333", "#333366", "#333399", "#3333CC", "#3333FF",
		"#336600", "#336633", "#336666", "#336699", "#3366CC", "#3366FF",
		"#339900", "#339933", "#339966", "#339999", "#3399CC", "#3399FF",
		"#33CC00", "#33CC33", "#33CC66", "#33CC99", "#33CCCC", "#33CCFF",
		"#33FF00", "#33FF33", "#33FF66", "#33FF99", "#33FFCC", "#33FFFF",
		"#660000", "#660033", "#660066", "#660099", "#6600CC", "#6600FF",
		"#663300", "#663333", "#663366", "#663399", "#6633CC", "#6633FF",
		"#666600", "#666633", "#666666", "#666699", "#6666CC", "#6666FF",
		"#669900", "#669933", "#669966", "#669999", "#6699CC", "#6699FF",
		"#66CC00", "#66CC33", "#66CC66", "#66CC99", "#66CCCC", "#66CCFF",
		"#66FF00", "#66FF33", "#66FF66", "#66FF99", "#66FFCC", "#66FFFF",
		"#990000", "#990033", "#990066", "#990099", "#9900CC", "#9900FF",
		"#993300", "#993333", "#993366", "#993399", "#9933CC", "#9933FF",
		"#996600", "#996633", "#996666", "#996699", "#9966CC", "#9966FF",
		"#999900", "#999933", "#999966", "#999999", "#9999CC", "#9999FF",
		"#99CC00", "#99CC33", "#99CC66", "#99CC99", "#99CCCC", "#99CCFF",
		"#99FF00", "#99FF33", "#99FF66", "#99FF99", "#99FFCC", "#99FFFF",
		"#CC0000", "#CC0033", "#CC0066", "#CC0099", "#CC00CC", "#CC00FF",
		"#CC3300", "#CC3333", "#CC3366", "#CC3399", "#CC33CC", "#CC33FF",
		"#CC6600", "#CC6633", "#CC6666", "#CC6699", "#CC66CC", "#CC66FF",
		"#CC9900", "#CC9933", "#CC9966", "#CC9999", "#CC99CC", "#CC99FF",
		"#CCCC00", "#CCCC33", "#CCCC66", "#CCCC99", "#CCCCCC", "#CCCCFF",
		"#CCFF00", "#CCFF33", "#CCFF66", "#CCFF99", "#CCFFCC", "#CCFFFF",
		"#FF0000", "#FF0033", "#FF0066", "#FF0099", "#FF00CC", "#FF00FF",
		"#FF3300", "#FF3333", "#FF3366", "#FF3399", "#FF33CC", "#FF33FF",
		"#FF6600", "#FF6633", "#FF6666", "#FF6699", "#FF66CC", "#FF66FF",
		"#FF9900", "#FF9933", "#FF9966", "#FF9999", "#FF99CC", "#FF99FF",
		"#FFCC00", "#FFCC33", "#FFCC66", "#FFCC99", "#FFCCCC", "#FFCCFF",
		"#FFFF00", "#FFFF33", "#FFFF66", "#FFFF99", "#FFFFCC", "#FFFFFF"],
	elements: [],
	init: function () {
		$.each(webColor.colors, function (i, e) {
			var a = $("<a>");
			a.addClass("webColor");
			a.css("background-color", e);
			a.attr("title", e);
			a.attr("href", "javascript:;");
			webColor.elements.push(a);
		});
	}
};
webColor.init();

$.Henry.tool.color = {
	mouseenter: function (t) {
		var w = $.Henry.openPopupWindow(t.button);
		$.each(webColor.elements, function (i, e) {
			e.click(function () {
				document.execCommand("foreColor", false, e.css("background-color"));
				$.Henry.closePopupWindow();
				$.Henry.activeStyle();
			});
			w.append(e);
			if (i != 0 && i % (6 * 4) == 23) {
				w.append($("<br>"));
			}
		});
		t.button.append(w);
	},
	mouseleave: function () {
		$.Henry.closePopupWindow();
	},
	isActive: function (t, n) {
		var c = $(n).css("color");
		var cs = c.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
		if (cs[1] + cs[2] + cs[3] == 0) {
			c = "black";
		}
		t.button.css("background-color", c);
	}
};

$.Henry.tool.background = {
	mouseenter: function (t) {
		var w = $.Henry.openPopupWindow(t.button);
		$.each(webColor.elements, function (i, e) {
			e.click(function () {
				document.execCommand("backColor", false, e.css("background-color"));
				$.Henry.closePopupWindow();
				$.Henry.activeStyle();
			});
			w.append(e);
			if (i != 0 && i % (6 * 4) == 23) {
				w.append($("<br>"));
			}
		});
		t.button.append(w);
	},
	mouseleave: function () {
		$.Henry.closePopupWindow();
	},
	isActive: function (t, n) {
		var c = $(n).css("background-color");
		var cs = null;
		if (c != null) {
			cs = c.match(/^rgb\((\d+),\s*(\d+),\s*(\d+)\)$/);
		}
		if (cs == null || cs[1] + cs[2] + cs[3] + cs[4] == 0) {
			c = "transparent";
		}
		t.button.css("background-color", c);
	}
};

$.Henry.tool.alignment = {
	variations: ["left", "right", "centered", "justify"],
	mouseenter: function (t) {
		var m = $.Henry.openPopupWindow(t.button);
		$.each(t.variations, function (i, e) {
			if (!t.button.hasClass(e)) {
				var a = $("<a>");
				a.addClass(e);
				a.attr("title", $.Henry.lang[e]);
				a.attr("href", "javascript:;");
				a.click(function () {
					switch (e) {
						case "left":
							document.execCommand("justifyLeft");
							break;
						case "right":
							document.execCommand("justifyRight");
							break;
						case "centered":
							document.execCommand("justifyCenter");
							break;
						case "justify":
							document.execCommand("justifyFull");
					}
					$.Henry.closePopupWindow();
					$.Henry.activeStyle();
				});
				$(m).append(a);
			}
		});
		t.button.append(m);
	},
	mouseleave: function () {
		$.Henry.closePopupWindow();
	},
	isActive: function (t, n) {
		t.button.removeClass();
		if (n.type != 3) {
			switch ($(n).css("text-align")) {
				case "right":
					t.button.addClass("right");
					break;
				case "center":
					t.button.addClass("center");
					break;
				case "justify":
					t.button.addClass("justify");
					break;
				default :
					t.button.addClass("left");
			}
		}
		return false;
	}
};

$.Henry.tool.src = {
	click: function (t) {
		var area = $.Henry.tool.src.area;
		if (area == undefined) {
			area = $.Henry.tool.src.area = $("<textarea class='src'></textarea>");
			area.height($.Henry.paper.height());
			area.width($.Henry.paper.width());
			area.keydown(function (e) {
				var keyCode = e.keyCode || e.which;
				if (keyCode == 9) {
					var val = area.value,
						start = area.selectionStart,
						end = area.selectionEnd;
					area.value = val.substring(0, start) + '\t' + val.substring(end);
					area.selectionStart = area.selectionEnd = start + 1;
					return false;
				}
				return true;
			});
			area.hide();
			$.Henry.editor.append(area);
		}
		if (area.css("display") != "inline-block") {
			t.button.css("background-color", "#fff");
			var html = "";
			$.each($.Henry.paper.children(), function (i, e) {
				html += "<" + e.tagName;
				if (e.attributes.length > 0) {
					$.each(e.attributes, function (i, e) {
						html += " " + e.name + "=\"" + e.value + '"';
					});
				}
				html += ">\n";
				if ($(e).html()) {
					html += "\t";
					html += $(e).html();
					html += "\n</" + e.tagName + ">\n";
				}
			});
			$.Henry.paper.hide();
			area.show();
			area.val(html);
		} else {
			t.button.css("background-color", "transparent");
			area.hide();
			$.Henry.paper.html(area.val().replace(/[\n|\r|\t]+|[ ]{2,}]/g, ""));
			$.Henry.paper.show().focus();
		}
	}
};