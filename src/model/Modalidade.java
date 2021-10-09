package model;

import java.util.ArrayList;
import java.util.List;

public class Modalidade {
    private int codigo;
    private String descricao;
    private String duracao;
    private List<Integer> diasOferecimento;
    private List<String> horarios;
    private List<String> professores;
    private float valor;

    public Modalidade(int codigo, String descricao, String duracao, List<Integer> diasOferecimento, List<String> horarios, List<String> professores, float valor) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.duracao = duracao;
        this.diasOferecimento = diasOferecimento;
        this.horarios = horarios;
        this.professores = professores;
        this.valor = valor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public List<Integer> getDiasOferecimento() {
        return diasOferecimento;
    }

    public void setDiasOferecimento(List<Integer> diasOferecimento) {
        this.diasOferecimento = diasOferecimento;
    }

    public List<String> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<String> horarios) {
        this.horarios = horarios;
    }

    public List<String> getProfessores() {
        return professores;
    }

    public void setProfessores(List<String> professores) {
        this.professores = professores;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Modalidade: " +
                "Codigo = " + codigo +
                ", Descricao = '" + descricao + '\'' +
                ", Duracao = '" + duracao + '\'' +
                ", Dias Oferecidos = " + traduzDias(diasOferecimento) +
                ", Horarios = " + horarios +
                ", Professores = " + professores +
                ", Valor=" + valor +
                '\n';
    }

    public List<String> traduzDias(List<Integer> diasCadastrados){
        String[] dias = {"Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"};
        List<String> diasTraduzidos = new ArrayList<String>();

        for(Integer dia : diasCadastrados){
            diasTraduzidos.add(dias[dia - 1]);
        }

        return diasTraduzidos;
    }


}
