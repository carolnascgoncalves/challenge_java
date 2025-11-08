package br.com.fiap.dao;

import br.com.fiap.dto.ExameAgendamentosDto;
import br.com.fiap.dto.ExameReagendarDto;
import br.com.fiap.dto.ExameResultadosMostrarDto;
import br.com.fiap.enums.ExamesPossiveisEnum;
import br.com.fiap.enums.InstitutoEnum;
import br.com.fiap.enums.TipoExameEnum;
import br.com.fiap.models.Emissao;
import br.com.fiap.models.Exame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.enterprise.context.ApplicationScoped;
@ApplicationScoped
public class ExameDao {
    private EmissaoDao emissaoDao = new EmissaoDao();
    private Connection conexao;

    public void cadastrar(Exame exame) throws SQLException{
        conexao = ConnectionFactory.obterConexao();
        String sql = "insert into exame_hc(tp_ex,instituicao_ex,id_emissao_fk) \n" +
                "values(?,?,?)";

        try(PreparedStatement ps = conexao.prepareStatement(sql)){

            Emissao emissao = new Emissao();
            emissao.criarEmissao(exame.getNome(), exame.getMedico(), exame.getPaciente(), exame.getValidade());
            var idEm = emissaoDao.cadastrar(emissao);

            ps.setString(1, exame.getTipoExame().toString().toUpperCase());
            ps.setString(2, exame.getUnidade().toString().toUpperCase());
            ps.setInt(3, idEm);

            ps.executeUpdate();
        }
    }


    public List<ExameResultadosMostrarDto> listar(String tipo, int id){
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        List<ExameResultadosMostrarDto> exames = new ArrayList<>();
        if(!tipo.equalsIgnoreCase("laboratorial") && !tipo.equalsIgnoreCase("imagem")){
            return exames;
        }

        try {
            String sql = "select em.nome_em, em.data_val, usuMed.nome_usu AS nome_medico, x.tp_ex from emissao_hc em\n" +
                    "join medico_hc med on em.id_med_resp_fk = med.id_med\n" +
                    "join paciente_hc pac on em.id_pac_resp_fk = pac.id_pac\n" +
                    "join usuario_hc usuMed on med.usuar_cpf_fk = usuMed.cpf_usu\n" +
                    "join usuario_hc usuPac on pac.usuar_cpf_fk = usuPac.cpf_usu\n" +
                    "JOIN exame_hc x ON x.id_emissao_fk = em.id_em\n" +
                    "where pac.id_pac = ? and x.tp_ex = ?\n" +
                    "order by em.data_val";

            ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, tipo.toUpperCase());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ExameResultadosMostrarDto exame = new ExameResultadosMostrarDto();
                exame.setNomeExame(rs.getString("nome_em"));
                exame.setDataResultado(rs.getDate("data_val"));
                exame.setNomeMedico(rs.getString("nome_medico"));
                exame.setTipoExame(TipoExameEnum.valueOf(rs.getString("tp_ex").toUpperCase()));
                exames.add(exame);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return exames;
    }

    public List<ExameAgendamentosDto> listarAgendamentos(int id){
        List<ExameAgendamentosDto> exames = new ArrayList<>();
        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;

        try{
            String sql = "select em.nome_em, usuMed.nome_usu, ex.instituicao_ex, em.data_val from emissao_hc em\n" +
                    "join exame_hc ex on em.id_em = ex.id_emissao_fk\n" +
                    "join medico_hc med on med.id_med = em.id_med_resp_fk\n" +
                    "join usuario_hc usuMed on usuMed.cpf_usu = med.usuar_cpf_fk\n" +
                    "join paciente_hc pac on pac.id_pac = em.id_pac_resp_fk\n" +
                    "join usuario_hc usuPac on pac.usuar_cpf_fk = usuPac.cpf_usu\n" +
                    "where pac.id_pac = ? order by em.data_val";

            ps = conexao.prepareStatement(sql);

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ExameAgendamentosDto exame = new ExameAgendamentosDto();
                exame.setNomeExame(ExamesPossiveisEnum.valueOf(rs.getString("nome_em")));
                exame.setNomeMedico(rs.getString("nome_usu"));
                exame.setInstituto(InstitutoEnum.valueOf(rs.getString("instituicao_ex")));
                exame.setDataExame(rs.getDate("data_val"));

                exames.add(exame);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return exames;
    }

    public boolean deletarExame(int idExame, int idPac) throws SQLException{
        conexao = ConnectionFactory.obterConexao();

        Integer idEmissao = null;
        String sqlIdEmissao = "select id_emissao_fk from exame_hc ex\n" +
                "join emissao_hc em on em.id_em = ex.id_emissao_fk\n" +
                "join paciente_hc pac on pac.id_pac = em.id_pac_resp_fk\n" +
                "where pac.id_pac = ? and ex.id_ex = ?";

        String sqlExame = "delete from exame_hc where id_ex = ?";

        try(PreparedStatement psIdEmissao = conexao.prepareStatement(sqlIdEmissao);
            PreparedStatement psExame = conexao.prepareStatement(sqlExame)){

            psIdEmissao.setInt(1, idPac);
            psIdEmissao.setInt(2, idExame);

            ResultSet rs = psIdEmissao.executeQuery();
            if(rs.next()){
                idEmissao = rs.getInt(1);
            }else{
                return false;
            }

            psExame.setInt(1, idExame);
            psExame.executeUpdate();

            emissaoDao.deletar(idEmissao);
        }
        return true;
    }

    public void alterarExame(int idExame, int idPac, ExameReagendarDto novoExame) throws SQLException{
        List<Integer> Ids = new ArrayList<>();

        conexao = ConnectionFactory.obterConexao();

        String sqlIds = "select ex.id_ex from exame_hc ex\n" +
                "join emissao_hc em on em.id_em = ex.id_emissao_fk\n" +
                "join paciente_hc pac on pac.id_pac = em.id_pac_resp_fk\n" +
                "join usuario_hc usuPac on usuPac.cpf_usu = pac.usuar_cpf_fk\n" +
                "where pac.id_pac = ? and em.data_val >= current_date order by 1";

        String sql = "UPDATE emissao_hc em\n" +
                "SET em.data_val = ?\n" +
                "WHERE em.id_em = (\n" +
                "  SELECT ex.id_emissao_fk\n" +
                "  FROM exame_hc ex\n" +
                "  WHERE ex.id_ex = ?\n" +
                ")";

        try(PreparedStatement psIds = conexao.prepareStatement(sqlIds);
            PreparedStatement ps = conexao.prepareStatement(sql)){

            psIds.setInt(1, idPac);

            ResultSet rs = psIds.executeQuery();
            while(rs.next()){
                int id = rs.getInt(1);
                Ids.add(id);
            }
            if(!Ids.contains(idExame)){
                return;
            }


            ps.setTimestamp(1, novoExame.getNovaData());
            ps.setInt(2, idExame);

            ps.executeUpdate();
        }
    }
}
