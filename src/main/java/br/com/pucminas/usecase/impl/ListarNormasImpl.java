package br.com.pucminas.usecase.impl;

import br.com.pucminas.domain.Norma;
import br.com.pucminas.repository.NormaRepository;
import br.com.pucminas.usecase.ListarNormas;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
@Transactional
public class ListarNormasImpl implements ListarNormas {

    private final NormaRepository normaRepository;

    public ListarNormasImpl(NormaRepository normaRepository) {
        this.normaRepository = normaRepository;
    }

    @Override
    public Uni<List<Norma>> listar() {
        return normaRepository.findAll();
    }
}
