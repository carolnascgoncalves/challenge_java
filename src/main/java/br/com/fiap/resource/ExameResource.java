package br.com.fiap.resource;

import br.com.fiap.dto.ExameAgendamentosDto;
import br.com.fiap.dto.ExameCadastroDto;
import br.com.fiap.dto.ExameReagendarDto;
import br.com.fiap.dto.ExameResultadosMostrarDto;
import br.com.fiap.service.ExameService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

@Path("/paciente/exames")
public class ExameResource {
    @Inject
    ExameService exameService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response cadastrar(ExameCadastroDto exame){
        try{
            var id = exameService.cadastrar(exame);
            return Response.ok(id).build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar exame!")
                    .build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id_paciente}/{tipo}")
    public List<ExameResultadosMostrarDto> listar(@PathParam("tipo") String tipo, @PathParam("id_paciente") int id){
        try{
            return exameService.listar(tipo, id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/agendamentos/{id_paciente}")
    public List<ExameAgendamentosDto> listarAgendamentos(@PathParam("id_paciente") int id){
        return exameService.listarAgendamentos(id);
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/desmarcar-exame/{id_paciente}/{id_exame}")
    public Response deletar(@PathParam("id_exame") int idExame, @PathParam("id_paciente") int idPac){
        try{
            if(exameService.deletar(idExame, idPac)){
                return Response.ok("Exame desmarcado com sucesso!").build();
            }
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Exame não encontrado!")
                    .build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao desmarcar exame!")
                    .build();
        }
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/reagendar-exame/{id_paciente}/{id_exame}")
    public Response reagendar(@PathParam("id_exame") int idExame , @PathParam("id_paciente") int idPac, ExameReagendarDto novoExame){
        try{
            exameService.reagendar(idExame, idPac, novoExame);
            return Response.ok("Exame reagendado com sucesso!").build();
        }catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao reagendar exame!")
                    .build();
        }
    }

}
