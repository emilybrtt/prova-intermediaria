package br.insper.provaintermediaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class LocacaoController {

    @Autowired
    private LocacaoService locacaoService;

    // ==================== POST =======================
    @PostMapping("/locacoes")
    @ResponseStatus(HttpStatus.CREATED)
    public Locacao cadastrarLocacao(@RequestBody Locacao locacao){
        return locacaoService.cadastrarLocacao(locacao);
    }


    // ==================== GET ALL =======================
    @GetMapping("/locacoes")
    public Collection<Locacao> getLocacoes(){
        return locacaoService.listarLocacoes();
    }


    // ==================== GET ONE =======================
    @GetMapping("/locacoes/{id}")
    public Locacao getLocacao(@PathVariable String id){
        return locacaoService.buscarLocacao(id);
    }


    // ==================== PUT =======================
    @PutMapping("/locacoes/{id}")
    public Locacao updateLocacao(@PathVariable String id, @RequestBody Locacao locacaoAtualizado){
        return locacaoService.atualizarLocacao(id, locacaoAtualizado);
    }


    // ==================== DELETE =======================
    @DeleteMapping("/locacoes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLocacao(@PathVariable String id){
        locacaoService.deletarLocacao(id);
    }

}
