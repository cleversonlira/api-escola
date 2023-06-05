package com.escola.aluno;

import com.escola.atividade.Atividade;

import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Path("/api/alunos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AlunoResource {

    @GET
    public Response obterTodos() {
        List<Aluno> alunos = Aluno.listAll();
        return Response.ok(alunos).build();
    }

    @GET
    @Path("/{id}")
    public Response obterPeloId(@PathParam("id") Long id) {
        Aluno aluno = Aluno.findById(id);
        return Response.ok(aluno).build();
    }

    @POST
    @Transactional
    public Response cadastrar(Aluno aluno) {
        aluno.matricula = "a" + aluno.matricula;
        Aluno.persist(aluno);
        return Response.ok(aluno).build();
    }

    @PUT
    @Path("/{matricula}")
    @Transactional
    public Response alterar(@PathParam("matricula") String matricula, Aluno atualizacao) {
        Aluno aluno = Aluno.find("matricula", matricula).firstResult();
        if (aluno == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        aluno.matricula = atualizacao.matricula;
        aluno.nome = atualizacao.nome;
        aluno.persist();
        return Response.ok(aluno).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response remover(@PathParam("id") Long id) {
        Aluno.deleteById(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}/media")
    @Transactional
    public Response obterMediaDasAtividades(@PathParam("id") Long id) {
        List<Atividade> atividadesDoAluno = Atividade.list("aluno_id", id);
        BigDecimal notasSomadas = atividadesDoAluno.stream()
                .map(Atividade::getNota)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal media = BigDecimal.ZERO;
        if (notasSomadas.compareTo(BigDecimal.ZERO) > 0) {
            media = notasSomadas
                    .divide(BigDecimal.valueOf(atividadesDoAluno.size()), 2, RoundingMode.HALF_EVEN);
        }
        return Response.ok(media).build();
    }

}