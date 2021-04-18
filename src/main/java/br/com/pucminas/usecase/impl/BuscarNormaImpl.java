package br.com.pucminas.usecase.impl;

import br.com.pucminas.domain.Norma;
import br.com.pucminas.repository.NormaRepository;
import br.com.pucminas.usecase.BuscarNorma;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

@RequestScoped
@Transactional
class BuscarNormaImpl implements BuscarNorma {

    private final NormaRepository normaRepository;

    BuscarNormaImpl(NormaRepository normaRepository) {
        this.normaRepository = normaRepository;
    }

    @Override
    public Uni<Norma> buscar(Long normaId) {
        return normaRepository.findById(normaId);
    }
}
