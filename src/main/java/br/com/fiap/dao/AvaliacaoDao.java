package br.com.fiap.dao;

import br.com.fiap.dto.AvaliacaoCadastroDto;
import br.com.fiap.enums.OpAvaliacaoEnum;
import br.com.fiap.models.Avaliacao;
import br.com.fiap.models.Exame;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.constraints.Null;
import oracle.jdbc.proxy.annotation.Pre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@ApplicationScoped
public class AvaliacaoDao {
    private Connection conexao;

    public void cadastrar(AvaliacaoCadastroDto avaliacao){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        String sql = "insert into avaliacao_hc(INTERNACAO_AV, TELECONSULTA_AV, PRONTO_SOCORRO_AV, AMBULANCIA_AV, FARMACIA_AV";

        try{
            if(avaliacao.getComentario() != null){
                sql += ", COMENTARIO_AV) values(?, ?, ?, ?, ?, ?)";
            }else{
                sql += ") values(?, ?, ?, ?, ?)";
            }

            ps = conexao.prepareStatement(sql);

            ps.setString(1, avaliacao.getInternacao().toString());
            ps.setString(2, avaliacao.getTeleconsulta().toString());
            ps.setString(3, avaliacao.getProntoSocorro().toString());
            ps.setString(4, avaliacao.getAmbulancia().toString());
            ps.setString(5, avaliacao.getFarmacia().toString());

            if(avaliacao.getComentario() != null){
                ps.setString(6, avaliacao.getComentario());
            }

            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Avaliacao> listar(){
        List<Avaliacao> avalicoes = new ArrayList<>();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        try{
            String sql = "select * from avaliacao_hc";

            ps = conexao.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();


            while(rs.next()){
                Avaliacao avaliacao = new Avaliacao();

                avaliacao.setId(rs.getInt("id_av"));
                avaliacao.setInternacao(OpAvaliacaoEnum.valueOf(rs.getString("INTERNACAO_AV")));
                avaliacao.setTeleconsulta(OpAvaliacaoEnum.valueOf(rs.getString("TELECONSULTA_AV")));
                avaliacao.setProntoSocorro(OpAvaliacaoEnum.valueOf(rs.getString("PRONTO_SOCORRO_AV")));
                avaliacao.setAmbulancia(OpAvaliacaoEnum.valueOf(rs.getString("AMBULANCIA_AV")));
                avaliacao.setFarmacia(OpAvaliacaoEnum.valueOf(rs.getString("FARMACIA_AV")));
                avaliacao.setComentario(rs.getString("COMENTARIO_AV"));

                avalicoes.add(avaliacao);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return avalicoes;
    }
}
