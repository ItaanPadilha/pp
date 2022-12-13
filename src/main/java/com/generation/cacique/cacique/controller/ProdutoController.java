package com.generation.cacique.cacique.controller;


import com.generation.cacique.cacique.model.Produto;
import com.generation.cacique.cacique.repository.CategoriaRepository;
import com.generation.cacique.cacique.repository.ProdutoRepository;
import com.generation.cacique.cacique.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/listartodos")
    public ResponseEntity<List<Produto>> getAll(){
        return ResponseEntity.ok(produtoRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity <Produto> getById(@PathVariable Long id){
        return produtoRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
    @GetMapping("/nome/{nome}")
    public ResponseEntity <List<Produto>> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }
    @GetMapping("/preco/{preco}")
    public ResponseEntity <List<Produto>> getByPrecoLess(@PathVariable BigDecimal preco){
        return ResponseEntity.ok(produtoRepository.findAllByPrecoLessThan(preco));
    }
    @GetMapping("/preco/maior/{preco}")
    public ResponseEntity <List<Produto>> getByPrecoGreater(@PathVariable BigDecimal preco){
        return ResponseEntity.ok(produtoRepository.findAllByPrecoGreaterThan(preco));
    }
    @PostMapping("/cadastrar")
    public ResponseEntity<Produto> post(@Valid @RequestBody Produto produto){
        if (categoriaRepository.existsById(produto.getCategoria().getId()))
            return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


    @PutMapping
    public ResponseEntity<Produto> put(@Valid @RequestBody Produto produto){
        if (produtoRepository.existsById(produto.getId())){
            if (categoriaRepository.existsById(produto.getCategoria().getId()))
                return ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Optional<Produto> produto=produtoRepository.findById(id);
        if (produto.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        produtoRepository.deleteById(id);
    }

}


