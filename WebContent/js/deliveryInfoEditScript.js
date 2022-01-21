$(function(){
	var addressObj = []; /*住所格納用配列(内部にオブジェクトを持つ)*/

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
	});

	/*配送先情報入力後の追加ボタンクリック時フォームをSubmitする。*/
	$("#executeAddButton").on('click',function(){
		$("#addDeliveryInfoForm").submit();
	});

	/*配送先情報情報一覧の変更ボタンクリック時値のセットとフォームの表示*/
	$("#deliveryInfoList").each(function(){
		$(".openUpdateFormButton").on('click',function(){
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

			updateForm.find("#executeUpdateButton").on('click',function(){
				var url = "updateDeliveryInfo?deliveryInfoId=" + deliveryInfoId;
				console.log(url);
				updateForm.attr('action',url);
				updateForm.submit();
			});

			$("#updateDeliveryFormArea").fadeIn(1100); /*1100ミリ秒かけてフェードイン*/

			$("body").css('background-color','#BBBBBB');

			$("body").find("#addDeliveryInfoButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",true); /*ボタンの無効化*/
		});
	});

	/*編集キャンセルボタン処理*/
	$("#updateCansel").on('click',function(){
		$("#updateDeliveryFormArea").fadeOut();

		$("body").css('background-color','#ffffff');

		$("body").find("#addDeliveryInfoButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",false); /*ボタンの有効化*/
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
				var url = "deleteDeliveryInfo?deliveryInfoId=" + deliveryInfoId;
				window.location.href = url;
			});

			$("#deleteDeliveryInfoArea").fadeIn(1100); /*1100ミリ秒かけてフェードイン*/

			$("body").css('background-color','#BBBBBB');

			$("body").find("#addDeliveryInfoButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",true); /*ボタンの無効化*/
		});
	});

	/*消去キャンセルボタン処理*/
	$("#deleteCansel").on('click',function(){
		$("#deleteDeliveryInfoArea").fadeOut();

		$("body").css('background-color','#ffffff');

		$("body").find("#addDeliveryInfoButton , .openUpdateFormButton , .openDeleteComfirmButton").attr("disabled",false); /*ボタンの有効化*/
	});
});

