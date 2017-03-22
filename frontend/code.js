var wellStart = document.getElementById("wellStart")
var wellEnd = document.getElementById("wellEnd")

var Item = {
    addToDocument:function(well){
        well.appendChild(this.item);
    }
}
var CodePanel = {
    createCode:function(text, id){
        this.item = document.createElement("PRE");
        this.item.setAttribute("id", id);
        var codeText = document.createTextNode(text);
        this.item.appendChild(codeText)
    }
}
CodePanel.__proto__ = Item;
CodePanel.createCode("this will be a well with start code", "holeStart");
CodePanel.addToDocument(wellStart);

CodePanel.createCode("this will be a well with end code", "holeEnd");
CodePanel.addToDocument(wellEnd);

window.changeCode = function(e){
    if(e.value=="hole1"){
        //code1.textContent = "Hole 1 eventually..."
		populatePre('holeStart', '/start/h1');
		populatePre('holeEnd', '/end/h1');
    }
    else if(e.value=="hole2"){
		populatePre('holeStart', '/start/h2');
		populatePre('holeEnd', '/end/h2');
    }
    else if(e.value == "hole3"){
		populatePre('holeStart', '/start/h3');
		populatePre('holeEnd', '/end/h3');
    }
    else if(e.value == "hole4"){
		populatePre('holeStart', '/start/h4');
		populatePre('holeEnd', '/end/h4');
    }
}

function populatePre(id, url) {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        document.getElementById(id).textContent = this.responseText;
    };
    xhr.open('GET', url);
    xhr.send();
}

