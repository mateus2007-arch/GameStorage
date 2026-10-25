function mostrarErro(campo, texto) {
    var el = document.getElementById("erro-" + campo);

    if (el) {
          el.textContent = texto;
    }
}


function limparErros(lista) {
    for (var i = 0; i < lista.length; i++) {
        mostrarErro(lista[i], "");
    }
}


function mostrarMensagem(tipo, texto) {
    var div = document.getElementById("mensagem");

    if (div) {
        div.className = "mensagem " + tipo;
        div.textContent = texto;
    }
}


function validarProduto() {
    var campos = ["nome", "categoria", "marca", "preco", "estoque"];
    limparErros(campos);

    var nome = document.getElementById("nome").value.trim()
    var cat = document.getElementById("categoria").value.trim();
    var m = document.getElementById("marca").value.trim();
    var txtP = document.getElementById("preco").value.trim().replace(",", ".");
      var txtE = document.getElementById("estoque").value.trim();

    var p = Number(txtP);
    var e = Number(txtE);
    var ok = true;


    if (nome == "") {
        mostrarErro("nome", "Informe o nome do produto.");
        ok = false;
    }

    if (cat == "") {
          mostrarErro("categoria", "Informe o tipo do produto.");
        ok = false;
    }

    if (m == "") {
        mostrarErro("marca", "Informe a marca.");
        ok = false;
    }


    if (txtP == "" || isNaN(p) || p <= 0) {
        mostrarErro("preco", "O preço deve ser um número maior que zero.");
        ok = false;
    }

    if (txtE == "" || isNaN(e) || e < 0 || e % 1 !== 0) {
          mostrarErro("estoque", "O estoque deve ser um número inteiro, 0 ou mais.");
        ok = false;
    }


    if (!ok) {
        mostrarMensagem("falha", "Corrija os campos em vermelho.");
        return null;
    }


    return {
        nome: nome,
        categoria: cat,
          marca: m,
        preco: p,
        estoque: e
    };
}


var form = document.getElementById("form-login");

if (form) {
    form.addEventListener("submit", function (evento) {
        evento.preventDefault();
        limparErros(["nome", "senha"]);

        var u = document.getElementById("nome").value.trim();
        var s = document.getElementById("senha").value;
        var loginOk = true;


        if (u.length < 3) {
              mostrarErro("nome", "Digite o nome do usuário (mínimo 3 letras).");
            loginOk = false;
        }

        if (s.length < 4) {
            mostrarErro("senha", "A senha deve ter pelo menos 4 caracteres.");
            loginOk = false;
        }


        if (loginOk) {
            mostrarMensagem("ok", "Logado.");
        } else {
              mostrarMensagem("falha", "Erro ao entrar.");
        }
    });
}

