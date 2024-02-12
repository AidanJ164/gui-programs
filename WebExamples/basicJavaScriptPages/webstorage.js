
function add1() {
	var x = document.getElementById("xValLong").value;
	var y = document.getElementById("yValLong").value;
	var z = parseInt(x) + parseInt(y);
	document.getElementById("answer1").innerHTML = z;
	
	localStorage.setItem("x", x);
	localStorage.setItem("y", y);
}

function add2() {
	var x = document.getElementById("xValSession").value;
	var y = document.getElementById("yValSession").value;
	var z = parseInt(x) + parseInt(y);
	document.getElementById("answer2").innerHTML = z;
	
	sessionStorage.setItem("x", x);
	sessionStorage.setItem("y", y);
}

function clearStorage(){
	sessionStorage.clear();
	localStorage.clear();
	window.location.reload();
}



//attach all functions to html elements
window.onload = function() {
	if (typeof(Storage) !== "undefined") {
		
		//local storage, which save until cache is cleared
		if(localStorage.hasOwnProperty("x") && localStorage.hasOwnProperty("y")){
	 	   document.getElementById("xValLong").value =localStorage.x;
		   document.getElementById("yValLong").value =localStorage.y;
		}else{
			document.getElementById("xValLong").value = 0;
			document.getElementById("yValLong").value = 0
		}
		
		document.getElementById("xValLong").oninput =add1;
		document.getElementById("yValLong").oninput =add1;
		add1();
		
		
		//session storage, which save until tab is closed
		if(sessionStorage.hasOwnProperty("x") && sessionStorage.hasOwnProperty("y")){
	 	   document.getElementById("xValSession").value =sessionStorage.x;
		   document.getElementById("yValSession").value =sessionStorage.y;
		}else{
			document.getElementById("xValSession").value = 0;
			document.getElementById("yValSession").value = 0
		}
		
		document.getElementById("xValSession").oninput =add2;
		document.getElementById("yValSession").oninput =add2;
		add2();
		
	} else {
	   //  No Web Storage support..
	   var error = document.getElementById("error").value = "No web storage supprt!!!!";
    }
	
	document.getElementById("clear").onclick = clearStorage;


}



