package com.petcare.repository;

import com.petcare.entity.Pet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositório responsável pelas operações de persistência da entidade Pet.
 * <p>
 * Esta interface estende MongoRepository, que fornece métodos CRUD (Create, Read, Update, Delete)
 * básicos para a entidade Pet. O Spring Data MongoDB implementa automaticamente esses
 * métodos em tempo de execução, permitindo operações como salvar, buscar, atualizar e excluir
 * pets no banco de dados MongoDB.
 * <p>
 * Além dos métodos básicos fornecidos pelo MongoRepository, esta interface define métodos
 * personalizados para buscar pets por critérios específicos, como o ID do tutor e o nome do pet.
 * O Spring Data MongoDB implementa automaticamente esses métodos com base na convenção de
 * nomenclatura (findBy seguido do nome do campo).
 *
 * @author Estudante de Programação
 * @version 1.0
 * @see com.petcare.entity.Pet
 * @see org.springframework.data.mongodb.repository.MongoRepository
 */
public interface PetRepository extends MongoRepository<Pet, String> {

    /**
     * Busca todos os pets associados a um determinado tutor.
     * <p>
     * Este método utiliza a convenção de nomenclatura do Spring Data para criar uma consulta
     * que busca todos os pets cujo campo idTutor corresponde ao valor fornecido.
     *
     * @param idTutor O identificador único do tutor
     * @return Uma lista de pets associados ao tutor especificado
     */
    List<Pet> findByIdTutor(String idTutor);

    /**
     * Busca um pet pelo seu nome.
     * <p>
     * Este método utiliza a convenção de nomenclatura do Spring Data para criar uma consulta
     * que busca um pet cujo campo nome corresponde ao valor fornecido. O resultado é encapsulado
     * em um Optional para lidar com o caso em que nenhum pet é encontrado.
     *
     * @param nome O nome do pet a ser buscado
     * @return Um Optional contendo o pet encontrado, ou um Optional vazio se nenhum pet for encontrado
     */
    Optional<Pet> findByNome(String nome);
}
