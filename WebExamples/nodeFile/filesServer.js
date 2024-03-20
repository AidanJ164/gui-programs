
var express = require('express');
var app = express();
var fileTransfer = require('./nodeFileHandler')
fileTransfer.init(app);
app.get("/|index.html", fileTransfer.runPage)
app.listen(8080);
