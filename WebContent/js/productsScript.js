function bt(i){

    if(i.style.backgroundColor ==""){
    	window.location.href = "addFavorite?itemId=" + i.value;


    	i.style.backgroundColor="#FFD700";
    	window.history.back(-1);
    }

    if(i.style.backgroundColor =="#FFD700"){
        i.style.backgroundColor="";

    	window.location.href = "deleteFavorite?itemId="+ i.value;
    }

}
