var wellStart = document.getElementById("wellStart")
var wellEnd = document.getElementById("wellEnd")

var Item = {
    addToDocument:function(well){
        well.appendChild(this.item);
    }
}
var CodePanel = {
    createCode:function(text, id){
        this.item = document.createElement("pre");
        this.item.setAttribute("id", id);
        this.item.setAttribute("class", "prettyprint linenums");
        this.item.setAttribute("onload", "PR.prettyPrint();");
        var codeText = document.createTextNode(text);
        this.item.appendChild(codeText)
    }
}
CodePanel.__proto__ = Item;
CodePanel.createCode("This well is the original text file that you must make\nchanges to using Vim commands.", "holeStart");
CodePanel.addToDocument(wellStart);

CodePanel.createCode("After running your Vim commands, the resulting text file\nmust match the text in this well.", "holeEnd");
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
        document.getElementById(id).setAttribute("class", "prettyprint linenums");
        PR.prettyPrint();
    };
    xhr.open('GET', url);
    xhr.send();
}

