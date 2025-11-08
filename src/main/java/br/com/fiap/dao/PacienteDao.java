package br.com.fiap.dao;


import br.com.fiap.dto.*;
import br.com.fiap.enums.*;
import br.com.fiap.models.*;
import br.com.fiap.service.*;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PacienteDao {
    private Connection conexao;
    public void cadastrar(Paciente paciente) throws SQLException{

        conexao = ConnectionFactory.obterConexao();
        String sqlPac = "insert into paciente_hc(usuar_cpf_fk, nome_mae_pac) values(?, ?)";
        String sql = "insert into usuario_hc(cpf_usu,nome_usu,email_usu,sexo_usu,telefone_usu,senha_usu,tipo_usu, data_nascimento_usu, id_endereco_fk)\n" +
                "values(?,?,?,?,?,?,?,?,?)";

        EnderecoDao endDao = new EnderecoDao();

        try (Connection connection = ConnectionFactory.obterConexao();
             PreparedStatement comandoSQL = connection.prepareStatement(sql);
             PreparedStatement comandoSQLPac = connection.prepareStatement(sqlPac)){

            comandoSQL.setString(1, paciente.getCpf());
            comandoSQL.setString(2, paciente.getNome());
            comandoSQL.setString(3, paciente.getEmail());
            comandoSQL.setString(4, paciente.getSexo().toString());
            comandoSQL.setString(5, paciente.getTelefone());
            comandoSQL.setString(6, paciente.getSenha());
            comandoSQL.setString(7, paciente.getTipo().toString());

            comandoSQL.setDate(8, paciente.getDataNascimento());

            var endereco = endDao.buscarPorCep(paciente.getEndereco().getCep().replace("-",""));
            //busca o endereco
            if(endereco != null){
                comandoSQL.setInt(9, endereco.getId());
            }else{
                var novoEnd = ViaCepService.buscaEndereco(paciente.getEndereco().getCep());
                endDao.inserir(novoEnd);

                var teste = endDao.buscarPorCep(novoEnd.getCep().replace("-",""));
                comandoSQL.setInt(9, teste.getId());
            }

            comandoSQL.executeUpdate();
            comandoSQL.close();

            comandoSQLPac.setString(1, paciente.getCpf());
            comandoSQLPac.setString(2, paciente.getNomeMae());

            comandoSQLPac.executeUpdate();
            comandoSQLPac.close();

            conexao.close();

        }
    }

    public String login(Paciente paciente){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            String sql = "select * from usuario_hc where cpf_usu = ? and senha_usu = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1, paciente.getCpf());
            ps.setString(2, paciente.getSenha());

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return "Usuário logado com sucesso";
            }else{
                return "Usuário e/ou senha inválidos";
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return "Usuário não encontrado";
    }

    public PacienteMostrarDto mostrarInfos(int id){
        Connection connection = ConnectionFactory.obterConexao();
        PacienteMostrarDto paciente = new PacienteMostrarDto();
        PreparedStatement ps = null;

        try{
            String sql = "select usu.cpf_usu, usu.nome_usu from paciente_hc pac\n" +
                    "join usuario_hc usu on usu.cpf_usu = pac.usuar_cpf_fk \n" +
                    "where pac.id_pac = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                paciente.setCpf(rs.getString("cpf_usu"));
                paciente.setNome(rs.getString("nome_usu"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return paciente;
    }

    public Paciente buscarLoginPac(String cpf){
        Connection connection = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Paciente paciente = new Paciente();

        try{
            String sql = "SELECT * FROM usuario_hc WHERE cpf_usu = ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                paciente.setCpf(rs.getString("cpf_usu"));
                paciente.setSenha(rs.getString("senha_usu"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return paciente;
    }

    public Paciente buscarPac(String cpf){
        EnderecoDao endDao = new EnderecoDao();
        Connection connection = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Paciente paciente = new Paciente();

        try{
            String sql = "select * from paciente_hc p\n" +
                    "join usuario_hc u on p.usuar_cpf_fk = u.cpf_usu\n" +
                    "where usuar_cpf_fk = ?";
            ps = connection.prepareStatement(sql);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                paciente.setCpf(rs.getString("cpf_usu"));
                paciente.setNome(rs.getString("nome_usu"));
                paciente.setDataNascimento(rs.getDate("data_nascimento_usu"));
                paciente.setEmail(rs.getString("email_usu"));
                paciente.setSexo(SexoEnum.valueOf(rs.getString("sexo_usu")));
                paciente.setTelefone(rs.getString("telefone_Usu"));
                paciente.setSenha(rs.getString("senha_usu"));
                paciente.setTipo(TipoUsuarioEnum.valueOf(rs.getString("tipo_usu")));

                paciente.setNomeMae(rs.getString("nome_mae_pac"));
                paciente.setIdPac(rs.getInt("id_pac"));

                EnderecoResponseDto dto = new EnderecoResponseDto();
                var endereco = endDao.buscarPorId(rs.getInt("id_endereco_fk"));
                paciente.setEndereco(dto.convertToEndereco(endereco));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return paciente;
    }

    public Paciente buscarPacId(int id){
        EnderecoDao endDao = new EnderecoDao();
        Connection connection = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        Paciente paciente = new Paciente();

        try{
            String sql = "select * from paciente_hc p\n" +
                    "join usuario_hc u on p.usuar_cpf_fk = u.cpf_usu\n" +
                    "where p.id_pac = ?";
            ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                paciente.setCpf(rs.getString("cpf_usu"));
                paciente.setNome(rs.getString("nome_usu"));
                paciente.setDataNascimento(rs.getDate("data_nascimento_usu"));
                paciente.setEmail(rs.getString("email_usu"));
                paciente.setSexo(SexoEnum.valueOf(rs.getString("sexo_usu")));
                paciente.setTelefone(rs.getString("telefone_Usu"));
                paciente.setSenha(rs.getString("senha_usu"));
                paciente.setTipo(TipoUsuarioEnum.valueOf(rs.getString("tipo_usu")));

                paciente.setNomeMae(rs.getString("nome_mae_pac"));
                paciente.setIdPac(rs.getInt("id_pac"));

                EnderecoResponseDto dto = new EnderecoResponseDto();
                var endereco = endDao.buscarPorId(rs.getInt("id_endereco_fk"));
                paciente.setEndereco(dto.convertToEndereco(endereco));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return paciente;
    }

    public String mostrarDocumento(int id) throws SQLException{
        String url = null;
        String sql = "select url_img_pac from paciente_hc where id_pac = ?";

        try(Connection conexao = ConnectionFactory.obterConexao();
            PreparedStatement ps = conexao.prepareStatement(sql)){

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                url = rs.getString("url_img_pac");
            }

            return url;
        }
    }
}
