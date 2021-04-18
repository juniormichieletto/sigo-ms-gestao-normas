package br.com.pucminas.repository;

import br.com.pucminas.domain.Norma;
import io.smallrye.mutiny.Uni;

import java.util.List;

public interface NormaRepository {
    Uni<List<Norma>> findAll();

    Uni<Norma> save(Norma normaSalva);

    Uni<Norma> findById(Long normaId);
}
