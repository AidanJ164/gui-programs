const Mustache = require('mustache');
const fs = require("fs");
const path = require("path");

var xCnt = 0;
var yCnt = 0;
function checkCommands(data, res){
    //reset command called
    if(data.hasOwnProperty("reset"))
    {
        xCnt = 0;
        yCnt = 0;
    }

    if(data.hasOwnProperty("x"))
    {
        xCnt++;
    }
    if(data.hasOwnProperty("y"))
    {
        yCnt++;
    }

    if(data.hasOwnProperty("total"))
    {
        res.writeHead(200, {'Content-Type': 'text/plain'});
        var total = xCnt + yCnt;
        res.write(total.toString());
        res.end();
        return true;
    }

    return false;
}


function load(path, data, res){
    try{
        let content = fs.readFileSync(path);
        var output = Mustache.render(content.toString(), data);

        res.writeHead(200, {'Content-Type': 'text/html'});
        res.write(output);
		
		//Option 2, node js made the javascript
        res.write(`<script> +
            console.log("Node appended javascript!");
            var script = document.getElementById("formula").innerHTML= "${xCnt} + ${yCnt}";
            </script>`);
    }catch(err){
        //data dump
        console.log(err);
    }
    res.end();
}


function runPage(req, res){

    let url = path.join(__dirname, "index.html");

    var params = req.query;

    var isDone = checkCommands(params, res);

    if (!isDone) {
        params["xCnt"] = xCnt;
        params["yCnt"] = yCnt;
        load(url, params, res)
    }
}

module.exports = { runPage};