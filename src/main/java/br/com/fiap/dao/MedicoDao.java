package br.com.fiap.dao;

import br.com.fiap.enums.SexoEnum;
import br.com.fiap.models.Medico;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MedicoDao {
    private Connection conexao;

    public Medico buscarPorNome(String nome){
        Medico medico = new Medico();
        PreparedStatement ps = null;
        conexao = ConnectionFactory.obterConexao();
        try{
            String sql = "select * from medico_hc m join usuario_hc u on m.usuar_cpf_fk = u.cpf_usu\n" +
                    "where u.nome_usu = ?";
            ps = conexao.prepareStatement(sql);
            ps.setString(1,nome);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                medico.setIdMed(rs.getInt("id_med"));
                medico.setAreaAtuacao(rs.getString("area_at_med"));
                medico.setCpf(rs.getString("cpf_usu"));
                medico.setNome(rs.getString("nome_usu"));
                medico.setDataNascimento(rs.getDate("data_nascimento_usu"));
                medico.setEmail(rs.getString("email_usu"));
                medico.setSexo(SexoEnum.valueOf(rs.getString("sexo_usu")));
                medico.setTelefone(rs.getString("telefone_usu"));
                medico.setSenha(rs.getString("senha_usu"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return medico;
    }

    public Medico buscarPorId(int id){
        Medico medico = new Medico();
        PreparedStatement ps = null;
        conexao = ConnectionFactory.obterConexao();
        try{
            String sql = "select * from medico_hc m\n" +
                    "inner join usuario_hc u on u.cpf_usu = m.usuar_cpf_fk\n" +
                    "where m.id_med = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                medico.setIdMed(rs.getInt("id_med"));
                medico.setAreaAtuacao(rs.getString("area_at_med"));
                medico.setCpf(rs.getString("cpf_usu"));
                medico.setNome(rs.getString("nome_usu"));
                medico.setDataNascimento(rs.getDate("data_nascimento_usu"));
                medico.setEmail(rs.getString("email_usu"));
                medico.setSexo(SexoEnum.valueOf(rs.getString("sexo_usu")));
                medico.setTelefone(rs.getString("telefone_usu"));
                medico.setSenha(rs.getString("senha_usu"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return medico;
    }
}
