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

		$("body").find("#addDeliveryInfoButton , .openUpdateFormButton , .openDeleteComfirmButton , #completeBtn").attr("disabled",true);
	});

	/*キャンセルボタンクリック示時、フォームを非表示にする*/
	$("#canselButton").on('click',function(){
		$("#addDeliveryInfoFormArea").fadeOut();

		$("body").css('background-color','#ffffff');

		$("body").find("#addDeliveryInfoButton , .openUpdateFormButton , .openDeleteComfirmButton , #completeBtn").attr("disabled",false);

		$(".errorText").html("");
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

				console.log("編集ボタン押された");

				if(result1 && result2 && result3 && result4 && result5){
					var url = "updateDeliveryInfo?deliveryInfoId=" + deliveryInfoId;
					console.log(url);
					updateForm.attr('action',url);
					updateForm.submit();
				}
			});

			$("#updateDeliveryFormArea").fadeIn(1100); /*1100ミリ秒かけてフェードイン*/

			$("body").css('background-color','#BBB');

			$("body").find("#addDeliveryInfoButton , .openUpdateFormButton , .openDeleteComfirmButton , #completeBtn").attr("disabled",true); /*ボタンの無効化*/
		});
	});

	/*編集キャンセルボタン処理*/
	$("#updateCansel").on('click',function(){
		$("#updateDeliveryFormArea").fadeOut();

		$("body").css('background-color','#ffffff');

		$("body").find("#addDeliveryInfoButton , .openUpdateFormButton , .openDeleteComfirmButton , #completeBtn").attr("disabled",false); /*ボタンの有効化*/

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
				var url = "deleteDeliveryInfo?deliveryInfoId=" + deliveryInfoId;
				window.location.href = url;
			});

			$("#deleteDeliveryInfoArea").fadeIn(1100); /*1100ミリ秒かけてフェードイン*/

			$("body").css('background-color','#BBBBBB');

			$("body").find("#addDeliveryInfoButton , .openUpdateFormButton , .openDeleteComfirmButton , #completeBtn").attr("disabled",true); /*ボタンの無効化*/
		});
	});

	/*消去キャンセルボタン処理*/
	$("#deleteCansel").on('click',function(){
		$("#deleteDeliveryInfoArea").fadeOut();

		$("body").css('background-color','#ffffff');

		$("body").find("#addDeliveryInfoButton , .openUpdateFormButton , .openDeleteComfirmButton , #completeBtn").attr("disabled",false); /*ボタンの有効化*/
	});
});

