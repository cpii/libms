<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" type="text/css" href='__fix_me__' />

<style>
#main-error-div {
	width: 75%;
}
</style>

<div id="banner">
	<div id = "logo">
		<img src="__fix_me__" alt="__fix_me__"/>
	</div>
</div>

<hr/>

<div id="main-error-div">
	<!--  Exception message -->
	<div class="ui-widget">
		<div class="ui-state-error ui-corner-all" style="padding: 0 .7em;">

			<p>
				<span class="ui-icon ui-icon-alert"
					style="float: left; margin-right: .3em;"></span> Access
					to the specified resource has been denied for the following reason:
					<br/>
					<strong>Sufficient authority to access this resource not found!</strong>.
			</p>

		</div>
	</div>
</div>