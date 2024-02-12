var sounds = ["res/440Hz_.5s.wav",
	"res/440Hz_.5s.wav", 
	'res/600Hz_.5s.wav', 
	"res/440Hz_.5s.wav",
	'res/600Hz_.5s.wav',
	"res/440Hz_.5s.wav",
	"res/1000Hz_3s.wav"]
	
var snd = new Audio(); 
snd.autoplay = false;
var time = 0;

function playSound()
{	
	var index = 0;
    time = 0;
	snd = new Audio(sounds[index]);
    snd.play();
	//snd.currentTime = 0.4;//shortens the current sound to 0.1 seconds by jumping ahead seconds
	
	//mutlple files can be played if started at th same time
	//snd = new Audio(sounds[index+2]);
    //snd.play();
	
	//when the sound ends, move to the next
	snd.onended = function() {
   		index++;
		if(sounds.length > index)
		{
	        snd.src=sounds[index];
		    snd.play();
		}

	};
}


// Update the count down every 1 second
var x = setInterval(function() {

  time = time + 0.5;
  document.getElementById("time").innerHTML = time
  
  //stop sound early if needed
  if(time > 3.5)
  {
	 snd.pause() 
  }
  
}, 500);

//attach all functions to html elements
window.onload = function() {
		
	//button click
	document.getElementById("button").onclick = playSound
   
} 

