package com.katrasolutions.gjethja.repository;

import com.katrasolutions.gjethja.entity.Role;
import com.katrasolutions.gjethja.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(RoleName roleName);
}
