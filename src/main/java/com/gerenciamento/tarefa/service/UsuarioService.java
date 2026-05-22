package com.gerenciamento.tarefa.service;

import com.gerenciamento.tarefa.model.Usuario;
import com.gerenciamento.tarefa.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    // Cria um usuário
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    // Atualiza = Pega todos os dados e atualiza da forma que preferir
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {

        Usuario usuario = repository.findById(id).orElseThrow();

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());

        return repository.save(usuario);
    }

    // Listar todos os usuários inseridos
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    // Buscar por ID
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // Buscar por email
    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    // Deletar por ID
    public void deletar(Long id) {
        repository.deleteById(id);
    }
}