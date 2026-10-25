/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamestorage.service;
import com.mycompany.gamestorage.exception.ValidacaoException;
import com.mycompany.gamestorage.model.Produto;
import org.springframework.stereotype.Component;

/**
 *
 * @author mateu
 */

@Component
public class ProdutoValidator {

    public void validar(Produto p) {
        if (p == null) {
            throw new ValidacaoException("Produto não informado.");
        }
        if (p.getNome() == null || p.getNome().trim().isEmpty()) {
            throw new ValidacaoException("Informe o nome do produto.");
        }
        if (p.getCategoria() == null || p.getCategoria().trim().isEmpty()) {
            throw new ValidacaoException("Informe o tipo do produto.");
        }
        if (p.getMarca() == null || p.getMarca().trim().isEmpty()) {
            throw new ValidacaoException("Informe a marca.");
        }
        if (p.getPreco() <= 0) {
            throw new ValidacaoException("O preço deve ser maior que zero.");
        }
        if (p.getEstoque() < 0) {
            throw new ValidacaoException("O estoque não pode ser negativo.");
        }
    }
}