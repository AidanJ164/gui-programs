function Buttons() {
   //1) Check if breackpoints is hit

   
   //2) confirm access and basic event

   document.getElementById("b1").onclick = function(event) {
      if (document.getElementById("b1").innerHTML == "Press me") {
         document.getElementById("b1").innerHTML = "&nbsp;";
         document.getElementById("b2").innerHTML = "Press me";
      }
   }

    
    //3) failed member variable
	
    this.on = 1;
	document.getElementById("b1").onclick = function(event){
		console.log(this);
        if(this.on == 1){
        	document.getElementById("b1").innerHTML="&nbsp;";
            document.getElementById("b2").innerHTML="Press me";
        }
	}
	

   //4) correct member variable
   
	var thisClass = this;
	this.on = 1;

	document.getElementById("b1").onclick = function(event){
	    console.log(thisClass);

    	   if(thisClass.on == 1){
				document.getElementById("b1").innerHTML="&nbsp;";
				document.getElementById("b2").innerHTML="Press me";
				thisClass.on = 2;
        }
   }

   //5) CSS update
   
   var thisClass = this;
   this.on = 1;
   update_compact(1);

    document.getElementById("b1").onclick = function(event){
		if(thisClass.on == 1){
			document.getElementById("b1").innerHTML="&nbsp;";
        	document.getElementById("b2").innerHTML="Press me";
            that.on = 2;
            update_compact(thisClass.on);
		}
	}

   //6) first attempt at loop (FAILS)
   
	var thisClass = this;
    this.on = 1;
	for(var b=1;  b<=3;  b++) {
      var c = (b == 3 ? 1 : b+1);
      document.getElementById("b"+b).onclick = function(event){
		console.log("b: " + b + " c: " + c);
   	    if(thisClass.on == b) {
            update_compact(c);
		    thisClass.on = c;
         }
      }
	}
	

   
	//7) correct loop
	var thisClass = this;
	this.on = 1;
	
	for(var b=1;  b<=3;  b++) {
        configButton(b);
    }
	
	function configButton(b) {
        var c = (b == 3 ? 1 : b+1); //get next button number
     
        document.getElementById("b" + b).onclick =function(event) {
            console.log(thisClass.on);
		
		    //update only
		    if(thisClass.on == b ){
                update_compact(c);
                thisClass.on = c;
           }
	    }
    }
	

   
   //8) new block scoping
   let on = 1;
   update_compact(1);
   for (let b = 1; b <= 3; b++) {
      let c = (b == 3 ? 1 : b + 1);
      document.getElementById("b" + b).onclick = function(event) {
         console.log("b: " + b + " c: " + c);
         if (on == b) {
            update_compact(c);
            on = c;
         }
      }
   }
   

}

//compact the update
function update_compact(on) {
   for (var i = 1; i <= 3; i++) {
      document.getElementById("b" + i).innerHTML = on == i ? "Press me" : "&nbsp;";
      document.getElementById("b" + i).style.background = on == i ? "red" : "green";
   }
}


//------------------------------------------------------------


//attach all functions to html elements
window.onload = function() {

   //clicker example
   var buttons = new Buttons();
   
   //5) 
   //update_compact(1);

}