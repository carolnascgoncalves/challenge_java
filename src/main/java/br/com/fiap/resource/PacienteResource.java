package br.com.fiap.resource;

import br.com.fiap.dto.PacienteCadastroDto;
import br.com.fiap.dto.PacienteLoginDto;
import br.com.fiap.dto.PacienteMostrarDto;
import br.com.fiap.service.PacienteService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

@Path("/paciente")
public class PacienteResource {
    @Inject
    PacienteService pacienteService;

    @POST
    @Path("/criar-conta")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response cadastrarPaciente(PacienteCadastroDto paciente){
        try{
            pacienteService.cadastrar(paciente);
            PacienteLoginDto cadastrado = pacienteService.buscar(paciente.getCpf());
            if (cadastrado.getCpf().equals(paciente.getCpf())) {
                return Response.status(Response.Status.CREATED)
                        .entity("Paciente cadastrado com sucesso!")
                        .build();
            }

            return Response.status(Response.Status.BAD_REQUEST).build();
        }catch (SQLIntegrityConstraintViolationException a){
            return Response.status(Response.Status.CONFLICT)
                    .entity("Usuário já cadastrado!")
                    .build();
        }
        catch (SQLException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Erro ao cadastrar paciente!")
                    .build();
        }
    }

    @GET
    @Path("/{id_paciente}")
    @Produces(MediaType.APPLICATION_JSON)
    public PacienteMostrarDto mostrarInfos(@PathParam("id_paciente") int id){
        return pacienteService.mostrarInfos(id);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login (PacienteLoginDto usuario){
        String mensagem = pacienteService.login(usuario);

        if(mensagem.equals("Usuário logado com sucesso")){
            return Response.ok().entity(mensagem).build();
        }
        else if(mensagem.equals( "Usuário e/ou senha inválidos")){
            return Response.status(Response.Status.UNAUTHORIZED)
                    .entity(mensagem)
                    .build();
        }

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity("Erro ao autenticar usuário")
                .build();
    }

    @GET
    @Path("/documento/{id_paciente}")
    @Produces(MediaType.APPLICATION_JSON)
    public String mostrarDocumento(@PathParam("id_paciente") int id){
        try{
            return pacienteService.mostrarDocumento(id);
        }catch (SQLException e){
            return "Documento não encontrado!";
        }
    }


}
