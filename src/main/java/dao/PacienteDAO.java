package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.BeanUserFone;
import model.Paciente;
import conexaoJDBC.ConexaoDB;
import model.Telefone;

public class PacienteDAO {

    private Connection connection;

    public PacienteDAO() {
        connection = ConexaoDB.SingleConnection.getConnection();
    }

    // Método salvar dados no banco.
    public void salvar(Paciente paciente) throws SQLException {
        try {
            String sql = "insert into paciente(cpf, nome, observacao) values (?,?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, paciente.getCpf());
            insert.setString(2, paciente.getNome());
            insert.setString(3, paciente.getObservacao());
            insert.execute();
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    public List<Paciente> listar() throws SQLException {
        List<Paciente> list = new ArrayList<>();

        String sql = "select * from paciente";

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {
            Paciente paciente = new Paciente();
            paciente.setPk_id_paciente(resultado.getLong("pk_id_paciente"));
            paciente.setCpf(resultado.getString("cpf"));
            paciente.setNome(resultado.getString("nome"));
            paciente.setObservacao(resultado.getString("observacao"));

            list.add(paciente);
        }
        return list;
    }

    public Paciente buscar(Long id) throws SQLException {
        Paciente buscaPaciente = new Paciente();

        String sql = "select * from paciente where pk_id_paciente = " + id;

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {
            buscaPaciente.setPk_id_paciente(resultado.getLong("pk_id_paciente"));
            buscaPaciente.setCpf(resultado.getString("cpf"));
            buscaPaciente.setNome(resultado.getString("nome"));
            buscaPaciente.setObservacao(resultado.getString("observacao"));
        }

        return buscaPaciente;
    }

    public void deletar(Long id) {
        try {
            String sql = "delete from paciente where pk_id_paciente = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            deleteFonesPorPaciente(id); // chama o método deletar telefone do paciente.
            preparedStatement.execute(); // executa a query depois de apagar o filho telefone.
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }

    public void salvarTelefone(Telefone telefone) {

        try {

            String sql = "insert into tel_paciente (numero, tipo, paciente_pessoa) values (?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, telefone.getNumero());
            statement.setString(2, telefone.getTipo());
            statement.setLong(3,telefone.getPacientePessoa());
            statement.execute();
            connection.commit();

        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }

    public List<BeanUserFone> listarTelPaciente(Long pk_id_paciente){

        List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();

        String sql = "select nome, numero, observacao from tel_paciente as telefone " +
                "inner join paciente as pacientes " +
                "on telefone.paciente_pessoa = pacientes.pk_id_paciente " +
                "where pacientes.pk_id_paciente = " + pk_id_paciente;
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultado = statement.executeQuery();

            while (resultado.next()){
                BeanUserFone userFone = new BeanUserFone();
                userFone.setObservacao(resultado.getString("observacao"));
                userFone.setNome(resultado.getString("nome"));
                userFone.setNumero(resultado.getString("numero"));
                beanUserFones.add(userFone);

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return beanUserFones;
    }

    public void deleteFonesPorPaciente(Long pk_id_paciente){
        try {
            String sqlFone = "delete from tel_paciente where paciente_pessoa = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlFone);
            preparedStatement.setLong(1, pk_id_paciente);
            preparedStatement.execute();
            connection.commit();
        }catch(Exception e){
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
}
