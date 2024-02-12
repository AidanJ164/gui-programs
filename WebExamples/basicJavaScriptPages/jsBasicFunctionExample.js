//Example for loop)--------------------------------------
for(var hour=0; hour<24; hour++) {
  var greeting;
  if(hour <= 12) {
    greeting = "Good Morning";
  } else if(hour > 18) {
    greeting = "Good Night";
  } else {
    greeting = "Good Afternoon";
  }

  console.log("Hour " + hour + ": " + greeting);
}
//---------------------------------------------------------

//Example for loop (slide 18 solution))--------------------
for(var i=1; i<=10; i++) 
{
  var stars = "";
  for(var j=1; j<=i; j++) 
  {
    stars += "*";  
  }
  console.log(stars + " " + i + " stars");
}
//---------------------------------------------------------


//Example function loop (slide 21 solution)----------------
function squared_formula(x, a, b, c) 
{  
   return a * x * x + b * x + c;
}

console.log(squared_formula(3, 1, 2, 3));
console.log(squared_formula(-1.7, 0.2, 0.4, 1.9));
console.log(squared_formula(1, 1, 0, 0));
//---------------------------------------------------------

//Example function loop (slide 22 solution)----------------
function quadratic(a, b, c) 
{  
  var n = b * b - 4 * a * c;  
  if(n < 0) 
  {    
    return null;  
  }  
  var answer = [(-b + Math.sqrt(n)) / (2 * a),                  
  (-b - Math.sqrt(n)) / (2 * a)]; 
  return answer;
}
console.log(quadratic(1, -5, 6));
console.log(quadratic(1, 1, 1));
console.log(quadratic(1, 2, 1));
//---------------------------------------------------------


//greeting function-----------------------------------
function getGreeting(hour) {
  var greeting;
  if(hour <= 12) {
    greeting = "Good Morning";
  } else if(hour > 18) {
    greeting = "Good Night";
  } else {
    greeting = "Good Afternoon";
  }

  return greeting;
}
//------------------------------------------------------------


//quad out function (slide 34 solution)-----------------------------------
function quadout(e, a, b, c) {

  html = "";
  var q = quadratic(a, b, c);
  if(q === null) {
    html += "nonexistent";
  } else {
    html += q[0] + " and " + q[1];
  }

  var result = document.getElementById(e);
  result.innerHTML = html;
}
//------------------------------------------------------------


//flips text upside-down function (slide 37 solution)-----------------------------------
var toggle = false;
function flipper() {
      
      var state = document.getElementById("state2");
      if(toggle)
      {
      	state.innerHTML = "Right Side Up";
        state.style.transform = "rotate(0deg)";
        state.style.textAlign = "left";
      }
      else
      {
      	state.innerHTML = "Upside down";
        state.style.transform = "rotate(180deg)";
        state.style.textAlign = "right";
      }
      toggle = !toggle;
 }
 //------------------------------------------------------------
 
//attach all functions to html elements
window.onload = function() {
	
	//quadratic solution
	quadout('result', 2, 5, 2);

	//input example
	document.getElementById("name").value = "Name";
		
	//button click...nested function
	document.getElementById("button").onclick = function(event) {
      document.getElementById("demo").innerHTML = "Clicky!";
   }

   //greeting...run immediately
    var d = new Date();
    var n = d.getHours();
	document.getElementById("greet").innerHTML =  getGreeting(n);
	
	//flipper example...run on click, but external function
    document.getElementById("flip").onclick = flipper;
	
	//CSS annoy example 
	document.getElementById("annoy").onclick = function() {
      var state = document.getElementById("state");
      state.innerHTML = "I'm Annoyed";
      state.style.color = "#FF0000";
      state.style.fontSize = "5em";
    }	

	//function scoping example
    document.getElementById("para").onclick = hello;
    document.getElementById("btn").onclick=  hello;

} 

//function scoping!!!!!!!!!!!!
var hello = function() {
  document.getElementById("scopeDemo").innerHTML = "Hello: " + this;
}
