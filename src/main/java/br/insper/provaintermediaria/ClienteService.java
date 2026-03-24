package br.insper.provaintermediaria;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class ClienteService {
    private HashMap<String, Cliente> clientes = new HashMap<>();


    // ==================== POST =======================
    public Cliente cadastrarCliente(Cliente cliente) {
        boolean emailExiste = clientes.values().stream()
                .filter(Predicate.not(Cliente::isDeleted))
                .anyMatch(u -> u.getEmail().equals(cliente.getEmail()));

        if (emailExiste) {
            throw new RuntimeException("Cliente já existe");
        } // Verificando se já existe


        if (cliente.getNome() == null || cliente.getEmail() == null || cliente.getCpf() == null) {
            throw new RuntimeException("Nome, CPF e email são obrigatórios");
        }

        clientes.put(cliente.getId(), cliente);
        return cliente;
    }


    // ==================== GET ALL =======================
    public Collection<Cliente> listarClientes() {
        return clientes.values().stream()
                .filter(Predicate.not(Cliente::isDeleted))
                .collect(Collectors.toList());
    }


    // ==================== GET ONE =======================
    public Cliente buscarCliente(String id) {
        Cliente cliente = clientes.get(id);
        if (cliente == null) {
            throw new RuntimeException("Cliente não encontrado");
        }
        if (cliente.isDeleted()) {
            throw new RuntimeException("Cliente inativo");
        }
        return cliente;
    }


    // ==================== PUT =======================
    public Cliente atualizarCliente(String id, Cliente clienteAtualizado) {
        Cliente clienteAntigo = buscarCliente(id);
        clienteAntigo.setNome(clienteAtualizado.getNome());
        clienteAntigo.setEmail(clienteAtualizado.getEmail());
        clienteAntigo.setCpf(clienteAtualizado.getCpf());
        clienteAntigo.setTelefone(clienteAtualizado.getTelefone());
        return clienteAntigo;
    }


    // ==================== DELETE =======================
    public void deletarCliente(String id) {
        Cliente cliente = buscarCliente(id);
        cliente.setDeleted(true);
    }
}
