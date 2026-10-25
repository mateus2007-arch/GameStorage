package com.mycompany.gamestorage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.mycompany.gamestorage.exception.ProdutoNaoEncontradoException;
import com.mycompany.gamestorage.exception.ValidacaoException;
import com.mycompany.gamestorage.model.Produto;
import com.mycompany.gamestorage.repository.ProdutoRepository;
import com.mycompany.gamestorage.service.ProdutoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
/**
 *
 * @author mateu
 */


@SpringBootTest
@ActiveProfiles("test")
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoRepository repositorio;

    @BeforeEach
    public void preparar() {
        repositorio.deleteAll();
        service.cadastrar(new Produto("RTX 4060", "GPU", "Nvidia", 2500, 10));
        service.cadastrar(new Produto("Ryzen 5", "CPU", "AMD", 900, 8));
    }

    @Test
    public void valorTotalDoEstoque() {
        assertEquals(32200, service.calcularValorTotalEstoque(), 0.001);
    }

    @Test
    public void precoNegativoDaErro() {
        Produto p = new Produto("Mouse", "Mouse", "Logitech", -5, 1);
        assertThrows(ValidacaoException.class, () -> service.cadastrar(p));
        assertEquals(2, service.listar().size());
    }

    @Test
    public void excluirProduto() {
        Long id = service.listar().get(0).getId();
        service.excluir(id);
        assertEquals(1, service.listar().size());
        assertThrows(ProdutoNaoEncontradoException.class, () -> service.buscar(id));
    }

    @Test
    public void idNaoRepeteDepoisDeExcluir() {
        Produto a = service.cadastrar(new Produto("Teclado", "Teclado", "Redragon", 200, 15));
        Long idA = a.getId();
        service.excluir(idA);
        Produto b = service.cadastrar(new Produto("Fone", "Fone", "HyperX", 300, 5));
        assertNotEquals(idA, b.getId());
    }

    @Test
    public void pesquisaPorMarca() {
        assertEquals(1, service.pesquisar("nvidia").size());
        assertEquals(0, service.pesquisar("(").size());
    }
}