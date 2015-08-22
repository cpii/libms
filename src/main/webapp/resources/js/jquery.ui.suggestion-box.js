/**
 * @author ben.smithlea@utoronto.ca
 * 
 * Thanks to http://http://www.debuggable.com/tim
 */
;(function($) {
//    "use strict";
    
    $.widget('ui.suggestion_box', {
    	options: {
            size: 4,
            height: '150px',
        	width: '150px',
        	delay: 100,
        	zindex: 3000
    	},
    	_create: function() {
    		var self = this;
    		this._suggestify();
            this.suggestion_list.bind('change', function(e){
                self.suggestion.val($(this).val());
                $(this).slideUp(self.options.delay);
            });
            
            this.suggestion.bind('keyup', function(e){
                self.suggestion_list.slideDown(self.options.delay);
                self._filter_suggestions();
            }).bind('click', function(e){
                self.suggestion_list.slideDown(self.options.delay);
                self._filter_suggestions();
            });
            
            this.element.closest('form').bind('click', function(e){
            	if ( ! (self.suggestion.is(':focus') || self.suggestion_list.is(':focus')) ){
            		//console.log('suggestion box is blurry');
            		self.suggestion_list.slideUp(self.options.delay);
            	} else {
            		// console.log('suggestion box has focus');      
            	}
            });

    	},

    	_setOption: function(key, value) {
			this.options[key] = value;
			this._suggestify();
    	},
    	
    	_suggestify: function(){
    		this.suggestion = this.element.find('input').eq(0)
    			.attr('autocomplete', 'off');
    		this.suggestion_list = this.element.find('select').eq(0)
	    		.attr('size', this.options.size)
	    		.css('height', this.options.height)
	    		.css('width', this.options.width)
	    		.css('position', 'absolute')
	    		.css('z-index', this.options.zindex)
	    		.hide();
	       var suggestion_options = [];
	       this.suggestion_list.find('option').each(function() {
    			suggestion_options.push({
    				value : $(this).val(),
    				text : $(this).text()
    			});
	       });
	       this.suggestion_list.data('suggestion_options', suggestion_options);
    	},
    	
    	_filter_suggestions: function(){
	    	var self = this;
	    	var suggestion_options = self.suggestion_list.empty().data('suggestion_options');
			var search = $.trim(self.suggestion.val());
			var regex = new RegExp(search, "gi");
			$.each(suggestion_options, function(i) {
				var o = suggestion_options[i];
				if (o.text.match(regex) !== null) {
					self.suggestion_list.append(
						$('<option>')
							.text(o.text)
							.val(o.value));
				}
			});
    	}
    	
    });
})(jQuery);