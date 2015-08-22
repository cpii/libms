<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div>
	<!--  Exception message -->
	<div class="ui-widget">
		<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">

			<p>
				<span class="ui-icon ui-icon-alert"
					style="float: left; margin-right: .3em;"></span> <strong>Error:</strong>
				${exception.message}
			</p>

		</div>
	</div>
	<div class="error-stack-trace">
		<br />
		<!-- Exception Trace -->
		<div class="ui-widget">
			<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">

				<p>
					<span class="ui-icon ui-icon-alert"
						style="float: left; margin-right: .3em;"></span> <strong>Stack
						Trace (for Huron TG Help Desk):</strong>
				<pre>
				<c:forEach items="${exception.stackTrace}" var="element">
					<c:out value="${element}" />
				</c:forEach>
				</pre>
				</p>
			</div>
		</div>
	</div><!-- end error-stack-trace -->
</div>