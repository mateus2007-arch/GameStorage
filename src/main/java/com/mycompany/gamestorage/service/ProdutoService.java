/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamestorage.service;

import com.mycompany.gamestorage.exception.ProdutoNaoEncontradoException;
import com.mycompany.gamestorage.model.Produto;
import com.mycompany.gamestorage.repository.ProdutoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
/**
 *
 * @author mateu
 */

@Service
public class ProdutoService {

    private ProdutoRepository repositorio;
    private ProdutoValidator validador;

    public ProdutoService(ProdutoRepository repositorio, ProdutoValidator validador) {
        this.repositorio = repositorio;
        this.validador = validador;
    }

    public List<Produto> listar() {
        return repositorio.findAll();
    }

    public Produto buscar(Long id) {
        Produto p = repositorio.findById(id).orElse(null);
        if (p == null) {
            throw new ProdutoNaoEncontradoException(id);
        }
        return p;
    }

    public Produto cadastrar(Produto produto) {
        validador.validar(produto);
        produto.setId(null);
        return repositorio.save(produto);
    }

    public Produto editar(Long id, Produto novosDados) {
        validador.validar(novosDados);
        Produto produto = buscar(id);
        produto.setNome(novosDados.getNome().trim());
        produto.setCategoria(novosDados.getCategoria().trim());
        produto.setMarca(novosDados.getMarca().trim());
        produto.setPreco(novosDados.getPreco());
        produto.setEstoque(novosDados.getEstoque());
        return repositorio.save(produto);
    }

    public void excluir(Long id) {
        if (!repositorio.existsById(id)) {
            throw new ProdutoNaoEncontradoException(id);
        }
        repositorio.deleteById(id);
    }

    public List<Produto> filtrarPorCategoria(String categoria) {
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : repositorio.findAll()) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public List<Produto> pesquisar(String texto) {
        if (texto == null || texto.trim().isEmpty()) {
            return listar();
        }
        String busca = texto.trim().toLowerCase();
        List<Produto> resultado = new ArrayList<>();
        for (Produto p : repositorio.findAll()) {
            if (p.getNome().toLowerCase().contains(busca)
                    || p.getMarca().toLowerCase().contains(busca)
                    || p.getCategoria().toLowerCase().contains(busca)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    public double calcularValorTotalEstoque() {
        double total = 0;
        for (Produto p : repositorio.findAll()) {
            total = total + p.getValorEmEstoque();
        }
        return total;
    }
}