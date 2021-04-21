package com.inso2.inso2.repository;

import com.inso2.inso2.model.Role;
import com.inso2.inso2.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleName name);
}
