package br.com.pucminas.repository;

import br.com.pucminas.domain.Norma;
import io.quarkus.hibernate.reactive.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NormaPanacheRepository implements PanacheRepository<Norma> {
}
