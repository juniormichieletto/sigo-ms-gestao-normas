package br.com.pucminas.repository;

import br.com.pucminas.domain.Norma;

import java.util.List;
import java.util.Optional;

public interface NormaRepository {
    List<Norma> findAll();

    Norma save(Norma normaSalva);

    Optional<Norma> findById(Long normaId);
}
