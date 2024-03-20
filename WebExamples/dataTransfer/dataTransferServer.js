
var express = require('express');
var app = express();
var dataTrasfer = require('./dataTransfer')
app.get("/|index.html", dataTrasfer.runPage)
app.listen(8080);