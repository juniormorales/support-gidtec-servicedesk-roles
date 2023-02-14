package com.pe.gidtec.servicedesk.roles.rol.repository;

import com.pe.gidtec.servicedesk.roles.rol.model.entity.RoleEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolesRepository extends ReactiveMongoRepository<RoleEntity,String> {

    Mono<Boolean> existsRoleEntityByRoleIdAndAuditDeletedFalse(String id);

    Mono<RoleEntity> findRoleEntityByRoleIdAndAuditDeletedFalse(String id);

    Mono<Boolean> existsRoleEntityByRoleNameAndAuditDeletedFalse(String roleName);

    Flux<RoleEntity> findAllByAuditDeletedFalse();

}
