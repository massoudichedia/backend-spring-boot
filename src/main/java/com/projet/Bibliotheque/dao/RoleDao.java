package com.projet.Bibliotheque.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.projet.Bibliotheque.entity.Role;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}
