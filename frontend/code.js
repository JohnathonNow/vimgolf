var Item = {
    addToDocument:function(){
        document.body.appendChild(this.item);
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
CodePanel.createCode("this will be a code panel", "holeCode");
CodePanel.addToDocument();

window.changeCode = function(e){
    if(e.value=="hole1"){
        //code1.textContent = "Hole 1 eventually..."
		populatePre('/start/h1');
    }
    else if(e.value=="hole2"){
		populatePre('/start/h2');
    }
    else if(e.value == "hole3"){
		populatePre('/start/h3');
    }
    else if(e.value == "hole4"){
		populatePre('/start/h4');
    }
}

function populatePre(url) {
    var xhr = new XMLHttpRequest();
    xhr.onload = function () {
        document.getElementById('holeCode').textContent = this.responseText;
    };
    xhr.open('GET', url);
    xhr.send();
}
