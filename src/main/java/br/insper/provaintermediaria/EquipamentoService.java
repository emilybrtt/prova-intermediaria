package br.insper.provaintermediaria;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EquipamentoService {

    private HashMap<String, Equipamento> equipamentos = new HashMap<>();


    // ==================== POST =======================
    public Equipamento cadastrarEquipamento(Equipamento equipamento) {
        boolean codigoPatrimonioExiste = equipamentos.values().stream()
                .filter(Predicate.not(Equipamento::isDeleted))
                .anyMatch(u -> u.getCodigoPatrimonio() == equipamento.getCodigoPatrimonio());
        if (codigoPatrimonioExiste) {
            throw new RuntimeException("Equipamento já existe");
        } // Verificando se já existe


        if (equipamento.getNome() == null || equipamento.getCodigoPatrimonio() == 0L)
            {
                throw new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Nome e Código do patrimônio são obrigatórios" // mudei esse erro por conta do problema na validação do codigoPatrimonio que mencionei durante a prova.
                );}

        equipamentos.put(equipamento.getId(), equipamento);
        return equipamento;
    }


    // ==================== GET ALL =======================
    public Collection<Equipamento> listarEquipamentos() {
        return equipamentos.values().stream()
                .filter(Predicate.not(Equipamento::isDeleted))
                .collect(Collectors.toList());
    }


    // ==================== GET ONE =======================
    public Equipamento buscarEquipamento(String id) {
        Equipamento equipamento = equipamentos.get(id);
        if (equipamento == null) {
            throw new RuntimeException("Equipamento não encontrado");
        }
        if (equipamento.isDeleted()) {
            throw new RuntimeException("Equipamento inativo");
        }
        return equipamento;
    }


    // ==================== PUT =======================
    public Equipamento atualizarEquipamento(String id, Equipamento equipamentoAtualizado) {
        Equipamento equipamentoAntigo = buscarEquipamento(id);
        equipamentoAntigo.setNome(equipamentoAtualizado.getNome());
        equipamentoAntigo.setCategoria(equipamentoAtualizado.getCategoria());
        equipamentoAntigo.setCodigoPatrimonio(equipamentoAtualizado.getCodigoPatrimonio());
        equipamentoAntigo.setValorDiaria(equipamentoAtualizado.getValorDiaria());
        return equipamentoAntigo;
    }


    // ==================== DELETE =======================
    public void deletarEquipamento(String id) {
        Equipamento equipamento = buscarEquipamento(id);
        equipamento.setDeleted(true);
    }
}
