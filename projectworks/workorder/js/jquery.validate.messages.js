/*
 * By default, OD uses the jquery validation plugin for validating input fields. 
 * Therefore validation messages have to be localized for different languages.
 * 
 * These are sample messages. The following English version is already embedded in the jquery.validate.js.
 * To make it work for an other language, simple make a copy of this file using the same name 
 * and localize the text enclosed by double-quotes.
 * Once the text is localized, copy the file directly under the <language> directory in the OD project.
 * 
 * Before you start any work, check the following github repository for the localized file you need. 
 * Most of the common languages seem to have been covered. You may find one to help you to start. If you find
 * one that matches your language, make sure you change the name to jquery.validation.messages.js and drop it into the 
 * <language> directory of the OD project.
 * 
 * https://github.com/jzaefferer/jquery-validation/tree/master/src/localization
 */
$.extend($.validator.messages, {
	required: "This field is required.",
	remote: "Please fix this field.",
	email: "Please enter a valid email address.",
	url: "Please enter a valid URL.",
	date: "Please enter a valid date.",
	dateISO: "Please enter a valid date ( ISO ).",
	number: "Please enter a valid number.",
	digits: "Please enter only digits.",
	creditcard: "Please enter a valid credit card number.",
	equalTo: "Please enter the same value again.",
	maxlength: $.validator.format( "Please enter no more than {0} characters." ),
	minlength: $.validator.format( "Please enter at least {0} characters." ),
	rangelength: $.validator.format( "Please enter a value between {0} and {1} characters long." ),
	range: $.validator.format( "Please enter a value between {0} and {1}." ),
	max: $.validator.format( "Please enter a value less than or equal to {0}." ),
	min: $.validator.format( "Please enter a value greater than or equal to {0}." ),
	
	//The following messages are add-on OD specific
	apperror: "Error",
	apperror_dismiss: "Dismiss"
});
