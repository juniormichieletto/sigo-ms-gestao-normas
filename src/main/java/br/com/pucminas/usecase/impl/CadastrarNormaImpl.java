package br.com.pucminas.usecase.impl;

import br.com.pucminas.domain.Norma;
import br.com.pucminas.repository.NormaRepository;
import br.com.pucminas.usecase.CadastrarNorma;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;

@RequestScoped
@Transactional
class CadastrarNormaImpl implements CadastrarNorma {

    private final NormaRepository normaRepository;

    public CadastrarNormaImpl(NormaRepository normaRepository) {
        this.normaRepository = normaRepository;
    }

    @Override
    public Norma cadastra(Norma norma) {
        return normaRepository.save(norma);
    }
}
