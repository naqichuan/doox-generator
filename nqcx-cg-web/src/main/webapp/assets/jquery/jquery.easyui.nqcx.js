/**
 * @Autor 黄保光 2013.06.06
 * 
 */
(function($) {
	/**
	 * submit the form
	 */
	function ajaxNqcxSubmit(target, options){
		options = options || {};
		
		var param = {};
		if (options.onSubmit){
			if (options.onSubmit.call(target, param) == false) {
				return;
			}
		}
		var datas = options.addDatas;
		
		var form = $(target);
		if (options.url){
			form.attr('action', options.url);
		}
		var frameId = 'easyui_frame_' + (new Date().getTime());
		var frame = $('<iframe id='+frameId+' name='+frameId+'></iframe>')
				.attr('src', window.ActiveXObject ? 'javascript:false' : 'about:blank')
				.css({
					position:'absolute',
					top:-1000,
					left:-1000
				});
		var t = form.attr('target'), a = form.attr('action');
		form.attr('target', frameId);
		
		var paramFields = $();
		try {
			for(var n in param){
				var f = $('<input type="hidden" name="' + n + '">').val(param[n]).appendTo(form);
				paramFields = paramFields.add(f);
			}
			for(var n in datas) {
				var f = $('<input type="hidden" name="' + n + '">').val(datas[n]).appendTo(form);
				paramFields = paramFields.add(f);
			}
			$.ajax({
				url: form.attr('action'),
				type: 'POST',
				dataType: "json",
				data: form.serialize(),
				success: cb
			});
		} finally {
			form.attr('action', a);
			t ? form.attr('target', t) : form.removeAttr('target');
			paramFields.remove();
		}
		
		var checkCount = 10;
		function cb(data){
			if (data == ''){
				if (--checkCount){
					setTimeout(cb, 100);
					return;
				}
				return;
			}
			if (options.success){
				options.success(data);
			}
			setTimeout(function(){
				frame.remove();
			}, 100);
		}
	}
	
	
	$.fn.form.methods.nqcxSubmit = function(jq, options) {
		return jq.each(function(){
			ajaxNqcxSubmit(this, $.extend({}, $.fn.form.defaults, options||{}));
		});
	};
})(jQuery);
