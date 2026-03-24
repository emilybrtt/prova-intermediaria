package br.insper.provaintermediaria;

import java.time.LocalDateTime;

public class Locacao {
    private String id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataFim;
    private Cliente cliente;
    private Equipamento equipamento;
    private boolean deleted;

    public Locacao(String id, LocalDateTime dataInicio, LocalDateTime dataFim, Cliente cliente, Equipamento equipamento) {
        this.id = id;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.cliente = cliente;
        this.equipamento = equipamento;
        this.deleted = false;
    }

    public String getId() {
        return id;
    }

//    public void setId(String id) {
//        this.id = id;
//    } -> Não se muda o id

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Equipamento getEquipamento() {
        return equipamento;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public void atribuiEquipamento(Equipamento equipamento) {
        if(equipamento == null || equipamento.isDeleted()){
            throw new RuntimeException("Equipamento inválido ou inativo.");
        }
        this.equipamento = equipamento;

    }

    public void setEquipamento(Equipamento equipamento) {
        this.equipamento = equipamento;
    }
}
