const express = require("express");
var app = express();
var num = 0;

app.get("/", function(req, res) {
    res.writeHead(200, {'Content-Type': 'text/html'});
    res.write("abc<span style='font-weight: bold'>AB</span>");
    res.end();
})

app.get("/catch_res", function(req, res) {
    res.write(num.toString());
    num++;
    res.end();
})

app.listen(8080)