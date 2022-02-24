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

	$(".upName").bind("blur", function() { // ログイン後の購入の編集
		var inputCardOwnerName = $(this).val();
		check_cardOwnerName(inputCardOwnerName,cardOwnerNameEditError);
	});

	$(".upMonth , .upYear").bind("blur", function() {
		var inputMonth = $(".upMonth").val();
		var inputYear = $(".upYear").val();

		check_date(inputMonth,inputYear,dateEditError);
	});

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
			var company = $("#cardCompany").text();
			var form = $("#addCreditCardForm");
			var url = "PurchseInsertCreditCardInfo?cardCompany=" + company;
			form.attr('action',url);

			form.submit();
		}
	});



	/*クレジットカード追加ボタンクリック時、追加用フォームを表示*/
	$("#addCreditCardButton").on('click',function(){
		$("#addCreditCardFormArea").fadeIn(1100); /*1100ミリ秒かけてフェードイン*/

		$("body").css('background-color','#BBBBBB');

		$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",true);
	});

	/*追加をキャンセルボタンクリック示時、追加用フォームを非表示にする*/
	$("#canselButton").on('click',function(){
		$("#addCreditCardFormArea").fadeOut();

		$("body").css('background-color','#ffffff');

		$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",false);
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

		$("#cardCompanyArea").text(company); // 判定結果の文字列をセット
	});*/
/*
	カード入力後の追加ボタンクリック時フォームをSubmitする。
	$("#executeAddButton").on('click',function(){
		$("#addCreditCardForm").submit();
	});*/

	/*クレジットカード情報一覧の変更ボタンクリック時値のセットとフォームの表示*/
	$(".creditCardInfo").each(function(){
		$(".openUpdateFormButton").on('click',function(){
			$(".errorText").html("");
			var targetCreditCardInfo = $(this).parent();

			var cardNo = targetCreditCardInfo.find(".lastFourDisits").html();
			var cardCompany = targetCreditCardInfo.find(".cardCompany").html();
			var cardOwnerName = targetCreditCardInfo.find(".cardOwnerName").html();
			var expirationDate = targetCreditCardInfo.find(".expirationDate").html();
			var cardId = targetCreditCardInfo.find(".openUpdateFormButton").val();

			var updateForm = $("#updateCreditCardInfoForm");

			updateForm.find("#lastFourDisitsCreditCardNo").val(cardNo);
			updateForm.find("#cardCompany").val(cardCompany);
			updateForm.find("#cardOwnerName").val(cardOwnerName);

			var separationDate = expirationDate.split("/");

			updateForm.find("#year").val(separationDate[0]);
			updateForm.find("#month").val(separationDate[1]);

			$("#executeUpdateButton").on('click',function(){
				console.log("osare");
				var cardOwnerNameEditError = $(".cardOwnerNameEditError");
				var dateEditError = $(".dateEditError");

				var cardOwner = $(".upName").val();
				var month = $(".upMonth").val();
				var year = $(".upYear").val();

				var result1 = check_cardOwnerName(cardOwner,cardOwnerNameEditError);
				var result2 = check_date(month,year,dateEditError);

				if(result1 && result2){
					var url = "purchaseUpdateCreditCardInfo?cardId=" + cardId;
					console.log(url);
					updateForm.attr('action',url);
					$("#updateCreditCardInfoForm").submit();
				}
			});

			$("#updateCardFormArea").fadeIn(1100); /*1100ミリ秒かけてフェードイン*/

			$("body").css('background-color','#BBBBBB');
			$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",true);
			$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("background","#BBBBBB");
			$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("border-color","#ccc");
			$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("pointer-events","none");
		});
	});

	/*編集キャンセルボタン処理*/
	$("#updateCansel").on('click',function(){
		$("#updateCardFormArea").fadeOut();

		$("body").css('background-color','#ffffff');

		$("body").find("#executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",false); /*ボタンの有効化*/
		$("body").find("#executeAddButton,.decisionButton").css("background","linear-gradient(#f7dfa5,#f0c14b)");
		$("body").find("#canselButton,.openUpdateFormButton , .openDeleteComfirmButton").css("background-color", "#e7e9ec");
		$("body").find("#canselButton,.openUpdateFormButton , .openDeleteComfirmButton").css("border-color", "#adb1b8");
		$("body").find("#executeAddButton,.decisionButton").css("border-color","#a88734");
		$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("pointer-events","auto");
		$("body").find("#canselButton,.openUpdateFormButton , .openDeleteComfirmButton").hover(function() {

		    //マウスを乗せたら色が変わる
		    $(this).css('background', '#c0c0c0');

		  //ここにはマウスを離したときの動作を記述
		  }, function() {

		    //色指定を空欄にすれば元の色に戻る
		    $(this).css('background', '');

		  });
		$("body").find("#executeAddButton,.decisionButton").hover(function() {

		    //マウスを乗せたら色が変わる
		    $(this).css('background', '#f1c85a');

		  //ここにはマウスを離したときの動作を記述
		  }, function() {

		    //色指定を空欄にすれば元の色に戻る
		    $(this).css('background', '');

		  });
		$(".errorText").html("");
	});


	/*クレジットカード情報一覧の消去ボタンクリック時値のセットとフォームの表示*/
	$(".creditCardInfo").each(function(){
		$(".openDeleteComfirmButton").on('click',function(){
			var targetCreditCardInfo = $(this).parent();

			var cardNo = targetCreditCardInfo.find(".lastFourDisits").html();
			var cardCompany = targetCreditCardInfo.find(".cardCompany").html();
			var cardOwnerName = targetCreditCardInfo.find(".cardOwnerName").html();
			var expirationDate = targetCreditCardInfo.find(".expirationDate").html();
			var cardId = targetCreditCardInfo.find(".openUpdateFormButton").val();

			var deletePage = $("#deleteCreditCardComfirmPageArea");

			deletePage.find(".lastFourDisits").html(cardNo);
			deletePage.find(".cardCompany").html(cardCompany);
			deletePage.find(".cardOwnerName").html(cardOwnerName);
			deletePage.find(".expirationDate").html(expirationDate);

			deletePage.find("#executeDeleteButton").on('click',function(){
				var url = "purchaseDeleteCreditCardInfo?cardId=" + cardId;
				window.location.href = url;
			});

			$("#deleteCreditCardComfirmPageArea").fadeIn(1100); /*1100ミリ秒かけてフェードイン*/

			$("body").css('background-color','#BBBBBB');

			$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",true);
			$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("background","#BBBBBB");
			$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("border-color","#e7e9ec");

		});
	});

	/*消去キャンセルボタン処理*/
	$("#deleteCansel").on('click',function(){
		$("#deleteCreditCardComfirmPageArea").fadeOut();

		$("body").css('background-color','#ffffff');

		$("body").find("#executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",false); /*ボタンの有効化*/
		$("body").find("#executeAddButton,.decisionButton").css("background","linear-gradient(#f7dfa5,#f0c14b)");
		$("body").find("#canselButton,.openUpdateFormButton , .openDeleteComfirmButton").css("background-color", "#e7e9ec");
		$("body").find("#canselButton,.openUpdateFormButton , .openDeleteComfirmButton").css("border-color", "#adb1b8");
		$("body").find("#executeAddButton,.decisionButton").css("border-color","#a88734");
		$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("pointer-events","auto");
		$("body").find("#canselButton,.openUpdateFormButton , .openDeleteComfirmButton").hover(function() {

		    //マウスを乗せたら色が変わる
		    $(this).css('background', '#c0c0c0');

		  //ここにはマウスを離したときの動作を記述
		  }, function() {

		    //色指定を空欄にすれば元の色に戻る
		    $(this).css('background', '');

		  });
		$("body").find("#executeAddButton,.decisionButton").hover(function() {

		    //マウスを乗せたら色が変わる
		    $(this).css('background', '#f1c85a');

		  //ここにはマウスを離したときの動作を記述
		  }, function() {

		    //色指定を空欄にすれば元の色に戻る
		    $(this).css('background', '');

		  });
		$(".errorText").html("");
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

