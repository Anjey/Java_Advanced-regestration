$(document).ready(function() {
	let selectedItem = localStorage.getItem("locales");
	$('#locales').val(selectedItem ? selectedItem : 'en');
	$('#locales').change(function() {
		let selectedOption = $('#locales').val();
		if (selectedOption) {
			window.location.replace('?lang=' + selectedOption);
			localStorage.setItem("locales", selectedOption);
		}
	});
});