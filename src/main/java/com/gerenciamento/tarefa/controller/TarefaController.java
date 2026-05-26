package com.gerenciamento.tarefa.controller;

import com.gerenciamento.tarefa.model.Tarefa;
import com.gerenciamento.tarefa.service.TarefaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    // Criar tarefa
    @PostMapping
    public Tarefa salvar(@RequestBody Tarefa tarefa) {
        return service.salvar(tarefa);
    }

    // Listar todas
    @GetMapping
    public List<Tarefa> listarTodas() {
        return service.listarTodas();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public Optional<Tarefa> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    // Atualizar
    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id,
            @RequestBody Tarefa tarefa) {

        return service.atualizar(id, tarefa);
    }

    // Deletar
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}