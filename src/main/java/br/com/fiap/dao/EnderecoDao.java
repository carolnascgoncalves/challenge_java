package br.com.fiap.dao;
import br.com.fiap.dto.EnderecoResponseDto;
import br.com.fiap.models.Endereco;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EnderecoDao {
    private Connection conexao;

    public void inserir(Endereco endereco) {
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement comandoSQL = null;
        try {
            String sql = "insert into endereco_hc (logradouro_end, cep_end, bairro_end, cidade_end, estado_end, uf_end, numero_end, complemento_end) " +
                    "values (?, ?, ?, ?, ?, ?, ?, ?)";
            comandoSQL = conexao.prepareStatement(sql);
            comandoSQL.setString(1, endereco.getLogradouro());
            comandoSQL.setString(2, endereco.getCep().replace("-", ""));
            comandoSQL.setString(3, endereco.getBairro());
            comandoSQL.setString(4, endereco.getCidade());
            comandoSQL.setString(5, endereco.getEstado());
            comandoSQL.setString(6, endereco.getUf());
            comandoSQL.setString(7, endereco.getNumero());
            comandoSQL.setString(8, endereco.getComplemento());

            ResultSet rs = comandoSQL.getGeneratedKeys();
            if (rs.next()) {
                endereco.setIdEnd(rs.getInt(1)); // <<< ESSA LINHA É CRUCIAL
            }

            comandoSQL.executeUpdate();

            rs.close();
            conexao.close();
            comandoSQL.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public EnderecoResponseDto buscarPorCep(String cep){
        conexao = ConnectionFactory.obterConexao();
        Endereco endereco = new Endereco();
        PreparedStatement comandoSql = null;
        try{
            comandoSql = conexao.prepareStatement("Select * from endereco_hc where cep_end = ?");
            comandoSql.setString(1,cep);
            ResultSet rs = comandoSql.executeQuery();

            if(rs.next()){
                endereco = new Endereco();

                endereco.setIdEnd((rs.getInt("id_end")));
                endereco.setLogradouro(rs.getString(2));
                endereco.setCep(rs.getString(3));
                endereco.setBairro(rs.getString(4));
                endereco.setCidade(rs.getString(5));
                endereco.setEstado(rs.getString(6));
                endereco.setUf(rs.getString(7));
                endereco.setNumero(rs.getString(8));
                endereco.setComplemento(rs.getString(9));
            }
            conexao.close();
            comandoSql.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        if(endereco.getCep() == null){
            return null;
        }
        EnderecoResponseDto dto = new EnderecoResponseDto();
        return dto.convertToDto(endereco);
    }

    public EnderecoResponseDto buscarPorId(Integer id){
        conexao = ConnectionFactory.obterConexao();
        Endereco endereco = new Endereco();
        PreparedStatement comandoSql = null;
        try{
            comandoSql = conexao.prepareStatement("Select * from endereco_hc where id_end = ?");
            comandoSql.setInt(1,id);
            ResultSet rs = comandoSql.executeQuery();
            if(rs.next()){
                endereco.setIdEnd((rs.getInt(1)));
                endereco.setLogradouro(rs.getString(2));
                endereco.setCep(rs.getString(3));
                endereco.setBairro(rs.getString(4));
                endereco.setCidade(rs.getString(5));
                endereco.setEstado(rs.getString(6));
                endereco.setUf(rs.getString(7));endereco.setNumero(rs.getString(8));
                endereco.setComplemento(rs.getString(9));
            }
            conexao.close();
            comandoSql.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        EnderecoResponseDto dto = new EnderecoResponseDto();
        return dto.convertToDto(endereco);
    }
}
