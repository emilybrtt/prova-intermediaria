package br.insper.provaintermediaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

        // ==================== POST =======================
        @PostMapping("/clientes")
        @ResponseStatus(HttpStatus.CREATED)
        public Cliente cadastrarCliente(@RequestBody Cliente cliente){
            return clienteService.cadastrarCliente(cliente);
        }


        // ==================== GET ALL =======================
        @GetMapping("/clientes")
        public Collection<Cliente> getClientes(){
            return clienteService.listarClientes();
        }


        // ==================== GET ONE =======================
        @GetMapping("/clientes/{id}")
        public Cliente getCliente(@PathVariable String id){
            return clienteService.buscarCliente(id);
        }


        // ==================== PUT =======================
        @PutMapping("/clientes/{id}")
        public Cliente updateCliente(@PathVariable String id, @RequestBody Cliente clienteAtualizado){
            return clienteService.atualizarCliente(id, clienteAtualizado);
        }


        // ==================== DELETE =======================
        @DeleteMapping("/clientes/{id}")
        @ResponseStatus(HttpStatus.NO_CONTENT)
        public void deleteCliente(@PathVariable String id){
            clienteService.deletarCliente(id);
        }

}
