var form = document.getElementById("form-produto");

if (form) {
    var lista = document.getElementById("lista-tipos");
    if (lista) {
        for (var i = 0; i < categorias.length; i++) {
            lista.innerHTML += '<option value="' + categorias[i] + '"></option>';
        }
    }

    var editando = form.getAttribute("data-modo") == "editar";
    var idEditado = null;

    if (editando) {
        idEditado = new URLSearchParams(window.location.search).get("id");
        chamarApi("/api/produtos/" + idEditado, "GET", null, function (p) {
            document.getElementById("nome").value = p.nome;
            document.getElementById("categoria").value = p.categoria;
            document.getElementById("marca").value = p.marca;
            document.getElementById("preco").value = p.preco;
            document.getElementById("estoque").value = p.estoque;
        }, function (msg) {
            mostrarMensagem("falha", msg + " Volte e selecione um produto na tabela.");
        });
    }

    form.addEventListener("submit", function (e) {
        e.preventDefault();
        var dados = validarProduto();
        if (dados == null) {
            return;
        }

        if (editando) {
            chamarApi("/api/produtos/" + idEditado, "PUT", dados, function () {
                mostrarMensagem("ok", "Produto atualizado.");
            }, function (msg) {
                mostrarMensagem("falha", msg);
            });
        } else {
            chamarApi("/api/produtos", "POST", dados, function () {
                mostrarMensagem("ok", "Produto cadastrado.");
                form.reset();
            }, function (msg) {
                mostrarMensagem("falha", msg);
            });
        }
    });
}