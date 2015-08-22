(function($){
	module("focus-home");
	
	test('initial setup', function() {
		expect(1);
		ok($('#home-container'),'home-container div exists');
	});
	
/*	test('clear-selection-unchecks-all-profiles-checkbox', function(){
		expect(2)
		$('input[name=all-profiles-checkbox]').attr('checked','checked');
		equal($('input[name=all-profiles-checkbox]').attr('checked'), true, 'All Profiles checkbox should be checked');

		$('#home-profiles-total-results-unselect-link').click();
		equal($('input[name=all-profiles-checkbox]').attr('checked'), false, 'All Profiles checkbox should be unchecked');
	});
	
	test('all-profiles-checkbox', function() {
		expect(4);
		// setup - nothing selected.
		$('input[name=all-profiles-checkbox]').removeAttr('checked');
		$('.profile-checkboxes').removeAttr('checked');
		
		equal(_focus_home.is_all_profiles_selected(), false, 'Expect all profiles are not selected' );
		equal(_focus_home.is_any_profile_selected(), false, 'Expect no profile is selected.');
		
		$('input[name=all-profiles-checkbox]').click();
		equal(_focus_home.is_all_profiles_selected(), true, 'Expect all profiles are selected' );
		equal(_focus_home.is_any_profile_selected(), true, 'Expect any profiles are selected.');
		
	});*/
	
	test('selected_profile_data is empty', function(){
		expect(2);
		var data = _focus_common.get_selected_profile_data('#none-selected-form');
		equal(data.profiles, undefined, 'data should be empty');
		equal(data.error, "ERROR: No profiles selected")
	});
	
	test('selected profile data is size one', function() {
		var data = _focus_common.get_selected_profile_data('#one-selected-form');
		equal(data.profiles.length, 1, 'data should be size of 1');		
	});

	test('selected profile data is all', function() {
		var data = _focus_common.get_selected_profile_data('#all-selected-form');
		equal(data.profiles.length, 3, 'data should be size of 3');		
	});
	
	test('is_any_profile_selected when none selected', function(){
		var result = _focus_common.is_any_profile_selected('#none-selected-form');
		equal(result, false);
	});

	test('is_any_profile_selected when one selected', function(){
		var result = _focus_common.is_any_profile_selected('#one-selected-form');
		equal(result, true);
	});

	test('is_any_profile_selected when all selected', function(){
		var result = _focus_common.is_any_profile_selected('#all-selected-form');
		equal(result, true);
	});

	test('is_all_profiles_selected when none selected', function(){
		var result = _focus_home.is_all_profiles_selected('#none-selected-form');
		equal(result, false);
	});
	
	test('is_all_profiles_selected when one selected', function(){
		var result = _focus_home.is_all_profiles_selected('#one-selected-form');
		equal(result, false);
	});
	
	test('is_all_profiles_selected when all selected', function(){
		var result = _focus_home.is_all_profiles_selected('#all-selected-form');
		equal(result, true);
	});


})(jQuery);