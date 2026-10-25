
if (localStorage.getItem("tema") == "escuro") {
      document.documentElement.classList.add("escuro");
}


function alternarTema() {
    document.documentElement.classList.toggle("escuro");
    
    if (document.documentElement.classList.contains("escuro")) {
        localStorage.setItem("tema", "escuro")
    } else {
          localStorage.setItem("tema", "claro");
    }
}


document.addEventListener("DOMContentLoaded", function () {
    var b = document.getElementById("btn-tema");

    if (b) {
        b.addEventListener("click", alternarTema);
    }
});
