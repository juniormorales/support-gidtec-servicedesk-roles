package com.pe.gidtec.servicedesk.roles.rol.dao.impl;

import com.pe.gidtec.servicedesk.roles.rol.dao.RolesDao;
import com.pe.gidtec.servicedesk.roles.rol.model.entity.RoleEntity;
import com.pe.gidtec.servicedesk.roles.rol.repository.RolesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class RolesDaoImpl implements RolesDao {

    private final RolesRepository rolesRepository;

    @Override
    public Mono<RoleEntity> saveRole(RoleEntity entity) {
        return rolesRepository.save(entity)
                .doOnSuccess(message -> log.info("Rol " + message.toString() + " guardado con éxito."))
                .onErrorResume( ex -> Mono.error(new Exception("Ocurrio un error al intentar guardar el rol")));
}

    @Override
    public Mono<Boolean> verifyIfExistsRoleById(String roleId) {
        return rolesRepository.existsRoleEntityByRoleIdAndAuditDeletedFalse(roleId);
    }

    @Override
    public Mono<RoleEntity> getRoleById(String roleId) {
        return rolesRepository.findRoleEntityByRoleIdAndAuditDeletedFalse(roleId);
    }

    @Override
    public Flux<RoleEntity> findRolesActive() {
        return rolesRepository.findAllByAuditDeletedFalse()
                .onErrorResume( ex -> Mono.error(new Exception("Ocurrio un error al intentar listar los roles")));
    }

    @Override
    public Mono<Boolean> verifyIfExistsRoleByName(String roleName) {
        return rolesRepository.existsRoleEntityByRoleNameAndAuditDeletedFalse(roleName);
    }
}
