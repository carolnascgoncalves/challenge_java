package br.com.fiap.dao;

import java.sql.*;
import br.com.fiap.dto.*;
import br.com.fiap.models.*;
import br.com.fiap.dao.*;

public class EmissaoDao {
    private Connection conexao;

    public Integer cadastrar(Emissao emissao){
        Integer id = null;
        PreparedStatement ps = null;
        conexao = ConnectionFactory.obterConexao();
        try{
            String sql = "insert into emissao_hc(nome_em, data_val, id_pac_resp_fk, id_med_resp_fk) " +
                    "values(?, ?, ?, ?)";
            ps = conexao.prepareStatement(sql, new String[] {"id_em"});
            ps.setString(1, emissao.getNome());
            ps.setDate(2, emissao.getValidade());
            ps.setInt(3, emissao.getPaciente().getIdPac());
            ps.setInt(4, emissao.getMedico().getIdMed());

            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                emissao.setIdEm(rs.getInt(1));
                id = rs.getInt(1);
            }
            rs.close();
            ps.close();
            conexao.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return id;
    }

    public void deletar(int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        try{
            String sql = "delete from emissao_hc where id_em = ?";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1,id);

            ps.executeUpdate();

            conexao.close();
            ps.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
