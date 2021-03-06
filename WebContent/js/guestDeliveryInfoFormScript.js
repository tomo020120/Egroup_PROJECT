var emptyPattern = /^[ 　\r\n\t]*$/; // スペースなども空白と判定するための正規表現
var telPattern = /[0-9]{11,12}/;
var postalCodePattern = /[0-9]{7}/;
var mailTextPattern = /^[A-Za-z0-9]{1}[A-Za-z0-9_.-]*@{1}[A-Za-z0-9_.-]{1,}.[A-Za-z0-9]{1,}$/; // メアドの正規表現

$(function(){
	console.log(sessionStorage.getItem('guestPurchaseInfo').split(","));

	var userNameError = $(".userNameError");
	var mailError = $(".mailError");
	var deliveryNameError = $(".deliveryNameError");
	var telError = $(".telError");
	var postalCodeError = $(".postalCodeError");
	var addressError = $(".addressError");
	var houseNumberError = $(".houseNumberError");
//--------------------------------住所追加----------------------------------

	$("#inputUserName").bind("blur", function() {
		var input_userName = $(this).val();
		check_name(input_userName,userNameError);
	});

	$("#inputMail").bind("blur", function() {
		var input_mail = $(this).val();
		check_mail(input_mail,mailError);
	});

	$("#inputName").bind("blur", function() {
		var input_name = $(this).val();
		check_deliveryName(input_name,deliveryNameError);
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

	$("#guestCreditCardInfoFormJumpBtn").on('click',function(){ // 購入画面の新規追加
		var input_userName = $("#inputUserName").val();
		var input_mail = $("#inputMail").val();
		var input_name = $("#inputName").val();
		var first = $("#inputFirstNum").val();
		var second = $("#inputSecondNum").val();
		var third = $("#inputThirdNum").val();
		var tel = (first.toString() + second.toString() + third.toString());
		var input_pos = $("#inputPostalCode").val();
		var input_address = $("#inputAddress").val();
		var input_houseNumber = $("#inputHouseNumber").val();

		var result1 = check_name(input_userName,userNameError);
		var result2 = check_mail(input_mail,mailError);
		var result3 = check_deliveryName(input_name,deliveryNameError);
		var result4 = check_tel(tel,telError);
		var result5 = check_postalCode(input_pos,postalCodeError);
		var result6 = check_address(input_address,addressError);
		var result7 = check_houseNumber(input_houseNumber,houseNumberError);

		if(result1 && result2 && result3 && result4 && result5 && result6 && result7){
			var guestPurchaseInfoArray = sessionStorage.getItem('guestPurchaseInfo').split(",");

			var length = parseInt(guestPurchaseInfoArray.length);


			var fullAddress = "";
			var tel = "";

			$("#userInfoInputArea,#addDeliveryInfoForm").find("input").each(function(index,element){
				switch(index){
				case 0:
				case 1:
				case 2:
					guestPurchaseInfoArray[length + index] = element.value;
					break;
				case 3:
				case 4:
					tel += ((element.value) + "-");
					break;
				case 5:
					tel += (element.value);
					guestPurchaseInfoArray[length + 3] = tel;
					break;
				case 6:
					guestPurchaseInfoArray[length + 4] = element.value;
					break;
				case 7:
				case 8:
				case 9:
					fullAddress += element.value
					guestPurchaseInfoArray[length + 5] = fullAddress;
					break;
				}
			});

			console.log(guestPurchaseInfoArray);

			sessionStorage.setItem('guestPurchaseInfo',guestPurchaseInfoArray.toString());

			window.location.href="guestCreditCardInfoForm";
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

function check_mail(str,eObj){ // メアドチェック関数
	var _result = true;
	var _textbox = $.trim(str);

	console.log(_textbox);

	if(_textbox.match(emptyPattern)){
		eObj.html("メールアドレスを入力してください");
		_result = false;
	}else if(!_textbox.match(mailTextPattern)){
		eObj.html("正しいメールアドレスの書式ではありません");
		_result = false;
	}else{
		eObj.html("");
	}
	return _result;
}

function check_deliveryName(str,eObj){
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

