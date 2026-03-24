package br.insper.provaintermediaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class LocacaoService {
    private HashMap<String, Locacao> locacoes = new HashMap<>();

    @Autowired
    private EquipamentoService equipamentoService;




    // ==================== POST =======================
    public Locacao cadastrarLocacao(Locacao locacao) {
        boolean idLocacaoExiste = locacoes.values().stream()
                .filter(Predicate.not(Locacao::isDeleted))
                .anyMatch(u -> Objects.equals(u.getId(), locacao.getId()));
        if (idLocacaoExiste) {
            throw new RuntimeException("Locação já existe");
        } // Verificando se já existe


        if (locacao.getCliente() == null)
        {
            throw new RuntimeException("Cliente solicitante é obrigatório");
        }

        Equipamento equipamento = equipamentoService.listarEquipamentos()
                .stream()
                .filter(e -> !e.isDeleted())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhum equipamento ativo disponível"));

        locacao.atribuiEquipamento(equipamento);

        locacoes.put(locacao.getId(), locacao);
        return locacao;
    }


    // ==================== GET ALL =======================
    public Collection<Locacao> listarLocacoes() {
        return locacoes.values().stream()
                .filter(Predicate.not(Locacao::isDeleted))
                .collect(Collectors.toList());
    }


    // ==================== GET ONE =======================
    public Locacao buscarLocacao(String id) {
        Locacao locacao = locacoes.get(id);
        if (locacao == null) {
            throw new RuntimeException("Locação não encontrada");
        }
        if (locacao.isDeleted()) {
            throw new RuntimeException("Locação inativa");
        }
        return locacao;
    }


    // ==================== PUT =======================
    public Locacao atualizarLocacao(String id, Locacao locacaoAtualizado) {
        Locacao locacaoAntiga = buscarLocacao(id);

        Equipamento equipamento = equipamentoService.listarEquipamentos()
                .stream()
                .filter(e -> !e.isDeleted())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Nenhum equipamento ativo disponível"));

        locacaoAntiga.atribuiEquipamento(equipamento);
        locacaoAntiga.setDataInicio(locacaoAtualizado.getDataInicio());
        locacaoAntiga.setDataFim(locacaoAtualizado.getDataFim());
        locacaoAntiga.setCliente(locacaoAtualizado.getCliente());
        return locacaoAntiga;
    }
    // OBS: Professor, como perguntei em aula, rodei novamente o HashMap de equipamentos e atribui um novo,
    // entretanto, como o equipamento não é passado no body - já que é atribuído pelo próprio sistema,
    // o correto seria fazer um DTO da atualização e, nele, receber algo como um booleano de se a pessoa
    // quer alterar o equipamento ou não e, caso seja TRUE, aí sim selecionar um próximo equipamento
    // disponível. Por conta do tempo de prova, acabei fazendo uma nova atribuição de equipamento toda
    // vez que o put é chamado.


    // ==================== DELETE =======================
    public void deletarLocacao(String id) {
        Locacao locacao = buscarLocacao(id);
        locacao.setDeleted(true);
    }
}
