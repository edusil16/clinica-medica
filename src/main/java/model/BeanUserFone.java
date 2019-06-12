package model;

public class BeanUserFone {

    private String nome;
    private String numero;
    private String observacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "BeanUserFone{" +
                "nome='" + nome + '\'' +
                ", numero='" + numero + '\'' +
                ", observacao='" + observacao + '\'' +
                '}';
    }
}
