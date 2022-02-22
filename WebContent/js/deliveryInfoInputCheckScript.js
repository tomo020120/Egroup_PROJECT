var emptyPattern = /^[ 　\r\n\t]*$/; // スペースなども空白と判定するための正規表現
var telPattern = /[0-9]{11,12}/;
var postalCodePattern = /[0-9]{7}/;

$(function(){
	var deliveryNameError = $(".deliveryNameError");
	var telError = $(".telError");
	var postalCodeError = $(".postalCodeError");
	var addressError = $(".addressError");
	var houseNumberError = $(".houseNumberError");
//--------------------------------住所追加----------------------------------
	$("#inputName").bind("blur", function() {
		var input_name = $(this).val();
		check_name(input_name,deliveryNameError);
	});

	$("#inputFirstNum , #inputSecondNum , #inputThirdNum").bind("blur", function() {
		var first = $("#inputFirstNum").val();
		var second = $("#inputSecondNum").val();
		var third = $("#inputThirdNum").val();

		var tel = (first.toString() + second.toString() + third.toString());

		var input_num = tel;
		check_tel(input_num,telError);
	});

	$("#inputPostalCode").bind("blur", function() {
		var input_pos = $(this).val();
		check_postalCode(input_pos,postalCodeError);
	});

	$("#inputAddress").bind("blur", function() {
		var input_address = $(this).val();
		check_address(input_address,addressError);
	});

	$("#inputHouseNumber").bind("blur", function() {
		var input_houseNumber = $(this).val();
		check_houseNumber(input_houseNumber,houseNumberError);
	});

	$("#executeAddButton").on('click',function(){ // 編集画面の追加
		var input_name = $("#inputName").val();
		var first = $("#inputFirstNum").val();
		var second = $("#inputSecondNum").val();
		var third = $("#inputThirdNum").val();
		var tel = (first.toString() + second.toString() + third.toString());
		var input_pos = $("#inputPostalCode").val();
		var input_address = $("#inputAddress").val();
		var input_houseNumber = $("#inputHouseNumber").val();

		var result1 = check_name(input_name,deliveryNameError);
		var result2 = check_tel(tel,telError);
		var result3 = check_postalCode(input_pos,postalCodeError);
		var result4 = check_address(input_address,addressError);
		var result5 = check_houseNumber(input_houseNumber,houseNumberError);

		if(result1 && result2 && result3 && result4 && result5){
			$("#addDeliveryInfoForm").submit();
		}
	});

	$("#newCreditBtn").on('click',function(){ // 購入画面の新規追加
		var input_name = $("#inputName").val();
		var first = $("#inputFirstNum").val();
		var second = $("#inputSecondNum").val();
		var third = $("#inputThirdNum").val();
		var tel = (first.toString() + second.toString() + third.toString());
		var input_pos = $("#inputPostalCode").val();
		var input_address = $("#inputAddress").val();
		var input_houseNumber = $("#inputHouseNumber").val();

		var result1 = check_name(input_name,deliveryNameError);
		var result2 = check_tel(tel,telError);
		var result3 = check_postalCode(input_pos,postalCodeError);
		var result4 = check_address(input_address,addressError);
		var result5 = check_houseNumber(input_houseNumber,houseNumberError);

		if(result1 && result2 && result3 && result4 && result5){
			$("#registDeliveryInfoForm").submit();
		}
	});

//--------------------------------住所編集----------------------------------

	$("#deliveryName").bind("blur", function() {
		var input_name = $(this).val();
		check_name(input_name,deliveryNameError);
	});

	$("#firstTelNo , #secondTelNo , #thirdTelNo").bind("blur", function() {
		var first = $("#firstTelNo").val();
		var second = $("#secondTelNo").val();
		var third = $("#thirdTelNo").val();

		var tel = (first.toString() + second.toString() + third.toString());

		var input_num = tel;
		check_tel(input_num,telError);
	});

	$("#postalCode").bind("blur", function() {
		var input_pos = $(this).val();
		check_postalCode(input_pos,postalCodeError);
	});

	$("#address").bind("blur", function() {
		var input_address = $(this).val();
		check_address(input_address,addressError);
	});

	$("#houseNumber").bind("blur", function() {
		var input_houseNumber = $(this).val();
		check_houseNumber(input_houseNumber,houseNumberError);
	});

	$("#executeUpdateButton").on('click',function(){
		var result;
		var input_name = $("#deliveryName").val();
		var first = $("#firstTelNo").val();
		var second = $("#secondTelNo").val();
		var third = $("#thirdTelNo").val();
		var tel = (first.toString() + second.toString() + third.toString());
		var input_pos = $("#postalCode").val();
		var input_address = $("#address").val();
		var input_houseNumber = $("#houseNumber").val();

		var result1 = check_name(input_name,deliveryNameError);
		var result2 = check_tel(tel,telError);
		var result3 = check_postalCode(input_pos,postalCodeError);
		var result4 = check_address(input_address,addressError);
		var result5 = check_houseNumber(input_houseNumber,houseNumberError);

		if(result1 && result2 && result3 && result4 && result5){
			updateForm.submit();
		}
	});

});

function check_name(str,eObj){
	var _result = true;
	var _textbox = $.trim(str);


	if(_textbox.match(emptyPattern)){
		eObj.html("ユーザー名が入力されていません");
		_result = false;
	}else{
		eObj.html("");
	}
	return _result;
}

function check_tel(str,eObj){
	var _result = true;
	var _textbox = $.trim(str);

	console.log(str);
	if(_textbox.match(emptyPattern)){
		eObj.html("電話番号が入力されていません");
		_result = false;
	}else if(!_textbox.match(telPattern)){
		eObj.html("電話番号の書式が正しくありません");
		_result = false;
	}else{
		eObj.html("");
	}
	return _result;
}

function check_postalCode(str,eObj){
	var _result = true;
	var _textbox = $.trim(str);

	if(_textbox.match(emptyPattern)){
		eObj.html("郵便番号が入力されていません");
		_result = false;
	}else if(_textbox.match(/\-/)){
		eObj.html("ハイフンを含むことはできません");
		_result = false;
	}else if(!_textbox.match(postalCodePattern)){
		eObj.html("郵便番号の書式が正しくありません");
		_result = false;
	}else{
		eObj.html("");
	}
	return _result;
}

function check_address(str,eObj){
	var _result = true;
	var _textbox = $.trim(str);

	if(_textbox.match(emptyPattern)){
		eObj.html("住所が入力されていません");
		_result = false;
	}else if(_textbox.length > 300){
		eObj.html("住所が長すぎます");
		_result = false;
	}else{
		eObj.html("");
	}
	return _result;
}

function check_houseNumber(str,eObj){
	var _result = true;
	var _textbox = $.trim(str);

	if(_textbox.match(emptyPattern)){
		eObj.html("丁目・番地・号が入力されていません");
		_result = false;
	}else{
		eObj.html("");
	}
	return _result;
}