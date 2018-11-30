package org.cptjmg.consultaprocesso.model;

import java.util.ArrayList;
import java.util.List;

public class Processo {
    private String assunto;
    private String classe;

    private List<Movimentacao> movimentacao = new ArrayList<>();
    private List<String> partes;

    public Processo() {
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public List<Movimentacao> getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(List<Movimentacao> movimentacao) {
        this.movimentacao = movimentacao;
    }

    public List<String> getPartes() {
        return partes;
    }

    public void setPartes(List<String> partes) {
        this.partes = partes;
    }
}

