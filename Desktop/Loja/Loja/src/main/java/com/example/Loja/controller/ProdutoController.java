package com.example.Loja.controller;


import com.example.Loja.model.Produto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private ArrayList<Produto> produtos = new ArrayList<>();
    public ProdutoController(){
        produtos.add(new Produto(1L, "Notebook", 4500.0));
        produtos.add(new Produto(2L, "PS2", 500.0));
        produtos.add(new Produto(3L, "Redmi 15", 1200.0));
    }

    @GetMapping
    public ArrayList<Produto> listarProdutos(){
        return produtos;
    }

    @GetMapping("/{id}")
    public Produto listarObjeto(@PathVariable Long id){ //O path captura o valor da Long id e joga pra url ID
        for(Produto p : produtos){ //Basicamente procura o produto na lista pelo id que a pessoa digitou na barra de navegação e se achar retorna o produto, se não, retorna null
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    @PostMapping
    public Produto adicionarObjeto(@RequestBody Produto produto){//Resquest body converte o json recebido em arquivo/objeto java
        produtos.add(produto);
        return produto;
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produtoAtualizado){
        for(Produto p : produtos){
            if(p.getId().equals(id)){
                p.setNome(produtoAtualizado.getNome());
                p.setPreco(produtoAtualizado.getPreco());
                return p;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String deletarProduto(@PathVariable Long id){
        produtos.removeIf(produto -> produto.getId().equals(id));
            return "Deletado!";
        }

    }
    
