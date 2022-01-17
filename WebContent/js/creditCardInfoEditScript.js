$(function(){
	/*クレジットカード追加ボタンクリック時、追加用フォームを表示*/
	$("#addCreditCardButton").on('click',function(){
		$("#addCreditCardFormArea").fadeIn(1100); /*1100ミリ秒かけてフェードイン*/

		$("body").css('background-color','#BBBBBB');
	});

	/*追加をキャンセルボタンクリック示時、追加用フォームを非表示にする*/
	$("#canselButton").on('click',function(){
		$("#addCreditCardFormArea").fadeOut();

		$("body").css('background-color','#ffffff');
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

		$("#cardCompany").text(company); // 判定結果の文字列をセット
	});

	/*カード入力後の追加ボタンクリック時フォームをSubmitする。*/
	$("#executeAddButton").on('click',function(){
		$("#addCreditCardForm").submit();
	});
});

