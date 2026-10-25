/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamestorage.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.mycompany.gamestorage.exception.ValidacaoException;
import org.junit.jupiter.api.Test;


/**
 *
 * @author mateu
 */



public class ProdutoTest {

    @Test
    public void valorEmEstoque() {
        Produto p = new Produto("RTX 4060", "GPU", "Nvidia", 2500, 10);
        assertEquals(25000, p.getValorEmEstoque(), 0.001);
    }

    @Test
    public void desconto10() {
        Produto p = new Produto("RTX 4060", "GPU", "Nvidia", 2500, 10);
        assertEquals(2250, p.preComDesconto(10), 0.001);
    }

    @Test
    public void descontoZerado() {
        Produto p = new Produto("RTX 4060", "GPU", "Nvidia", 2500, 10);
        assertEquals(2500, p.preComDesconto(0), 0.001);
    }

    @Test
    public void descontoMaior100() {
        Produto p = new Produto("RTX 4060", "GPU", "Nvidia", 2500, 10);
        assertThrows(ValidacaoException.class, () -> p.preComDesconto(150));
    }
}