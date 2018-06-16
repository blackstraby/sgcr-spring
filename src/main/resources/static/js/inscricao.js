var precoTotal = 0;
var precoLote = 0;
var precoKit = 0;
function atualizaPrecoLote(preco, nome) {
    precoLote = preco;
    precoTotal = parseFloat(precoLote.toFixed(2)) + parseFloat(precoKit.toFixed(2));
    var mostraPrecoLote = document.getElementById("precoLote");
    var mostraNomeLote = document.getElementById("nomeLote");
    var mostraPrecoTotal = document.getElementById("precoTotal");
    var divPrecoTotal = document.getElementById("divPrecoTotal");

    divPrecoTotal.style.opacity = "1";
    divPrecoTotal.style.border = "none";
    divPrecoTotal.style.padding = "0px";

    mostraPrecoLote.innerHTML = "R$ " + precoLote.toFixed(2);
    mostraNomeLote.innerHTML = nome;
    mostraPrecoTotal.innerHTML = "R$ " + precoTotal.toFixed(2);

    document.getElementById("infoLote").style.display = "table-row";
    document.getElementById("tabelaPreco").style.display = "block";
    document.getElementById("infoVazio").style.display = "none";
}

function atualizaPrecoKit(preco, nome) {
    precoKit = preco;
    precoTotal = parseFloat(precoLote.toFixed(2)) + parseFloat(precoKit.toFixed(2));
    var mostraPrecoKit = document.getElementById("precoKit");
    var mostraNomeKit = document.getElementById("nomeKit");
    var mostraPrecoTotal = document.getElementById("precoTotal");
    var divPrecoTotal = document.getElementById("divPrecoTotal");

    divPrecoTotal.style.opacity = "1";
    divPrecoTotal.style.border = "none";
    divPrecoTotal.style.padding = "0px";

    mostraPrecoKit.innerHTML = "R$ " + precoKit.toFixed(2);
    mostraNomeKit.innerHTML = nome;
    mostraPrecoTotal.innerHTML = "R$ " + precoTotal.toFixed(2);

    document.getElementById("infoKit").style.display = "table-row";
    document.getElementById("tabelaPreco").style.display = "block";
    document.getElementById("infoVazio").style.display = "none";
}

