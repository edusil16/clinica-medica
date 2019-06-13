import dao.PacienteDAO;
import model.BeanUserFone;
import model.Paciente;
import model.Telefone;
import org.junit.Test;

import java.util.*;

import java.sql.SQLException;

public class TestaBancoJdbc {
    @Test
    public void salvarNoBanco() throws SQLException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        Paciente paciente = new Paciente();
        Paciente paciente1 = new Paciente();
        Paciente paciente2 = new Paciente();

        paciente.setCpf("145.985.698-47");
        paciente.setNome("Clodoaldo Batista");
        paciente.setObservacao("Dores musculares");

        paciente1.setCpf("939.907.407.25");
        paciente1.setNome("Lindalva Josefa");
        paciente1.setObservacao("Dores de cabeça");

        paciente2.setCpf("132.916.697-38");
        paciente2.setNome("Eduardo Santos");
        paciente2.setObservacao("Dores abdominais");

        pacienteDAO.salvar(paciente);
        pacienteDAO.salvar(paciente1);
        pacienteDAO.salvar(paciente2);
    }

    @Test
    public void iniciarLista() {
        PacienteDAO pacienteDAO = new PacienteDAO();

        try {
            List<Paciente> list = pacienteDAO.listar();

            for (Paciente paciente : list) {
                System.out.println("-------------------------");
                System.out.println(paciente);
                System.out.println("-------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void iniciarBusca() {
        PacienteDAO pacienteDAO = new PacienteDAO();

        try {
            Paciente paciente = pacienteDAO.buscar(1l);
            System.out.println(paciente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void iniciarDelete(){
        try{
            PacienteDAO pacienteDAO = new PacienteDAO();
            pacienteDAO.deletar(5l);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void testeInsertTelefone(){
        PacienteDAO paciente = new PacienteDAO();
        for (Telefone telefone : telefones()) {
            paciente.salvarTelefone(telefone);
        }
    }

    @Test
    public void carregarDadosDoPaciente(){
        PacienteDAO pacienteDAO = new PacienteDAO();

        List<BeanUserFone> beanUserFones = pacienteDAO.listarTelPaciente(1L);

        for (BeanUserFone beanUserFone : beanUserFones) {
            System.out.println(beanUserFone);
            System.out.println("----------------------------------");
        }
    }

    @Test
    public void testaDelatarFone(){
        PacienteDAO pacienteDAO = new PacienteDAO();
        pacienteDAO.deleteFonesPorPaciente(1L);
    }

    private List<Telefone> telefones(){
        Telefone telefone = new Telefone();
        Telefone telefone1 = new Telefone();
        Telefone telefone2 = new Telefone();
        Telefone telefone3 = new Telefone();
        Telefone telefone4 = new Telefone();

        telefone.setNumero("(21) 2409-5961");
        telefone.setTipo("fixo");

        telefone1.setNumero("(21) 3409-5961");
        telefone1.setTipo("fixo");

        telefone2.setNumero("(21)99781-5496");
        telefone2.setTipo("celular");

        telefone3.setNumero("(21) 3658-9568");
        telefone3.setTipo("fixo");

        telefone4.setNumero("(21) 9 6620-5855");
        telefone4.setTipo("celular");

        telefone1.setPacientePessoa(String.valueOf(5L));
        telefone2.setPacientePessoa(String.valueOf(5L));
        telefone3.setPacientePessoa(String.valueOf(6L));

        return Arrays.asList(telefone, telefone1, telefone2, telefone3, telefone4);
    }
}