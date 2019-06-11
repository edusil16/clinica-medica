import dao.PacienteDAO;
import model.Paciente;
import org.junit.Test;

import java.util.*;

import java.sql.SQLException;

public class TestaBancoJdbc {
    @Test
    public void salvarNoBanco() throws SQLException {
        PacienteDAO pacienteDAO = new PacienteDAO();
        Paciente paciente = new Paciente();

          paciente.setCpf("145.985.698-47");
          paciente.setNome("Clodoaldo Batista");
          paciente.setObservacao("Dores musculares");


            pacienteDAO.salvar(paciente);
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
            pacienteDAO.deletar(2l);
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}