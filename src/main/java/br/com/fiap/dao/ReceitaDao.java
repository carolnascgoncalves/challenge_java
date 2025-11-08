package br.com.fiap.dao;

import br.com.fiap.dto.PacienteMostrarDto;
import br.com.fiap.dto.ReceitaMostrarDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class ReceitaDao {
    private Connection conexao;

    public List<ReceitaMostrarDto> listar(String tipo, int id) {
        PreparedStatement ps = null;
        conexao = ConnectionFactory.obterConexao();
        List<ReceitaMostrarDto> receitas = new ArrayList<>();

        if (!tipo.equalsIgnoreCase("novas-receitas") && !tipo.equalsIgnoreCase("historico-receitas")) {
            return receitas;
        }

        try {
            String sql = "select usuMed.nome_usu, em.data_val from emissao_hc em\n" +
                    "join receita_hc rec on em.id_em = rec.id_emissao_fk\n" +
                    "join medico_hc med on em.id_med_resp_fk = med.id_med\n" +
                    "join usuario_hc usuMed on med.usuar_cpf_fk = usuMed.cpf_usu\n" +
                    "join paciente_hc pac on em.id_pac_resp_fk = pac.id_pac\n" +
                    "join usuario_hc usuPac on pac.usuar_cpf_fk = usuPac.cpf_usu\n" +
                    "where pac.id_pac = ? ";

            if (tipo.equalsIgnoreCase("novas-receitas")) {
                sql += "and em.data_val >= CURRENT_DATE";
            } else {
                sql += "and em.data_val <= CURRENT_DATE";
            }

            ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ReceitaMostrarDto receita = new ReceitaMostrarDto();
                receita.setMedicoResponsavel(rs.getString("nome_usu"));
                receita.setDataEmissao(rs.getDate("data_val"));
                receita.setPrescricao("Digital");

                receitas.add(receita);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return receitas;
    }
}
