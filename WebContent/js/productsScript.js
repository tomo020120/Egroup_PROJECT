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
		window.location.href=hostname+"/Egroup_PROJECT/productsSearch?productName="+searchs[0]+"&categoryId="+searchs[1]+"&sort="+searchs[2]+"&color="+checkBoxSplit+"&pageNo="+sessionStorage.getItem('sessionPageNo');
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
		window.location.href=hostname+"/Egroup_PROJECT/productsSearch?productName="+searchs[0]+"&categoryId="+searchs[1]+"&sort="+searchs[2]+"&color="+checkBoxSplit+"&pageNo="+sessionStorage.getItem('sessionPageNo');
	});
});