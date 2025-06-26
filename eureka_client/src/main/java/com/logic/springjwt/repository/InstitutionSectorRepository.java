// Repository
package com.logic.springjwt.repository;


import com.logic.springjwt.models.InstituteSectorModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstitutionSectorRepository extends CrudRepository<InstituteSectorModel, Long> {
}