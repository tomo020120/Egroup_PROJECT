var emptyPattern = /^[ 　\r\n\t]*$/; // スペースなども空白と判定するための正規表現
var pricePattern = /^[0-9]+$/;
var categoryIdPattern = /^[1-3]{1}$/;
var colorIdPattern = /^[1-9]|1[0-2]$/
var artistIdPattern = /^[0-32]{1}$/

$(function(){
	var name = $("input[name=productName]");
	var price = $("input[name=price]");
	var categoryId = $("input[name=categoryId]");
	var colorId = $("input[name=colorId]");
	var artistId = $("input[name=artistId]");

	var nameError = $("#nameErrorText");
	var priceError = $("#priceErrorText");
	var categoryIdError = $("#categoryIdErrorText");
	var colorIdError = $("#colorIdErrorText");
	var artistIdError = $("#artistIdErrorText");

	name.bind("blur", function() {
		var input_name = $(this).val();
		check_name(input_name,nameError);
	});

	price.bind("blur", function() {
		var input_price = $(this).val();
		check_price(input_price,priceError);
	});

	categoryId.bind("blur", function() {
		var input_categoryId = $(this).val();
		check_categoryId(input_categoryId,categoryIdError);
	});

	colorId.bind("blur", function() {
		var input_colorId = $(this).val();
		check_colorId(input_colorId,colorIdError);
	});

	artistId.bind("blur", function() {
		var input_artistId = $(this).val();
		check_artistId(input_artistId,artistIdError);
	});


	$('#productPict').change(function(){
		if (this.files.length > 0) {
		    // 選択されたファイル情報を取得
		    var file = this.files[0];

		    // readerのresultプロパティに、データURLとしてエンコードされたファイルデータを格納
		    var reader = new FileReader();
		    reader.readAsDataURL(file);

		    reader.onload = function() {
		      $('#thumbnail').attr('src', reader.result );
		    }
		}
	});

	$("#submitBtn").on('click',function(){

		var nameValue = name.val();
		var priceValue = price.val();
		var categoryIdValue = categoryId.val();
		var colorIdValue = colorId.val();
		var artistIdValue = artistId.val();

		var result1 = check_name(nameValue,nameError);
		var result2 = check_price(priceValue,priceError);
		var result3 = check_categoryId(categoryIdValue,categoryIdError);
		var result4 = check_colorId(colorIdValue,colorIdError);
		var result5 = check_artistId(artistIdValue,artistIdError);

		if(result1 && result2 && result3 && result4 && result5){
			if(artistIdValue == null || artistIdValue == ''){
				$("input[name=artistId]").val(0);
				$("#addProductForm").submit();
			}
		}

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

function check_price(str,eObj){
	var _result = true;
	var _textbox = $.trim(str);


	if(_textbox.match(emptyPattern)){
		eObj.html("値段が入力されてません");
		_result = false;
	}else if(!_textbox.match(pricePattern)){
		eObj.html("数値ではありません");
		_result = false;
	}else{
		eObj.html("");
	}
	return _result;
}

function check_categoryId(str,eObj){
	var _result = true;
	var _textbox = $.trim(str);


	if(_textbox.match(emptyPattern)){
		eObj.html("カテゴリー番号が入力されていません");
		_result = false;
	}else if(!_textbox.match(categoryIdPattern)){
		eObj.html("存在しない番号です");
		_result = false;
	}else{
		eObj.html("");
	}
	return _result;
}

function check_colorId(str,eObj){
	var _result = true;
	var _textbox = $.trim(str);


	if(_textbox.match(emptyPattern)){
		eObj.html("カラー番号が入力されていません");
		_result = false;
	}else if(!_textbox.match(colorIdPattern)){
		eObj.html("存在しない番号です");
		_result = false;
	}else{
		eObj.html("");
	}
	return _result;
}

function check_artistId(str,eObj){
	var _result = true;
	var _textbox = $.trim(str);


	if(emptyPattern){
		eObj.html("");
		_result = true;
	}else if(!_textbox.match(artistIdPattern)){
		eObj.html("存在しない番号です");
		_result = false;
	}else{
		eObj.html("");
	}
	return _result;
}
