$(function(){
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

	/*クレジットカード番号入力中動的に、カード会社判定を行う。*/
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
	});

	/*カード入力後の追加ボタンクリック時フォームをSubmitする。*/
	$("#executeAddButton").on('click',function(){
		$("#addCreditCardForm").submit();
	});

	/*クレジットカード情報一覧の変更ボタンクリック時値のセットとフォームの表示*/
	$(".creditCardInfo").each(function(){
		$(".openUpdateFormButton").on('click',function(){
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

			updateForm.find("#executeUpdateButton").on('click',function(){
				var url = "purchaseUpdateCreditCardInfo?cardId=" + cardId;
				updateForm.attr('action',url);
			});

			$("#updateCardFormArea").fadeIn(1100); /*1100ミリ秒かけてフェードイン*/

			$("body").css('background-color','#BBBBBB');
			$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",true);
			$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("background","#BBBBBB");
			$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("border-color","#ccc");
			$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("pointer-events","none");
		});
	});

	/*編集完了ボタンクリック時のsubmit処理*/
	$("executeUpdateButton").on('click',function(){
		$("#updateCreditCardInfoForm").submit();
	});

	/*編集キャンセルボタン処理*/
	$("#updateCansel").on('click',function(){
		$("#updateCardFormArea").fadeOut();

		$("body").css('background-color','#ffffff');

		$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",false); /*ボタンの有効化*/
		$("body").find("#executeAddButton,.decisionButton").css("background","linear-gradient(#f7dfa5,#f0c14b)");
		$("body").find("#canselButton,.openUpdateFormButton , .openDeleteComfirmButton").css("background-color", "#e7e9ec");
		$("body").find("#canselButton,.openUpdateFormButton , .openDeleteComfirmButton").css("border-color", "#adb1b8");
		$("body").find("#executeAddButton,.decisionButton").css("border-color","#a88734");
		$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("pointer-events","auto");

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

		$("body").find(".decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",false); /*ボタンの有効化*/
		$("body").find("#executeAddButton,.decisionButton").css("background","linear-gradient(#f7dfa5,#f0c14b)");
		$("body").find("#canselButton,.openUpdateFormButton , .openDeleteComfirmButton").css("background-color", "#e7e9ec");
		$("body").find("#canselButton,.openUpdateFormButton , .openDeleteComfirmButton").css("border-color", "#adb1b8");
		$("body").find("#executeAddButton,.decisionButton").css("border-color","#a88734");
		$("body").find("#canselButton, #executeAddButton,.decisionButton , .openUpdateFormButton , .openDeleteComfirmButton").css("pointer-events","auto");

	});
});

