package br.com.pedro.springStarter.models.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedro.springStarter.models.entities.product.ProductDTO;
import br.com.pedro.springStarter.service.ProductService;
import jakarta.validation.Valid;

/**
 * Controller responsável por determinar as funções da api
 *
 * @author Pedro Henrique Pereira de Oliveira
 */

@RestController()
@RequestMapping("/api/produtos")
public class ProductController {


    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public @ResponseBody ResponseEntity<ProductDTO> create(@RequestBody @Valid ProductDTO requestedProduct) {
        return ResponseEntity.ok().body(productService.create(requestedProduct));
    }

    @GetMapping
    public @ResponseBody ResponseEntity<List<ProductDTO>> get() {
        return ResponseEntity.ok(productService.get());
    }

    @GetMapping(path = "/{id}")
    public @ResponseBody ResponseEntity<ProductDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(productService.get(id));
    }

    @GetMapping(path = "/page/{numberPage}")
    public @ResponseBody ResponseEntity<List<ProductDTO>> getByPage(@PathVariable int numberPage) {

        return ResponseEntity.ok(productService.getByPage(numberPage));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ProductDTO> update(@RequestBody @Valid ProductDTO data) {
        return ResponseEntity.ok(productService.update(data));
    }

    @DeleteMapping(path = "/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
