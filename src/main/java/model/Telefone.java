package model;

public class Telefone {

    private Long id;
    private String numero;
    private String tipo;

    private String pacientePessoa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getPacientePessoa() {
        return Long.parseLong(pacientePessoa);
    }

    public void setPacientePessoa(String pacientePessoa) {
        this.pacientePessoa = pacientePessoa;
    }
}
