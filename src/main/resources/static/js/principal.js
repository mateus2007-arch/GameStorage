var corpoTabela = document.getElementById("corpo-tabela");
var idSelecionado = null;

function mostrarTabela() {
    var busca = document.getElementById("busca").value.toLowerCase();
    var filtroHardware = document.getElementById("filtro-hardware").value.toLowerCase();
    var filtroPeriferico = document.getElementById("filtro-periferico").value.toLowerCase();
    var html = "";

    for (var i = 0; i < produtos.length; i++) {
        var p = produtos[i];
        var achouTexto = busca == "" ||
            p.nome.toLowerCase().indexOf(busca) >= 0 ||
            p.marca.toLowerCase().indexOf(busca) >= 0 ||
            p.categoria.toLowerCase().indexOf(busca) >= 0;
        var achouHardware = filtroHardware == "" || p.categoria.toLowerCase() == filtroHardware;
        var achouPeriferico = filtroPeriferico == "" || p.categoria.toLowerCase() == filtroPeriferico;

        if (achouTexto && achouHardware && achouPeriferico) {
            var classe = p.id == idSelecionado ? ' class="selecionada"' : "";
            html += '<tr data-id="' + p.id + '"' + classe + '><td>' + p.id + '</td><td>' + p.nome +
                '</td><td>' + p.categoria + '</td><td>' + p.marca + '</td><td>' + formatarPreco(p.preco) +
                '</td><td>' + p.estoque + '</td></tr>';
        }
    }
    if (html == "") {
        html = '<tr><td colspan="6">Nenhum produto encontrado.</td></tr>';
    }
    corpoTabela.innerHTML = html;
}

function carregarTabela() {
    chamarApi("/api/produtos", "GET", null, function (dados) {
        produtos = dados;
        mostrarTabela();
    }, function (msg) {
        mostrarMensagem("falha", msg);
    });
}

function preencherSelect(id, lista) {
    var seletor = document.getElementById(id);
    for (var i = 0; i < lista.length; i++) {
        seletor.innerHTML += '<option value="' + lista[i] + '">' + lista[i] + '</option>';
    }
}

if (corpoTabela) {
    preencherSelect("filtro-hardware", hardware);
    preencherSelect("filtro-periferico", perifericos);

    document.getElementById("busca").addEventListener("input", mostrarTabela);

    document.getElementById("filtro-hardware").addEventListener("change", function (e) {
        if (e.target.value != "") {
            document.getElementById("filtro-periferico").value = "";
        }
        mostrarTabela();
    });

    document.getElementById("filtro-periferico").addEventListener("change", function (e) {
        if (e.target.value != "") {
            document.getElementById("filtro-hardware").value = "";
        }
        mostrarTabela();
    });

    corpoTabela.addEventListener("click", function (e) {
        var linha = e.target.closest("tr");
        if (linha && linha.getAttribute("data-id")) {
            idSelecionado = Number(linha.getAttribute("data-id"));
            mostrarTabela();
        }
    });

    document.getElementById("btn-editar").addEventListener("click", function () {
        if (idSelecionado == null) {
            mostrarMensagem("falha", "Selecione um produto na tabela.");
            return;
        }
        window.location.href = "editar-produto.html?id=" + idSelecionado;
    });

    document.getElementById("btn-excluir").addEventListener("click", function () {
        if (idSelecionado == null) {
            mostrarMensagem("falha", "Selecione um produto na tabela.");
            return;
        }
        if (!confirm("Deseja excluir este produto?")) {
            return;
        }
        chamarApi("/api/produtos/" + idSelecionado, "DELETE", null, function () {
            idSelecionado = null;
            carregarTabela();
            mostrarMensagem("ok", "Produto excluído.");
        }, function (msg) {
            mostrarMensagem("falha", msg);
        });
    });

    window.addEventListener("pageshow", function (e) {
        if (e.persisted) {
            carregarTabela();
        }
    });

    carregarTabela();
}