function favoriteClick(obj){
	if(obj.style.backgroundColor == ""){
		obj.style.backgroundColor = "pink";
		window.location.href = "addFavorite?itemId=" + obj.value;
	}else{
		obj.style.backgroundColor = "";
		window.location.href = "deleteFavorite?itemId=" + obj.value;
	}
}

$(function(){
	var favoriteFlag = $("body").data("favorite");

	if(favoriteFlag){
		$("#favoriteBtn").css('background-color','pink');
	}
});
