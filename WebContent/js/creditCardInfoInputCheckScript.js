var emptyPattern = /^[ 　\r\n\t]*$/; // スペースなども空白と判定するための正規表現
var visaPattern = /^4[0-9]{12}(?:[0-9]{3})?$/;
var masterPattern = /^5[1-5][0-9]{14}$/;
var americanPattern = /^3[47][0-9]{13}$/;
var dinersPattern = /^3(?:0[0-5]|[68][0-9])[0-9]{11}$/;
var jcbPattern = /^(?:2131|1800|35\d{3})\d{11}$/;


$(function(){
	var cardNoError = $(".cardNoError");
	var cardOwnerNameError = $(".cardOwnerNameError");
	var dateError = $(".dateError");
	var securityCodeError = $(".securityCodeError");
	var cardOwnerNameEditError = $(".cardOwnerNameEditError");
	var dateEditError = $(".dateEditError");
//--------------------------------カード追加----------------------------------
	$("#creditCardNo").bind("blur", function() {
		console.log("error");
		var creditCardNo = $(this).val();
		check_cardNo(creditCardNo,cardNoError);
	});

	$("#inputCardOwnerName").bind("blur", function() {
		var inputCardOwnerName = $(this).val();
		check_cardOwnerName(inputCardOwnerName,cardOwnerNameError);
	});

	$("#inputMonth , #inputYear").bind("blur", function() {
		var inputMonth = $("#inputMonth").val();
		var inputYear = $("#inputYear").val();

		check_date(inputMonth,inputYear,dateError);
	});

	$("#inputSecurityCode").bind("blur", function() {
		var inputSecurityCode = $(this).val();
		check_securityCode(inputSecurityCode,securityCodeError);
	});

	$("#executeAddButton").on('click',function(){ // 編集の追加
		var cardNo = $("#creditCardNo").val();
		var cardOwner = $("#inputCardOwnerName").val();
		var month = $("#inputMonth").val();
		var year = $("#inputYear").val();
		var code = $("#inputSecurityCode").val();

		var result1 = check_cardNo(cardNo,cardNoError);
		var result2 = check_cardOwnerName(cardOwner,cardOwnerNameError);
		var result3 = check_date(month,year,dateError);
		var result4 = check_securityCode(code,securityCodeError);

		if(result1 && result2 && result3 && result4){
			var cardCmp = $("#cardCompany").html();

			var form = $("#addCreditCardForm");

			form.attr('action','addCreditCardInfo?cardCompany=' + cardCmp);

			console.log(cardCmp);

			form.submit();
		}
	});

//--------------------------------カード編集----------------------------------

	$("#cardOwnerName").bind("blur", function() { // 編集画面のカード編集
		var inputCardOwnerName = $(this).val();
		check_cardOwnerName(inputCardOwnerName,cardOwnerNameError);
	});

	$("#month , #year").bind("blur", function() {
		var inputMonth = $("#month").val();
		var inputYear = $("#year").val();

		check_date(inputMonth,inputYear,dateError);
	});
});

function check_cardNo(str,eObj){
	var _result = true;
	var _textbox = $.trim(str);

	$("#creditCardNo").val(convertNumber(str));

	if(_textbox.match(emptyPattern)){
		eObj.html("カード番号が入力されていません");
		_result = false;
	}else if(_textbox.match(visaPattern)){
		$("#cardCompany").text("VISA");
		eObj.html("");
		_result = true;
	}else if(_textbox.match(masterPattern)){
		$("#cardCompany").text("Master Card");
		eObj.html("");
		_result = true;
	}else if(_textbox.match(americanPattern)){
		$("#cardCompany").text("American Express");
		eObj.html("");
		_result = true;
	}else if(_textbox.match(dinersPattern)){
		$("#cardCompany").text("Diners Club");
		eObj.html("");
		_result = true;
	}else if(_textbox.match(jcbPattern)){
		$("#cardCompany").text("JCB");
		eObj.html("");
		_result = true;
	}else{
		eObj.html("カード番号が正しくありません");
		result = false;
	}
	return _result;
}

function check_cardOwnerName(str,eObj){
	var _result = true;
	var _textbox = $.trim(str);

	console.log(str);
	if(_textbox.match(emptyPattern)){
		eObj.html("カード名義人名が入力されていません");
		_result = false;
	}else{
		eObj.html("");
	}
	return _result;
}

function check_date(month,year,eObj){
	var _result = true;

	var exDate = new Date(year + "-" + month);
	var nowDate = new Date();

	var nowMonth = (nowDate.getMonth() + 1).toString().padStart(2,"0");

	console.log(month);
	console.log(year);

	if(month == "" || year == ""){
		eObj.html("有効期限が入力されていません");
		_result = false;
	}else if(nowDate > exDate){
		if(month != nowMonth){
			eObj.html("有効期限が過ぎています");
			_result = false;
		}else{
			eObj.html("");
			_result = true;
		}
	}else{
		eObj.html("");
	}
	return _result;
}

function check_securityCode(str,eObj){
	var _result = true;
	var _textbox = $.trim(str);

	$("#inputSecurityCode").val(convertNumber(str));

	if(_textbox.match(emptyPattern)){
		eObj.html("セキュリティコードが入力されていません");
		_result = false;
	}else if((_textbox.length == 3 && ($("#cardCompany").text() != "" && $("#cardCompany").text() != "American Express")) || ($("#cardCompany").text() == "American Express" && (_textbox.length == 4))){
		eObj.html("");
		_result = true;
	}else{
		eObj.html("セキュリティコードの書式が正しくありません");
		_result = false;
	}
	return _result;
}

function convertNumber(str) {
    return str.replace(/[Ａ-Ｚａ-ｚ０-９]/g, function(s) {
        return String.fromCharCode(s.charCodeAt(0) - 0xFEE0);
    });
}