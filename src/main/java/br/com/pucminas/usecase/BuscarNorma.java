package br.com.pucminas.usecase;

import br.com.pucminas.domain.Norma;

import java.util.Optional;

public interface BuscarNorma {

    Optional<Norma> buscar(Long normaId);
}
