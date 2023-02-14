package com.pe.gidtec.servicedesk.roles.rol.dao;

import com.pe.gidtec.servicedesk.roles.rol.model.entity.RoleEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolesDao {

    Mono<RoleEntity> saveRole(RoleEntity entity);

    Mono<Boolean> verifyIfExistsRoleById(String roleId);

    Mono<RoleEntity> getRoleById(String roleId);

    Flux<RoleEntity> findRolesActive();

    Mono<Boolean> verifyIfExistsRoleByName(String roleName);
}
