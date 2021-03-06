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

	$("#guestPurchaseConfirmPageJumpBtn").on('click',function(){ // 編集の追加
		var cardNo = $("#creditCardNo").val();
		var cardOwner = $("#inputCardOwnerName").val();
		var month2 = $("#inputMonth").val();
		var year2 = $("#inputYear").val();
		var code = $("#inputSecurityCode").val();

		var result1 = check_cardNo(cardNo,cardNoError);
		var result2 = check_cardOwnerName(cardOwner,cardOwnerNameError);
		var result3 = check_date(month2,year2,dateError);
		var result4 = check_securityCode(code,securityCodeError);

		if(result1 && result2 && result3 && result4){
			var guestPurchaseInfoArray = sessionStorage.getItem('guestPurchaseInfo').split(",");

			var length = parseInt(guestPurchaseInfoArray.length);

				var month = "";
				var year = "";
				var expirationDate = "";

				$("#addCreditCardForm").find(".target").each(function(index,element){
					switch(index){
					case 0:guestPurchaseInfoArray[10] = (element.value).slice(-4);
					break;
					case 1:guestPurchaseInfoArray[11] = $(element).text();
					break;
					case 2:guestPurchaseInfoArray[12] = element.value;
					break;
					case 3:month = element.value;
					break;
					case 4:year = element.value;
					break;
					}
				});

				expirationDate = (year + "/" + month);
				guestPurchaseInfoArray[13] = expirationDate;

				console.log(guestPurchaseInfoArray);

				sessionStorage.setItem('guestPurchaseInfo',guestPurchaseInfoArray.toString());

				var cardNo = $("#creditCardNo").val();
				var securityCode = $("#inputSecurityCode").val();

				window.location.href="guestPurchaseConfirmPage?cardNo=" + cardNo + "&securityCode=" + securityCode;
		}

	});


	/*クレジットカード番号入力中動的に、カード会社判定を行う。
	$("#creditCardNo").on("input",function(){
		var inputNo = $(this).val(); //入力値取得
		var firstCardNo = inputNo.slice(0,1);
		var company = "";

		if(firstCardNo == 4){ //頭文字で判定
			company = "VISA";
		}else if(firstCardNo == 5){
			company = "Master Card"
		}else{
			var firstTwoDigitsCardNo = inputNo.slice(0,2);

			if(firstTwoDigitsCardNo == 35){
				company = "JCB";
			}else if(firstTwoDigitsCardNo == 36){
				company = "Diners Club";
			}else if((firstTwoDigitsCardNo == 34) || (firstTwoDigitsCardNo == 37 )){
				company = "American Express";
			}
		}

		$("#cardCompany").html(company); // 判定結果の文字列をセット
	});
*/
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