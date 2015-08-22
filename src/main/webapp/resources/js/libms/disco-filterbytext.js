/**
 * ben.smithlea@utoronto.ca
 * 
 * Add a text filter box above a select element. Optionally
 * load option elements for the select element via ajax by setting
 * the 'source' option like so:
 * 
 * $('#myDropdownList').textFilter({'source', 'foo/appl/bar/json'});
 */
(function($, undefined) {
	$.widget('disco.textFilter', {
		options: {
			textbox: '#textbox',
			selectSingleMatch: false,
			widgetClass: 'textFilter',
			source: 'dom',
			debug: false
		},
		_create: function() {
			this.debug && this.log('textFilter: _create called');
			var self = this;
			self.element.addClass(self.options.widgetClass);			
			$(self.options.textbox).bind('change.textFilter keyup.textFilter', $.proxy(self._onChangeTextbox, self));
		},
		_init: function() {
			this.debug && this.log('textFilter: _init called');
			this._loadOptionElements();
		},
		_loadOptionElements: function() {
			var self = this;
			self.optionElements = [];
			if ( self.options.source === 'dom' ) {
				self.element.find('option').each(function() {
					self.optionElements.push({
						value: $(this).val(),
						text: $(this).text()
					});
				});
				self._appendAllOptionElements();
			} else {
				var url = self.options.source;
				if ( ! self.options.cache ) {
					// TODO what if url already has params?
					url += '?' + self.no_cache_param();
				} 
				$.ajax({
					url: self.options.source, 
					global: false,
					dataType: 'json',
					type: 'get',
					success: $.proxy(self._loadOptionElementsCallback, self),
					error: function(xhr) {
						self.log('Error loading search contents: ' + xhr.responseText);
					}
				});
			}
		},
		_loadOptionElementsCallback: function(data) {
			var self = this;
			self.element.empty();
			$.each(data, function(i){
				self.optionElements.push({
					value: data[i].id,
					text: data[i].name
				});
			});
			self._appendAllOptionElements();
		},
		_appendAllOptionElements: function() {
			var self = this;
			$.each(self.optionElements, function(i) {
				var o = self.optionElements[i];
				self.element.append(
						$('<option>')
							.text(o.text)
							.val(o.value));
			});
		},
		_onChangeTextbox: function(event) {
			var self = this;
			self.element.empty();
			var search = $.trim($(event.target).val());
			var regex = new RegExp(search, "gi");
			$.each(self.optionElements, function(i) {
				var option = self.optionElements[i];
				if (option.text.match(regex) !== null) {
					self.element.append(
							$('<option>').text(option.text).val(
									option.value));
				}
			});
			if (self.options.selectSingleMatch === true
					&& self.element.children().length === 1) {
				self.element.children().get(0).selected = true;
			}			
		},
		no_cache_param: function() {
			return '_=' + new Date().getTime();
		},
		log: function(msg) {
			window.console && console.log(msg);
		},
		destroy: function() {
			this.debug && this.log('destroy called');
			this.element.removeClass(this.options.widgetClass);
			$.Widget.prototype.destroy.apply(this, arguments);
		}
	});
})(jQuery);