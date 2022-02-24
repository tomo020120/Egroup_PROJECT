$(function(){
	var itemIdList; // 消去したい商品IDをカンマ区切りで格納
	var count = 0; // ループカウント

	$(".targetItemCheck").hide(); // 各部品は最初見えないようにする
	$("#canselSelectBtn").hide();
	$("#deleteProductsBtn").hide();
	$("#allSelectArea").hide();

	$("#addProductBtn").on('click',function(){ // 商品追加ボタンクリック時処理
		window.location.href="addProductPage";
	});

	$("#selectProductBtn").on('click',function(){ // 商品消去ボタンクリック時、各部品を表示する
		$(this).hide();
		$("#canselSelectBtn").show();
		$("#deleteProductsBtn").show();
		$(".targetItemCheck").show();
		$("#allSelectArea").show();
		$(".productEditBtn").hide();
		$("th:eq(0)").text("消去したい商品");
	});

	$("#canselSelectBtn").on('click',function(){ // キャンセル実行時、キャンセル確認画面を表示
		var result = window.confirm("キャンセルした場合選択された商品は解除されてしまいますがよろしいですか？");
		console.log(result);

		if(result){ // はいを選択時にチェックをすべて解除して表示を切り替え
			$("input[name='deleteTargetItem']").prop('checked',false);
			$(".targetItemCheck").hide();
			$("#canselSelectBtn").hide();
			$("#deleteProductsBtn").hide();
			$("#allSelectArea").hide();

			$(".productEditBtn").show();
			$("#selectProductBtn").show();

			$("th:eq(0)").text("編集");
		}
	});

	$("#allSelect").on('click',function(){ // 全商品選択と解除
		$("input[name='deleteTargetItem']").prop('checked', this.checked);
	});

	$("#deleteProductsBtn").on('click',function(){ // 消去確認画面表示
		count = 0; // 初期化

		$("input[name=deleteTargetItem]:checked").each(function() {
			count++;

			if(count == 1){
				itemIdList = $(this).val();
			}else{
				itemIdList += ("," + $(this).val());
			}

	        $("#itemList").append("<p>" + $(this).val() + ":" + $(this).attr('id') + "</p>");
	      });

		if(count == 0){ //一つも商品がない処理
			$("#deleteBtn").attr("disabled",true);
			$("#itemList").append("<p>商品が一つも選択されていません</p>");
		}else{
			$("#deleteBtn").attr("disabled",false);
		}

		$("#selectItemList").fadeIn(1000);

		$("body").find(".actionBtn,input,select,#previousPageBtn,#nextPageBtn").attr("disabled",true); // 基本的にすべての部品を無効化
		$(".itemLink").css('pointer-events', 'none').attr('tabindex', -1); // リンク無効化,Tabでの選択もできないように
	});

	$("#canselBtn").on('click',function(){ // 確認画面を閉じる
		$("#selectItemList").fadeOut(1000);
		$("#itemList").empty();

		$("body").find(".actionBtn,input,select,#previousPageBtn,#nextPageBtn").attr("disabled",false); // 基本的にすべての部品を無効化
		$(".itemLink").css('pointer-events', 'auto').attr('tabindex', 0); // リンク無効化,Tabでの選択もできないように
	});

	$("#deleteBtn").on('click',function(){ // 消去ボタンクリック時の処理
		console.log(itemIdList);
		window.location.href="deleteProducts?itemIdList=" + itemIdList;
	})

});


// ユーザーの品一覧と同一処理
$(function(){
	//var searchWord = null;
	//var sortNo  = null;


	var searchs = [];
	var checkBoxs = [];
	var checkBoxSplit = null;

	var hostname = $(location).attr('host');
	var protocol = $(location).attr('protocol');
	console.log("プロトコルは→"+protocol+"ホストネームは→"+hostname);
	var pageNo = 1;

	console.log(sessionStorage.getItem('sessionPageNo'));


	$("body").find(".info2").each(function(index,element){
		console.log($(element).val());
		sessionStorage.setItem('sessionMaxPage',$(element).val());
	});




	//前のページに戻るボタンロック処理
	// チェックが入っていたら有効化
	if (sessionStorage.getItem('sessionPageNo')==1){
	    // ボタンを無効化
	    $('#previousPageBtn').prop('disabled', true);
	}else if(sessionStorage.getItem('sessionPageNo')==null){
		$('#previousPageBtn').prop('disabled', true);
	}else {
	    // ボタンを有効化
	    $('#previousPageBtn').prop('disabled', false);
	}





	//後のページに行くボタンロック処理
	if (sessionStorage.getItem('sessionPageNo')==sessionStorage.getItem('sessionMaxPage')){
	    // ボタンを無効化
	    $('#nextPageBtn').prop('disabled', true);
	}else {
	    // ボタンを有効化
	    $('#nextPageBtn').prop('disabled', false);
	}





	$("#nextPageBtn").on('click',function(){
		if(sessionStorage.getItem('sessionPageNo')==null){
			console.log("sessionPageNoがnullでした");
			pageNo = 1; //current
		}else{
			pageNo = sessionStorage.getItem('sessionPageNo');//current
		}

		pageNo=Number(pageNo)+1;//after
		console.log(pageNo);
		console.log(sessionStorage.getItem('sessionPageNo'));

		sessionStorage.setItem('sessionPageNo',pageNo);


		//ボタンを押されたら検索用語、ソート番号、カラーを取得
		//取得してきたものをリンクにパラメーターとして挿入
		$("body").find(".info1").each(function(index,element){
			searchs.push($(element).val());
			console.log($(element).val());
		});


		$('input[name=color]:checked').each(function(i){
			checkBoxs.push($(this).val());
		});

		console.log(searchs);
		console.log(checkBoxs);
		console.log(checkBoxs[1]);
		console.log(checkBoxSplit);
		checkBoxSplit = checkBoxs.join('&color=');
		console.log(checkBoxSplit);
		if(checkBoxSplit==null){
			checkBoxSplit = 0;
		}


		//checkBoxsの中を繰り返し処理で書き出しでカンマうち処理
		window.location.href=protocol+"//"+hostname+"/Egroup_PROJECT/searchByAdmin?productName="+searchs[0]+"&categoryId="+searchs[1]+"&sort="+searchs[2]+"&color="+checkBoxSplit+"&pageNo="+sessionStorage.getItem('sessionPageNo');
	});

	$("#previousPageBtn").on('click',function(){
		if(sessionStorage.getItem('sessionPageNo'==null)){
			console.log("sessionPageNoがnullでした");
			pageNo = 1; //current
		}else{
			pageNo = sessionStorage.getItem('sessionPageNo');//current
		}

		pageNo=Number(pageNo)-1;//after
		console.log(pageNo);
		console.log(sessionStorage.getItem('sessionPageNo'));

		sessionStorage.setItem('sessionPageNo',pageNo);


		//ボタンを押されたら検索用語、ソート番号、カラーを取得
		//取得してきたものをリンクにパラメーターとして挿入
		$("body").find(".info1").each(function(index,element){
			searchs.push($(element).val());
			console.log($(element).val());
		});


		$('input[name=color]:checked').each(function(i){
			checkBoxs.push($(this).val());
		});

		console.log(searchs);
		console.log(checkBoxs);
		console.log(checkBoxs[1]);
		console.log(checkBoxSplit);
		checkBoxSplit = checkBoxs.join('&color=');
		console.log(checkBoxSplit);
		if(checkBoxSplit==null){
			checkBoxSplit = 0;
		}


		//checkBoxsの中を繰り返し処理で書き出しでカンマうち処理
		window.location.href=protocol+"//"+hostname+"/Egroup_PROJECT/searchByAdmin?productName="+searchs[0]+"&categoryId="+searchs[1]+"&sort="+searchs[2]+"&color="+checkBoxSplit+"&pageNo="+sessionStorage.getItem('sessionPageNo');
	});
});