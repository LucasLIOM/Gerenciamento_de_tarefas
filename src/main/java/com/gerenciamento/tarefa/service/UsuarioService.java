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

    // Criar usuário
    public Usuario salvar(Usuario usuario) {

        validarUsuario(usuario); // Utiliza o método de validação para conferir se os dados estão certos

        return repository.save(usuario);
    }

    // Atualizar usuário
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {

        validarUsuario(usuarioAtualizado); // Utiliza o método de validação

        Usuario usuario = repository.findById(id).orElseThrow();

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        usuario.setSenha(usuarioAtualizado.getSenha());

        return repository.save(usuario);
    }

    // Listar todos
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    // Buscar por ID
    public Optional<Usuario> buscarPorId(Long id) {
        Optional<Usuario> idUsuario = repository.findById(id);

        if(idUsuario.isEmpty()){
            throw new RuntimeException("ID do usuário não identificado.");
        }

        return repository.findById(id);
    }

    // Buscar por email
    public Optional<Usuario> buscarPorEmail(String email) {
        return repository.findByEmail(email);
    }

    // Deletar
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    // Validações
    public void validarUsuario(Usuario usuario) {

        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new RuntimeException("Nome não pode estar vazio");
        }
        if (usuario.getNome().length() < 3) {
            throw new RuntimeException("Nome muito curto");
        }

        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new RuntimeException("Email obrigatório");
        }
        if (usuario.getEmail().length() > 90 || usuario.getEmail().length() < 15) {
            throw new RuntimeException("Email inválido");
        }
        if (!usuario.getEmail().endsWith("@gmail.com") && !usuario.getEmail().endsWith("@hotmail.com")) {
            throw new RuntimeException("Email deve ser gmail ou hotmail");
        }

        if (usuario.getSenha() == null || usuario.getSenha().isBlank()) {
            throw new RuntimeException("Senha obrigatória");
        }
        if (usuario.getSenha().length() < 8) {
            throw new RuntimeException("Senha deve ter no mínimo 8 caracteres");
        }
        if (!usuario.getSenha().matches(".*[A-Z].*")) {
            throw new RuntimeException("Senha precisa de letra maiúscula");
        }
    }
}