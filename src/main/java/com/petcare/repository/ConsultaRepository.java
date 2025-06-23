package com.petcare.repository;

import com.petcare.entity.Consulta;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositório responsável pelas operações de persistência da entidade Consulta.
 * <p>
 * Esta interface estende MongoRepository, que fornece métodos CRUD (Create, Read, Update, Delete)
 * básicos para a entidade Consulta. O Spring Data MongoDB implementa automaticamente esses
 * métodos em tempo de execução, permitindo operações como salvar, buscar, atualizar e excluir
 * consultas no banco de dados MongoDB.
 * <p>
 * Os parâmetros genéricos da interface MongoRepository são:
 * - Consulta: o tipo da entidade gerenciada pelo repositório
 * - String: o tipo do identificador (ID) da entidade
 * <p>
 * Este repositório não define métodos personalizados além dos fornecidos pelo MongoRepository,
 * pois as operações básicas são suficientes para as necessidades atuais da aplicação.
 *
 * @author Estudante de Programação
 * @version 1.0
 * @see com.petcare.entity.Consulta
 * @see org.springframework.data.mongodb.repository.MongoRepository
 */
public interface ConsultaRepository extends MongoRepository<Consulta, String> {
}
