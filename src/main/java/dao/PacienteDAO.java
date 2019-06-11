package dao;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Paciente;
import conexaoJDBC.ConexaoDB;

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
            paciente.setPk_id_paciente(resultado.getInt("pk_id_paciente"));
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
            buscaPaciente.setPk_id_paciente(resultado.getInt("pk_id_paciente"));
            buscaPaciente.setCpf(resultado.getString("cpf"));
            buscaPaciente.setNome(resultado.getString("nome"));
            buscaPaciente.setObservacao(resultado.getString("observacao"));
        }

        return buscaPaciente;
    }

    public void deletar(Long id){
        try{
            String sql = "delete from paciente where pk_id_paciente = " + id;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.commit();
        } catch(Exception e){
            try{
                connection.rollback();
            } catch(SQLException e1){
                e1.printStackTrace();
            }
        }

    }

}
