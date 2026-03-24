package br.insper.provaintermediaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class EquipamentoController {

    @Autowired
    private EquipamentoService equipamentoService;

    // ==================== POST =======================
    @PostMapping("/equipamentos")
    @ResponseStatus(HttpStatus.CREATED)
    public Equipamento cadastrarEquipamento(@RequestBody Equipamento equipamento){
        return equipamentoService.cadastrarEquipamento(equipamento);
    }


    // ==================== GET ALL =======================
    @GetMapping("/equipamentos")
    public Collection<Equipamento> getEquipamentos(){
        return equipamentoService.listarEquipamentos();
    }


    // ==================== GET ONE =======================
    @GetMapping("/equipamentos/{id}")
    public Equipamento getEquipamento(@PathVariable String id){
        return equipamentoService.buscarEquipamento(id);
    }


    // ==================== PUT =======================
    @PutMapping("/equipamentos/{id}")
    public Equipamento updateEquipamento(@PathVariable String id, @RequestBody Equipamento equipamentoAtualizado){
        return equipamentoService.atualizarEquipamento(id, equipamentoAtualizado);
    }


    // ==================== DELETE =======================
    @DeleteMapping("/equipamentos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEquipamento(@PathVariable String id){
        equipamentoService.deletarEquipamento(id);
    }

}
