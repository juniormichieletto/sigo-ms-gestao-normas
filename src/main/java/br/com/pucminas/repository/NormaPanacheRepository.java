package br.com.pucminas.repository;

import br.com.pucminas.domain.Norma;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NormaPanacheRepository implements PanacheRepository<Norma> {
}
