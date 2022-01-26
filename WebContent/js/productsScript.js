function bt(i){

    if(i.style.backgroundColor ==""){
    	//window.history.back(-1);
    	i.style.backgroundColor="#FFD700";
    	window.location.href = "addFavorite?itemId=" + i.value;
    }else{
        i.style.backgroundColor="";

    	window.location.href = "deleteFavorite?itemId="+ i.value;

    }

}
