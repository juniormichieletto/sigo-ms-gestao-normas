package br.com.pucminas.usecase;

import br.com.pucminas.domain.Norma;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface ListarNormas {
    Uni<List<Norma>> listar();
}
