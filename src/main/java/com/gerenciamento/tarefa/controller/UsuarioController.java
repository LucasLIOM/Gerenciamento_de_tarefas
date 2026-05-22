package com.gerenciamento.tarefa.controller;

import com.gerenciamento.tarefa.model.Usuario;
import com.gerenciamento.tarefa.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    // Atualiza um usuário pelo ID
    @PutMapping("/{id}")
    public Usuario atualizar(@PathVariable Long id, @RequestBody Usuario usuario){
    return service.atualizar(id, usuario);
    }

    /*  Criar um usuario com body json utiliza-se: 
     "nome" : "user",
     "email": "user@exemplo"
     "senha": "user123" */
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return service.salvar(usuario);
    }

    // Lista todos
    @GetMapping
    public List<Usuario> listar() {
        return service.listarTodos();
    }

    // Busca por ID
    @GetMapping("/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .orElse(null);
    }

    // DeleteMapping = deleta o usuário por ID
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}