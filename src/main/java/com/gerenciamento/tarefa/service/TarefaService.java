package com.gerenciamento.tarefa.service;

import com.gerenciamento.tarefa.model.Tarefa;
import com.gerenciamento.tarefa.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    // Criar tarefa
    public Tarefa salvar(Tarefa tarefa) {

        validarTarefa(tarefa); // Validação

        return repository.save(tarefa);
    }

    // Atualizar tarefa
    public Tarefa atualizar(Long id, Tarefa tarefaAtualizada) {

        validarTarefa(tarefaAtualizada); // Mesmo método de validação do UsuarioService

        Tarefa tarefa = repository.findById(id).orElseThrow();

        tarefa.setUsuario(tarefaAtualizada.getUsuario());
        tarefa.setTitulo(tarefaAtualizada.getTitulo());
        tarefa.setDescricao(tarefaAtualizada.getDescricao());
        tarefa.setConcluida(tarefaAtualizada.isConcluida());
        tarefa.getStatus();
        

        return repository.save(tarefa);
    }

    // Listar todas
    public List<Tarefa> listarTodas() {
        List<Tarefa> listTarefa = repository.findAll();

        if (listTarefa.isEmpty()) {
            throw new RuntimeException("Nenhuma tarefa foi encontrada.");
        }
        return repository.findAll();
    }

    // Buscar por ID
    public Optional<Tarefa> buscarPorId(Long id) {
        Optional<Tarefa> idTarefa = repository.findById(id);

        if (idTarefa.isEmpty() || idTarefa == null) {
            throw new RuntimeException("ID não indentificado.");
        }
        return repository.findById(id);
    }

    // Deletar
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // Validações
    public void validarTarefa(Tarefa tarefa) {

        if (tarefa.getTitulo() == null || tarefa.getTitulo().isBlank()) {
            throw new RuntimeException("Título obrigatório");
        }

        if (tarefa.getTitulo().length() < 3) {
            throw new RuntimeException("Título muito curto");
        }

        if (tarefa.getDescricao() == null || tarefa.getDescricao().isBlank()) {
            throw new RuntimeException("Descrição obrigatória");
        }

        if (tarefa.getUsuario() == null) {
            throw new RuntimeException("Usuário obrigatório");
        }
    }

    

    /*
     * public String trocarStatus(Tarefa tarefa){
     * 
     * String status = tarefa.isConcluida() ? "Concluída" : "Não concluída";
     * 
     * return status;
     * 
     * }
     */
}