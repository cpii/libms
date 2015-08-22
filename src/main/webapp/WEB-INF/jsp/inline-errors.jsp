<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

	<div class="ui-widget" id="disco-errors" >
		<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">

			<p>
				<span class="ui-icon ui-icon-alert"
					style="float: left; margin-right: .3em;"></span> <strong>Error:</strong>
				${exception.message}
			</p>

		</div>
	</div>