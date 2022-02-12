$(function(){
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
		var artistIdValue = $("input[name=artistId]").val();

		if(artistIdValue == null || artistIdValue == ''){
			$("input[name=artistId]").val(0);
		}

		$("#addProductForm").submit();
	});
});