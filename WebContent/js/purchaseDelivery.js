var emptyPattern = /^[ 　\r\n\t]*$/; // スペースなども空白と判定するための正規表現
var telPattern = /[0-9]{11,12}/;
var postalCodePattern = /[0-9]{7}/;
$(function(){
	var deliveryNameError = $(".deliveryNameError");
	var telError = $(".telError");
	var postalCodeError = $(".postalCodeError");
	var addressError = $(".addressError");
	var houseNumberError = $(".houseNumberError");
	var deliveryNameEditError = $(".deliveryNameEditError");
	var telEditError = $(".telEditError");
	var postalCodeEditError = $(".postalCodeEditError");
	var addressEditError = $(".addressEditError");
	var houseNumberEditError = $(".houseNumberEditError");

	var addressObj = []; /*住所格納用配列(内部にオブジェクトを持つ)*/

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


	//--------------------------------住所編集----------------------------------

	$("#deliveryName").bind("blur", function() {
		var input_name = $(this).val();
		check_name(input_name,deliveryNameEditError);
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
		check_postalCode(input_pos,postalCodeEditError);
	});

	$("#address").bind("blur", function() {
		var input_address = $(this).val();
		check_address(input_address,addressEditError);
	});

	$("#houseNumber").bind("blur", function() {
		var input_houseNumber = $(this).val();
		check_houseNumber(input_houseNumber,houseNumberEditError);
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


	/*配送先情報一覧で住所のスラッシュを消去*/
	$(".deliveryInfo").each(function(){
		var beforeReplaceAddress = $(this).find(".address").html(); /*変換前住所*/

		var afterReplaceAddress = beforeReplaceAddress.replace(/\//g,""); /*変換後住所*/

		$(this).find(".address").html(afterReplaceAddress);

		addressObj.push({key:afterReplaceAddress,value:beforeReplaceAddress}); /*key:変換後住所 value:変換前住所をセット*/
	});

	/*配送先情報追加ボタンクリック時、追加用フォームを表示*/
	$("#addDeliveryInfoButton").on('click',function(){
		$("#addDeliveryInfoFormArea").fadeIn(1100); /*1100ミリ秒かけてフェードイン*/

		$("body").css('background-color','#BBBBBB');

		$("body").find("#addDeliveryInfoButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",true);
	});

	/*キャンセルボタンクリック示時、フォームを非表示にする*/
	$("#canselButton").on('click',function(){
		$("#addDeliveryInfoFormArea").fadeOut();

		$("body").css('background-color','#ffffff');

		$("body").find("#addDeliveryInfoButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",false);
		$(".errorText").html("");
	});

	/*配送先情報入力後の追加ボタンクリック時フォームをSubmitする。*/
	$("#executeAddButton").on('click',function(){
		$("#addDeliveryInfoForm").submit();
	});

	/*配送先情報情報一覧の変更ボタンクリック時値のセットとフォームの表示*/
	$("#deliveryInfoList").each(function(){
		$(".openUpdateFormButton").on('click',function(){
			$(".errorText").html("");
			var targetDeliveryInfo = $(this).parent();

			var deliveryName = targetDeliveryInfo.find(".deliveryName").html();
			var tel = targetDeliveryInfo.find(".tel").html();
			var postalCode = targetDeliveryInfo.find(".postalCode").html();
			var address = targetDeliveryInfo.find(".address").html();
			var deliveryInfoId = targetDeliveryInfo.find(".openUpdateFormButton").val();

			var beforeAddress = $.map(addressObj,function(value,index){
				if(value['key'] == address){
					return value['value'];
				}
			});

			var updateForm = $("#updateDeliveryInfoForm");

			updateForm.find("#deliveryName").val(deliveryName);

			var separationTel = tel.split("-");

			updateForm.find("#firstTelNo").val(separationTel[0]);
			updateForm.find("#secondTelNo").val(separationTel[1]);
			updateForm.find("#thirdTelNo").val(separationTel[2]);

			updateForm.find("#postalCode").val(postalCode);

			var separationAddress = beforeAddress.toString().split("/");

			updateForm.find("#address").val(separationAddress[0]);
			updateForm.find("#houseNumber").val(separationAddress[1]);
			updateForm.find("#buildingName").val(separationAddress[2]);

			console.log(deliveryInfoId);

			var deliveryNameError = $(".deliveryNameError");
			var telError = $(".telError");
			var postalCodeError = $(".postalCodeError");
			var addressError = $(".addressError");
			var houseNumberError = $(".houseNumberError");

			$("#executeUpdateButton").on('click',function(){
				var input_name = $("#deliveryName").val();
				var first = $("#firstTelNo").val();
				var second = $("#secondTelNo").val();
				var third = $("#thirdTelNo").val();
				var tel = (first.toString() + second.toString() + third.toString());
				var input_pos = $("#postalCode").val();
				var input_address = $("#address").val();
				var input_houseNumber = $("#houseNumber").val();

				var result1 = check_name(input_name,deliveryNameEditError);
				var result2 = check_tel(tel,telEditError);
				var result3 = check_postalCode(input_pos,postalCodeEditError);
				var result4 = check_address(input_address,addressEditError);
				var result5 = check_houseNumber(input_houseNumber,houseNumberEditError);

				if(result1 && result2 && result3 && result4 && result5){
					var url = "purchaseUpdateDeliveryInfo?deliveryInfoId=" + deliveryInfoId;
					console.log(url);
					updateForm.attr('action',url);
					updateForm.submit();
				}
			});
			$("#updateDeliveryFormArea").fadeIn(1100); /*1100ミリ秒かけてフェードイン*/

			$("body").css('background-color','#BBBBBB');

			$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton,#newCreditBtn").attr("disabled",true); /*ボタンの無効化*/
			$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton,#newCreditBtn").css("background","#BBBBBB");
			$("body").find(".decisionButton, .openUpdateFormButton , .openDeleteComfirmButton,#newCreditBtn").css("border-color","#ccc");
			$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton,#newCreditBtn").css("pointer-events","none");
		});
	});

	/*編集キャンセルボタン処理*/
	$("#updateCansel").on('click',function(){
		$("#updateDeliveryFormArea").fadeOut();

		$("body").css('background-color','#ffffff');

		$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton,#newCreditBtn").attr("disabled",false); /*ボタンの有効化*/
		$("body").find(".decisionButton,#newCreditBtn").css("background","linear-gradient(#f7dfa5,#f0c14b)");
		$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("background-color", "#e7e9ec");
		$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("border-color", "#adb1b8");
		$("body").find(".decisionButton,#newCreditBtn").css("border-color","#a88734");
		$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton,#newCreditBtn").css("pointer-events","auto");
		$("body").find(".openUpdateFormButton , .openDeleteComfirmButton").hover(function() {

		    //マウスを乗せたら色が変わる
		    $(this).css('background', '#c0c0c0');

		  //ここにはマウスを離したときの動作を記述
		  }, function() {

		    //色指定を空欄にすれば元の色に戻る
		    $(this).css('background', '');

		  });
		$("body").find(".decisionButton,#newCreditBtn").hover(function() {

		    //マウスを乗せたら色が変わる
		    $(this).css('background', '#f1c85a');

		  //ここにはマウスを離したときの動作を記述
		  }, function() {

		    //色指定を空欄にすれば元の色に戻る
		    $(this).css('background', '');

		  });
		$(".errorText").html("");
	});


	/*配送先情報一覧の消去ボタンクリック時値のセットとフォームの表示*/
	$("#deliveryInfoList").each(function(){
		$(".openDeleteComfirmButton").on('click',function(){
			var targetDeliveryInfo = $(this).parent();

			var deliveryName = targetDeliveryInfo.find(".deliveryName").html();
			var tel = targetDeliveryInfo.find(".tel").html();
			var postalCode = targetDeliveryInfo.find(".postalCode").html();
			var address = targetDeliveryInfo.find(".address").html();
			var deliveryInfoId = targetDeliveryInfo.find(".openDeleteComfirmButton").val();

			var deletePage = $("#deleteDeliveryInfoArea");

			deletePage.find(".name").html(deliveryName);
			deletePage.find(".tel").html(tel);
			deletePage.find(".postalCode").html(postalCode);
			deletePage.find(".address").html(address);

			deletePage.find("#executeDeleteButton").on('click',function(){
				var url = "purchaseDeleteDeliveryInfo?deliveryInfoId=" + deliveryInfoId;
				window.location.href = url;
			});

			$("#deleteDeliveryInfoArea").fadeIn(1100); /*1100ミリ秒かけてフェードイン*/

			$("body").css('background-color','#BBBBBB');

			$("body").find(".decisionButton, .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",true);/*ボタンの無効化*/
			$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("background","#BBBBBB");
			$("body").find(".decisionButton, .openUpdateFormButton , .openDeleteComfirmButton").css("border-color","#ccc");
			$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("pointer-events","none");
		});
	});

	/*消去キャンセルボタン処理*/
	$("#deleteCansel").on('click',function(){
		$("#deleteDeliveryInfoArea").fadeOut();

		$("body").css('background-color','#ffffff');

		$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",false); /*ボタンの有効化*/
		$("body").find(".decisionButton").css("background","linear-gradient(#f7dfa5,#f0c14b)");
		$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("background-color", "#e7e9ec");
		$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("border-color", "#adb1b8");
		$("body").find(".decisionButton ").css("border-color","#a88734");
		$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("pointer-events","auto");
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

