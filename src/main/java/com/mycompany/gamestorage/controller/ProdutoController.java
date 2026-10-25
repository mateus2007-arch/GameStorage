/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gamestorage.controller;


import com.mycompany.gamestorage.model.Produto;
import com.mycompany.gamestorage.service.ProdutoService;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author mateu
 */



@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private ProdutoService service;

    public ProdutoController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Produto> listar(@RequestParam(required = false) String busca) {
        return service.pesquisar(busca);
    }

    @GetMapping("/total-estoque")
    public Map<String, Double> totalEstoque() {
        return Map.of("total", service.calcularValorTotalEstoque());
    }

    @GetMapping("/{id}")
    public Produto buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto produto) {
        Produto salvo = service.cadastrar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public Produto editar(@PathVariable Long id, @RequestBody Produto produto) {
        return service.editar(id, produto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}