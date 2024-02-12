var lineCnt = 2;

function addLine() {
  //get prior values
  var priorVals = [""];
  for( var i = 1; i < lineCnt; i++)
  {
      var val =  document.getElementById("id"+i).value;
      if( val == undefined )
      	 priorVals.push("");
      else
         priorVals.push(val);
  }
  
  //add new line
  var newLine = '<label>id' + lineCnt + ': </label>'
  newLine += '<input type="text"  id="id' + lineCnt +'" name="id' + lineCnt + '"><br><br>';
  var theFormContent = document.getElementById("lines");
  theFormContent.innerHTML += newLine;
  
  //copy prior values back in 
  for( var i = 1; i < lineCnt; i++)
  {
      document.getElementById("id"+i).value = priorVals[i];
  }
  
  lineCnt = lineCnt+1;
}

function  getResults(){
  var result = "";
  for( var i = 1; i < lineCnt; i++)
  {
      result += document.getElementById("id"+i).name + "-->" + document.getElementById("id"+i).value;
      result += '<br>';
  }
  document.getElementById("results").innerHTML = result;
}


//attach all functions to html elements
window.onload = function() {
	//button click...nested function
	document.getElementById("addLine").onclick = addLine;
	document.getElementById("getResults").onclick = getResults;
} 
