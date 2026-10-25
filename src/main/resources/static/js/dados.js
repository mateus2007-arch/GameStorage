var categorias = ["Placa Mãe", "Memória Ram", "CPU", "GPU", "Fonte", "Gabinete", "Teclado", "Monitor", "Mouse", "Fone"];
var hardware = ["Placa Mãe", "Memória Ram", "CPU", "GPU", "Fonte", "Gabinete"];
var perifericos = ["Teclado", "Monitor", "Mouse", "Fone"];

var produtos = [];

function formatarPreco(valor) {
    return "R$ " + valor.toFixed(2).replace(".", ",");
}

function chamarApi(url, metodo, corpo, quandoOk, quandoErro) {
    var opcoes = { method: metodo, headers: { "Content-Type": "application/json" } };
    if (corpo) {
        opcoes.body = JSON.stringify(corpo);
    }
    fetch(url, opcoes).then(function (resposta) {
        return resposta.text().then(function (texto) {
            var dados = null;
            if (texto) {
                dados = JSON.parse(texto);
            }
            if (resposta.ok) {
                quandoOk(dados);
            } else if (dados && dados.erro) {
                quandoErro(dados.erro);
            } else {
                quandoErro("Erro no servidor.");
            }
        });
    }, function () {
        quandoErro("Não consegui falar com o servidor.");
    });
}