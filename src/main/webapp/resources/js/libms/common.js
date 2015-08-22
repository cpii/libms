humane.info = humane.spawn({addnCls: 'humane-libnotify-info', timeout: 3000});
humane.success = humane.spawn({addnCls: 'humane-libnotify-success', timeout: 3000});
humane.error = humane.spawn({addnCls: 'humane-libnotify-error', timeout: 0, clickToClose: true});

var handleMenuItemsActiveState = function(activeMenuItem) {
	var $this = $("#" + activeMenuItem);
	$this.siblings(".active").removeClass("active");
	$this.addClass("active");
}

$(document).ready(function() {
	// Main menu's active items
	/*$("#main-menu-bar li a").on("click", function(e) {
		var $li = $(this).closest("li");
		$li.siblings(".active").removeClass("active");
		$li.addClass("active");
		e.preventDefault();
	});*/
});
