//CRIAÇÃO DE CORRIDA

class Kit {
    constructor(imagem, nome, preco, tipoChip, descricao) {
        this.id = new Date().getTime();
        if (imagem.length > 0)
            this.imagem = imagem;
        this.nome = nome;
        this.preco = preco;
        this.tipoChip = tipoChip;
        this.descricao = descricao;
        this.imagemServidor;
        Kit.inputs = ['imagemKit', 'nomeKit', 'precoKit', 'tipoChipKit', 'descricaoKit'];
    }

    static adiciona() {
        var nome = document.getElementById('nomeKit').value;
        var preco = parseFloat(document.getElementById('precoKit').value);
        var tipoChip = document.getElementById('tipoChipKit').value;
        var descricao = document.getElementById('descricaoKit').value;

        var imagem = document.getElementById("imagemKit");

        var kit = new Kit(imagem, nome, preco, tipoChip, descricao);

        var imagemInput = document.createElement("INPUT");
        imagemInput.setAttribute("type", "file");
        imagemInput.setAttribute("name", "txtImagemKit");
        imagemInput.setAttribute("id", "txtImagemKit" + kit.id);
        imagemInput.files = imagem.files;
        kit.imagem = imagemInput;
        arrayKits[kit.id] = kit;

        var kits = document.getElementById("area-kits");
        var kitTr = '';

        if (kit.nome.length > 0 && kit.preco > 0 && kit.tipoChip.length > 0 && kit.descricao.length > 0) {

            if (!document.getElementById("div-vazia-kits") === null) {
                document.getElementById("div-vazia-kits").style.display = 'none';
            }

            kitTr += '<tr id="kit' + kit.id + '">';
            kitTr += '<div id="divImagemKit' + kit.id + '"></div>';
            kitTr += '<input type="hidden" name="txtNomeKit" id="txtNomeKit' + kit.id + '" value="' + kit.nome + '" />';
            kitTr += '<input type="hidden" name="txtPrecoKit" id="txtPrecoKit' + kit.id + '" value="' + kit.preco + '" />';
            kitTr += '<input type="hidden" name="txtTipoChipKit" id="txtTipoChipKit' + kit.id + '" value="' + kit.tipoChip + '" />';
            kitTr += '<input type="hidden" name="txtDescricaoKit" id="txtDescricaoKit' + kit.id + '" value="' + kit.descricao + '" />';

            kitTr += '   <td id="nomeKit' + kit.id + '">' + kit.nome + '</td>';
            kitTr += '   <td id="precoKit' + kit.id + '">' + kit.preco + '</td>';
            kitTr += '   <td id="tipoChipKit' + kit.id + '">' + kit.tipoChip + '</td>';
//            kitTr += '   <td id="descricaoKit' + kit.id + '">' + kit.descricao + '</td>';
            kitTr += '   <td style="width: 20px;"><a class="btn btn-success btn-xs" onclick="Kit.prepararEditar(' + kit.id + ')" ><i class="fa fa-pencil"></i></a></td>';
            kitTr += '   <td style="width: 20px;"><a class="btn btn-danger btn-xs" onclick="Kit.removeItem(\'kit' + kit.id + '\')" ><i class="fa fa-trash"></i></a></td>';
            kitTr += '</tr>';

            kits.insertAdjacentHTML('afterbegin', kitTr);

            limpaInputs(Kit.inputs);

            var divImagemKit = document.getElementById("divImagemKit" + kit.id);
            divImagemKit.style.display = "none";
            divImagemKit.innerHTML = "";
            divImagemKit.appendChild(imagemInput);
        }
    }

    static prepararEditar(id) {
        if (typeof arrayKits[id] === "undefined") {
            var kit = new Kit("",
                    document.getElementById('txtNomeKit' + id).value,
                    parseFloat(document.getElementById('txtPrecoKit' + id).value),
                    document.getElementById('txtTipoChipKit' + id).value,
                    document.getElementById('txtDescricaoKit' + id).value);

            kit.imagemServidor = document.getElementById('txtImagemKitServidor' + id).value;
            kit.id = id;
            arrayKits[kit.id] = kit;

            kit = arrayKits[id];
        } else {
            var kit = arrayKits[id];
            if (typeof kit.imagem !== "undefined") {
//                arrayKits[id].imagemServidor = "";
//                arrayKits[id].imagem = document.getElementById("fileUpload");
                kit = arrayKits[id];
            }
        }

        document.getElementById("preview-kit").style.display = "block";

        document.getElementById("nomeKit").value = kit.nome;
        document.getElementById("precoKit").value = kit.preco;
        document.getElementById("tipoChipKit").value = kit.tipoChip;
        document.getElementById("descricaoKit").value = kit.descricao;

        if (typeof kit.imagem === "undefined") {
            var div = document.getElementById("image-holder");
            div.innerHTML = '<img src="' + kit.imagemServidor + '"/>';
        }

        var editaKit = '<a class="btn btn-warning btn-block" id="adicionaKit" onclick="Kit.edita(' + id + ')">Editar</a>';
        document.getElementById("divAdicionaKit").innerHTML = editaKit;
    }

    static prepararEditarBD(id) {
        document.frmManterKit.action = "ManterKitController?acao=confirmarEditar";

        var kit = new Kit("",
                document.getElementById('txtNomeKit' + id).value,
                parseFloat(document.getElementById('txtPrecoKit' + id).value),
                document.getElementById('txtTipoChipKit' + id).value,
                document.getElementById('txtDescricaoKit' + id).value);

        kit.imagemServidor = document.getElementById('txtImagemKitServidor' + id).value;
        kit.id = id;
        arrayKits[kit.id] = kit;

        kit = arrayKits[id];

        document.getElementById("preview-kit").style.display = "block";

        document.getElementById("idKit").value = kit.id;
        document.getElementById("nomeKit").value = kit.nome;
        document.getElementById("precoKit").value = kit.preco;
        document.getElementById("tipoChipKit").value = kit.tipoChip;
        document.getElementById("descricaoKit").value = kit.descricao;

        var div = document.getElementById("image-holder");
        div.innerHTML = '<img src="' + kit.imagemServidor + '"/>';

        var editaKit = '<button type="submit" class="btn btn-warning btn-block">Editar</button>';
        document.getElementById("divAdicionaKit").innerHTML = editaKit;
    }

    static edita(id) {
        if (document.getElementById("fileUpload").value !== "") {
            arrayKits[id].imagem = document.getElementById('imagemKit');
            var imagemInput = document.createElement("INPUT");
            imagemInput.setAttribute("type", "file");
            imagemInput.setAttribute("name", "txtImagemKit");
            imagemInput.setAttribute("id", "txtImagemKit" + arrayKits[id].id);
            imagemInput.files = arrayKits[id].imagem.files;
            arrayKits[id].imagem = imagemInput;
            arrayKits[id].imagemServidor = null;
        } else if (typeof arrayKits[id].imagemServidor !== "undefined") {
            var div = document.getElementById("image-holder");
            div.innerHTML = '<img src="' + arrayKits[id].imagemServidor + '"/>';
        }

        arrayKits[id].nome = document.getElementById('nomeKit').value;
        arrayKits[id].preco = parseFloat(document.getElementById('precoKit').value);
        arrayKits[id].tipoChip = document.getElementById('tipoChipKit').value;
        arrayKits[id].descricao = document.getElementById('descricaoKit').value;

        var kit = arrayKits[id];

        var divImagemKit = document.getElementById("divImagemKit" + kit.id);
        divImagemKit.style.display = "none";
        divImagemKit.innerHTML = "";

        if (typeof arrayKits[id].imagem !== "undefined") {
            divImagemKit.appendChild(kit.imagem);
        }

        document.getElementById("txtNomeKit" + id).value = kit.nome;
        document.getElementById("nomeKit" + id).innerHTML = kit.nome;
        document.getElementById("txtPrecoKit" + id).value = kit.preco;
        document.getElementById("precoKit" + id).innerHTML = kit.preco;
        document.getElementById("txtTipoChipKit" + id).value = kit.tipoChip;
        document.getElementById("tipoChipKit" + id).innerHTML = kit.tipoChip;
        document.getElementById("txtDescricaoKit" + id).value = kit.descricao;
//        document.getElementById("descricaoKit" + id).innerHTML = kit.descricao;

        limpaInputs(Kit.inputs);

        var adicionaKit = '<a class="btn btn-info btn-block" id="adicionaKit" onclick="Kit.adiciona()">Adicionar</a>';
        document.getElementById("divAdicionaKit").innerHTML = adicionaKit;
    }

    static removeItem(id) {
        document.getElementById(id).remove();
        limpaInputs(Kit.inputs);
        var adicionaKit = '<a class="btn btn-info btn-block" id="adicionaKit" onclick="Kit.adiciona()">Adicionar</a>';
        document.getElementById("divAdicionaKit").innerHTML = adicionaKit;
    }

    static removeItemBD(id) {
        document.frmManterKit.action = "ManterKitController?acao=confirmarExcluir";
        var kit = new Kit("",
                document.getElementById('txtNomeKit' + id).value,
                parseFloat(document.getElementById('txtPrecoKit' + id).value),
                document.getElementById('txtTipoChipKit' + id).value,
                document.getElementById('txtDescricaoKit' + id).value);

        kit.imagemServidor = document.getElementById('txtImagemKitServidor' + id).value;
        kit.id = id;
        arrayKits[kit.id] = kit;

        kit = arrayKits[id];

        document.getElementById("preview-kit").style.display = "block";

        document.getElementById("idKit").value = kit.id;
        document.getElementById("nomeKit").value = kit.nome;
        document.getElementById("precoKit").value = kit.preco;
        document.getElementById("tipoChipKit").value = kit.tipoChip;
        document.getElementById("descricaoKit").value = kit.descricao;
        document.getElementById("nomeKit").disabled = true;
        document.getElementById("precoKit").disabled = true;
        document.getElementById("tipoChipKit").disabled = true;
        document.getElementById("descricaoKit").disabled = true;
        document.getElementById("fileUpload").disabled = true;

        var div = document.getElementById("image-holder");
        div.innerHTML = '<img src="' + kit.imagemServidor + '"/>';

        var editaKit = '<button type="submit" class="btn btn-danger btn-block">Excluir</button>';
        document.getElementById("divAdicionaKit").innerHTML = editaKit;
    }
}

var kitInputs = ['imagemKit', 'nomeKit', 'precoKit', 'tipoChipKit', 'descricaoKit'];
var arrayKits = [];


// -----------------------------------------------------------------------------


class Percurso {
    constructor(quilometragem, descricao) {
        this.id = new Date().getTime();
        this.quilometragem = quilometragem;
        this.descricao = descricao;
        Percurso.inputs = ['quilometragemPercurso', 'descricaoPercurso'];
    }

    static removeItem(id) {
        document.getElementById(id).remove();
        limpaInputs(Percurso.inputs);
        var criaPercurso = '<a class="btn btn-info btn-block" id="criaPercurso" onclick="criaPercurso()">Adicionar</a>';
        document.getElementById("divCriaPercurso").innerHTML = criaPercurso;
    }
    
    static removeItemBD(id) {
        document.frmManterPercurso.action = "ManterPercursoController?acao=confirmarExcluir";
        var percurso = new Percurso(parseFloat(document.getElementById('txtQuilometragemPercurso'+id).value), document.getElementById('txtDescricaoPercurso'+id).value);

        percurso.id = id;
        arrayPercursos[percurso.id] = percurso;

        percurso = arrayPercursos[id];

        document.getElementById("idPercurso").value = percurso.id;
        document.getElementById("quilometragemPercurso").value = parseFloat(percurso.quilometragem);
        document.getElementById("descricaoPercurso").value = percurso.descricao;

        document.getElementById("quilometragemPercurso").disabled = true;
        document.getElementById("descricaoPercurso").disabled = true;
        
        var criaPercurso = '<button type="submit" class="btn btn-danger btn-block">Excluir</button>';
        document.getElementById("divCriaPercurso").innerHTML = criaPercurso;
    }
}

var percursoInputs = ['quilometragemPercurso', 'descricaoPercurso'];
var arrayPercursos = [];

function criaPercurso() {
    var percurso = new Percurso(parseFloat(document.getElementById('quilometragemPercurso').value), document.getElementById('descricaoPercurso').value);
    arrayPercursos[percurso.id] = percurso;

    var percursos = document.getElementById("area-percursos");
    var percursoTr = '';

    if (percurso.quilometragem > 0 && percurso.descricao.length > 0) {
        document.getElementById("div-vazia-percursos").style.display = 'none';

        percursoTr += '<tr id="percurso' + percurso.id + '">';
        percursoTr += '<input type="hidden" class="form-control" name="txtQuilometragemPercurso" id="txtQuilometragemPercurso' + percurso.id + '" value="' + percurso.quilometragem + '" />';
        percursoTr += '<input type="hidden" class="form-control" name="txtDescricaoPercurso" id="txtDescricaoPercurso' + percurso.id + '" value="' + percurso.descricao + '" />';
        percursoTr += '   <td id="quilometragemPercurso' + percurso.id + '">' + percurso.quilometragem + '</td>';
        percursoTr += '   <td id="descricaoPercurso' + percurso.id + '">' + percurso.descricao + '</td>';
        percursoTr += '   <td style="width: 20px;"><a class="btn btn-success btn-xs" onclick="prepararEditarPercurso(' + percurso.id + ')" ><i class="fa fa-pencil"></i></a></td>';
        percursoTr += '   <td style="width: 20px;"><a class="btn btn-danger btn-xs" onclick="Percurso.removeItem(\'percurso' + percurso.id + '\')" ><i class="fa fa-trash"></i></a></td>';
        percursoTr += '</tr>';

        percursos.insertAdjacentHTML('afterbegin', percursoTr);

        limpaInputs(Percurso.inputs);
    }
}

function prepararEditarPercurso(percursoId) {
    if (typeof arrayPercursos[percursoId] === "undefined") {
        var percurso = new Percurso(parseFloat(document.getElementById('txtQuilometragemPercurso' + percursoId).value), document.getElementById('txtDescricaoPercurso' + percursoId).value);
        percurso.id = percursoId;
        arrayPercursos[percurso.id] = percurso;
    }

    var percurso = arrayPercursos[percursoId];
    document.getElementById("quilometragemPercurso").value = percurso.quilometragem;
    document.getElementById("descricaoPercurso").value = percurso.descricao;

    var editaPercurso = '<a class="btn btn-warning btn-block" id="criaPercurso" onclick="editaPercurso(' + percursoId + ')">Editar</a>';
    document.getElementById("divCriaPercurso").innerHTML = editaPercurso;
}

function prepararEditarPercursoBD(percursoId) {
    document.frmManterPercurso.action = "ManterPercursoController?acao=confirmarEditar";

    var percurso = new Percurso(parseFloat(document.getElementById('txtQuilometragemPercurso' + percursoId).value), document.getElementById('txtDescricaoPercurso' + percursoId).value);
    percurso.id = percursoId;
    arrayPercursos[percurso.id] = percurso;

    percurso = arrayPercursos[percursoId];

    document.getElementById("idPercurso").value = percurso.id;
    document.getElementById("quilometragemPercurso").value = percurso.quilometragem;
    document.getElementById("descricaoPercurso").value = percurso.descricao;

    var editaPercurso = '<button type="submit" class="btn btn-warning btn-block">Editar</button>';
    document.getElementById("divCriaPercurso").innerHTML = editaPercurso;
}

function editaPercurso(percursoId) {
    arrayPercursos[percursoId].quilometragem = document.getElementById("quilometragemPercurso").value;
    arrayPercursos[percursoId].descricao = document.getElementById("descricaoPercurso").value;
    var percurso = arrayPercursos[percursoId];
    document.getElementById("txtQuilometragemPercurso" + percursoId).value = percurso.quilometragem;
    document.getElementById("quilometragemPercurso" + percursoId).innerHTML = percurso.quilometragem;
    document.getElementById("txtDescricaoPercurso" + percursoId).value = percurso.descricao;
    document.getElementById("descricaoPercurso" + percursoId).innerHTML = percurso.descricao;

    limpaInputs(Percurso.inputs);

    var criaPercurso = '<a class="btn btn-info btn-block" id="criaPercurso" onclick="criaPercurso()">Adicionar</a>';
    document.getElementById("divCriaPercurso").innerHTML = criaPercurso;
}

// ---------------------------------------------------------------

class Lote {
    constructor(tipo, preco, dataInicio, dataTermino) {
        this.id = new Date().getTime();
        this.tipo = tipo;
        this.preco = preco;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        Lote.inputs = ['tipoLote', 'precoLote', 'dataInicioLote', 'dataTerminoLote'];
    }

    static adiciona() {
        var tipo = document.getElementById('tipoLote').value;
        var preco = parseFloat(document.getElementById('precoLote').value);
        var dataInicio = document.getElementById('dataInicioLote').value;
        var dataTermino = document.getElementById('dataTerminoLote').value;

        var lote = new Lote(tipo, preco, dataInicio, dataTermino);
        arrayLotes[lote.id] = lote;

        var lotes = document.getElementById("area-lotes");
        var loteTr = '';

        if (lote.tipo.length > 0 && lote.preco > 0 && lote.dataInicio.length > 0 && lote.dataTermino.length > 0) {

            document.getElementById("div-vazia-lotes").style.display = 'none';
            loteTr += '<tr id="lote' + lote.id + '">';
            loteTr += '<input type="hidden" name="txtTipoLote" id="txtTipoLote' + lote.id + '" value="' + lote.tipo + '" />';
            loteTr += '<input type="hidden" name="txtPrecoLote" id="txtPrecoLote' + lote.id + '" value="' + lote.preco + '" />';
            loteTr += '<input type="hidden" name="txtDataInicioLote" id="txtDataInicioLote' + lote.id + '" value="' + lote.dataInicio + '" />';
            loteTr += '<input type="hidden" name="txtDataTerminoLote" id="txtDataTerminoLote' + lote.id + '" value="' + lote.dataTermino + '" />';

            loteTr += '   <td id="tipoLote' + lote.id + '">' + lote.tipo + '</td>';
            loteTr += '   <td id="precoLote' + lote.id + '">R$ ' + lote.preco + '</td>';
//            loteTr += '   <td id="dataInicioLote' + lote.id + '">' + lote.dataInicio + '</td>';
//            loteTr += '   <td id="dataTerminoLote' + lote.id + '">' + lote.dataTermino + '</td>';

            loteTr += '   <td style="width: 20px;"><a class="btn btn-success btn-xs" onclick="Lote.prepararEditar(' + lote.id + ')" ><i class="fa fa-pencil"></i></a></td>';
            loteTr += '   <td style="width: 20px;"><a class="btn btn-danger btn-xs" onclick="Lote.removeItem(\'lote' + lote.id + '\')" ><i class="fa fa-trash"></i></a></td>';
            loteTr += '</tr>';

            lotes.insertAdjacentHTML('afterbegin', loteTr);

            limpaInputs(Lote.inputs);
        }
    }

    static prepararEditar(id) {
        if (typeof arrayLotes[id] === "undefined") {
            var lote = new Lote(
                    document.getElementById('txtTipoLote' + id).value,
                    parseFloat(document.getElementById('txtPrecoLote' + id).value),
                    document.getElementById('txtDataInicioLote' + id).value,
                    document.getElementById('txtDataTerminoLote' + id).value);

            lote.id = id;
            arrayLotes[lote.id] = lote;
        }

        var lote = arrayLotes[id];
        document.getElementById("tipoLote").value = lote.tipo;
        document.getElementById("precoLote").value = lote.preco;
        document.getElementById("dataInicioLote").value = lote.dataInicio;
        document.getElementById("dataTerminoLote").value = lote.dataTermino;

        var editaLote = '<a class="btn btn-warning btn-block" id="adicionaLote" onclick="Lote.edita(' + id + ')">Editar</a>';
        document.getElementById("divAdicionaLote").innerHTML = editaLote;
    }

    static prepararEditarBD(id) {
        document.frmManterLote.action = "ManterKitController?acao=confirmarEditar";

        var lote = new Lote(
                document.getElementById('txtTipoLote' + id).value,
                parseFloat(document.getElementById('txtPrecoLote' + id).value),
                document.getElementById('txtDataInicioLote' + id).value,
                document.getElementById('txtDataTerminoLote' + id).value);

        lote.id = id;
        arrayLotes[lote.id] = lote;

        lote = arrayLotes[id];

        document.getElementById("idLote").value = lote.id;
        document.getElementById("tipoLote").value = lote.tipo;
        document.getElementById("precoLote").value = lote.preco;
        document.getElementById("dataInicioLote").value = lote.dataInicio;
        document.getElementById("dataTerminoLote").value = lote.dataTermino;

        var editaKit = '<button type="submit" class="btn btn-warning btn-block">Editar</button>';
        document.getElementById("divAdicionaKit").innerHTML = editaKit;
    }

    static edita(id) {
        arrayLotes[id].tipo = document.getElementById('tipoLote').value;
        arrayLotes[id].preco = parseFloat(document.getElementById('precoLote').value);
        arrayLotes[id].dataInicio = document.getElementById('dataInicioLote').value;
        arrayLotes[id].dataTermino = document.getElementById('dataTerminoLote').value;

        var lote = arrayLotes[id];
        document.getElementById("txtTipoLote" + id).value = lote.tipo;
        document.getElementById("tipoLote" + id).innerHTML = lote.tipo;
        document.getElementById("txtPrecoLote" + id).value = lote.preco;
        document.getElementById("precoLote" + id).innerHTML = "R$ " + lote.preco;
        document.getElementById("txtDataInicioLote" + id).value = lote.dataInicio;
//        document.getElementById("dataInicioLote" + id).innerHTML = lote.dataInicio;
        document.getElementById("txtDataTerminoLote" + id).value = lote.dataTermino;
//        document.getElementById("dataTerminoLote" + id).innerHTML = lote.dataTermino;

        limpaInputs(Lote.inputs);

        var adicionaLote = '<a class="btn btn-info btn-block" id="adicionaLote" onclick="Lote.adiciona()">Adicionar</a>';
        document.getElementById("divAdicionaLote").innerHTML = adicionaLote;
    }

    static removeItem(id) {
        document.getElementById(id).remove();
        limpaInputs(Lote.inputs);
        var adicionaLote = '<a class="btn btn-info btn-block" id="adicionaLote" onclick="Lote.adiciona()">Adicionar</a>';
        document.getElementById("divAdicionaLote").innerHTML = adicionaLote;
    }
}

var loteInputs = ['tipoLote', 'precoLote', 'dataInicioLote', 'dataTerminoLote'];
var arrayLotes = [];

// ----------------------------------------

function limpaInputs(arrayInputs) {
    for (var i = 0; i < arrayInputs.length; i++) {
        if (arrayInputs[i] === "imagemKit") {
            document.getElementById("preview-kit").style.display = "none";
        } else {
            document.getElementById(arrayInputs[i]).value = "";
        }
    }
}

function escolherImagem(input) {
    document.getElementById("image-holder").innerHTML = "";
    if (typeof (FileReader) !== "undefined") {

        var image_holder = $("#image-holder");
        image_holder.empty();

        var reader = new FileReader();
        reader.onload = function (e) {
            $("<img />", {
                "src": e.target.result,
                "class": "thumb-image"
            }).appendTo(image_holder);
        };
        image_holder.show();
        reader.readAsDataURL($(input)[0].files[0]);
    } else {
        alert("Este navegador nao suporta FileReader.");
    }

    document.getElementById("preview-kit").style.display = "inline-block";
    var x = document.createElement("INPUT");
    x.setAttribute("type", "file");
    x.setAttribute("id", "imagemKit");
    x.setAttribute("name", "imagemKitBD");
    x.files = input.files;
    var divImagemKit = document.getElementById("divImagemKit");
    divImagemKit.innerHTML = "";
    divImagemKit.appendChild(x);
    x.style.display = "none";
}

function escreveNome(input) {
    var nome = input.value;
    if (input.value.length > 0) {
        document.getElementById("mostraNomeCorrida").style.display = "inline";
        document.getElementById("mostraNomeCorrida").innerHTML = nome;
    } else {
        document.getElementById("mostraNomeCorrida").style.display = "none";
    }
}

//function carregaItens() {
//    console.log(percursoInputs);
//    for (var i = 0; i < 3; i++) {
//        var quilometragem = document.getElementsByName('txtQuilometragemPercurso')[i].value;
//        var descricao = document.getElementsByName('txtDescricaoPercurso')[i].value;
//        var percurso = new Percurso(quilometragem, descricao);
//        arrayPercursos[percurso.id] = percurso;
//    }
//}
//
//carregaItens();