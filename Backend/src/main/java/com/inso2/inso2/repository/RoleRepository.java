package com.inso2.inso2.repository;

import com.inso2.inso2.model.Role;
import com.inso2.inso2.model.RoleName;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(RoleName name);
}
