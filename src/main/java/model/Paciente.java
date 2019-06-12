package model;

public class Paciente{

    private Long pk_id_paciente;
    private String nome;
    private String cpf;
    private String observacao;

    public Long getPk_id_paciente() {
        return pk_id_paciente;
    }

    public void setPk_id_paciente(Long pk_id_paciente) {
        this.pk_id_paciente = pk_id_paciente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "pk_id_paciente=" + pk_id_paciente +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
