package br.com.fiap.dao;

import br.com.fiap.dto.ConsultaMostrarDto;
import br.com.fiap.dto.PacienteLoginDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDao {
    private Connection conexao;

    public List<ConsultaMostrarDto> listar(int id){
        PacienteDao pacDao = new PacienteDao();
        MedicoDao medDao = new MedicoDao();
        List<ConsultaMostrarDto> consultas = new ArrayList<>();

        conexao = ConnectionFactory.obterConexao();
        PreparedStatement ps = null;
        try{
            if(pacDao.buscarPacId(id).getNome() == null){
                return null;
            }

            String sql = "select * from consulta_hc c\n" +
                    "inner join emissao_hc e on e.id_em = c.id_emissao_fk\n" +
                    "where id_pac_resp_fk = ? order by e.data_val";
            ps = conexao.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ConsultaMostrarDto consulta = new ConsultaMostrarDto();
                consulta.setNomeMedico(medDao.buscarPorId(rs.getInt("id_med_resp_fk")).getNome());
                consulta.setDataConsulta(rs.getDate("data_val"));
                consulta.setEspecialidadeConsulta(medDao.buscarPorId(rs.getInt("id_med_resp_fk")).getAreaAtuacao());

                consultas.add(consulta);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return consultas;
    }
}
