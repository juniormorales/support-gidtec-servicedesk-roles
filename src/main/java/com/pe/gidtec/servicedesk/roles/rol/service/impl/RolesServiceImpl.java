package com.pe.gidtec.servicedesk.roles.rol.service.impl;

import com.pe.gidtec.servicedesk.roles.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.roles.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.roles.common.util.ResponseStatus;
import com.pe.gidtec.servicedesk.roles.rol.dao.RolesDao;
import com.pe.gidtec.servicedesk.roles.rol.mapper.RolesMapper;
import com.pe.gidtec.servicedesk.roles.rol.model.api.request.RolePostRequest;
import com.pe.gidtec.servicedesk.roles.rol.model.api.request.RolePutRequest;
import com.pe.gidtec.servicedesk.roles.rol.model.api.response.RoleResponse;
import com.pe.gidtec.servicedesk.roles.rol.model.entity.RoleEntity;
import com.pe.gidtec.servicedesk.roles.rol.service.RolesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class RolesServiceImpl implements RolesService {

    private final RolesDao rolesDao;

    private final RolesMapper rolesMapper;


    @Override
    public Mono<ResultResponse<RoleResponse>> createRol(RolePostRequest request, CommonHeaders headers) {
        return rolesDao.verifyIfExistsRoleByName(request.getRoleName())
                .flatMap(bool -> {
                   if( Boolean.FALSE.equals(bool)) {
                       return rolesDao.saveRole(rolesMapper.rolePostRequestToEntity(request,headers))
                               .map(response -> ResultResponse.ok(rolesMapper.entityToRolResponse(response)))
                               .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_ROLE));
                   } else {
                       return Mono.just(ResultResponse.error(ResponseStatus.ERROR_ROLE_NAME_ALREADY_EXISTS));
                   }
                });
    }

    @Override
    public Mono<ResultResponse<RoleResponse>> updateRol(RolePutRequest request, CommonHeaders headers) {
        return rolesDao.verifyIfExistsRoleById(request.getRoleId())
                .flatMap( bool -> {
                    if(Boolean.TRUE.equals(bool)) {
                        return rolesDao.verifyIfExistsRoleByName(request.getRoleName())
                                .flatMap( bool2 -> {
                                    if(Boolean.FALSE.equals(bool2)) {
                                        return rolesDao.getRoleById(request.getRoleId())
                                                .map(entity -> buildRequestForUpdate(entity,request,headers))
                                                .flatMap(rolesDao::saveRole)
                                                .map(response -> ResultResponse.ok(rolesMapper.entityToRolResponse(response)))
                                                .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_ROLE));
                                    } else {
                                        return Mono.just(ResultResponse.error(ResponseStatus.ERROR_ROLE_NAME_ALREADY_EXISTS));
                                    }
                                });
                    } else {
                        return Mono.just(ResultResponse.error(ResponseStatus.ERROR_ID_ROLE_NOT_EXISTS));
                    }
                });
    }

    @Override
    public Mono<ResultResponse<RoleResponse>> deleteRol(String roleId, CommonHeaders headers) {
        return rolesDao.verifyIfExistsRoleById(roleId)
                .flatMap( bool -> {
                    if(Boolean.TRUE.equals(bool)) {
                        return rolesDao.getRoleById(roleId)
                                .map(entity -> buildRequestForDelete(entity,headers))
                                .flatMap(rolesDao::saveRole)
                                .map(response -> ResultResponse.ok(RoleResponse.builder().build()))
                                .onErrorReturn(ResultResponse.error(ResponseStatus.ERROR_SAVE_ROLE));
                    } else {
                        return Mono.just(ResultResponse.error(ResponseStatus.ERROR_ID_ROLE_NOT_EXISTS));
                    }
                });
    }

    @Override
    public Mono<ResultResponse<RoleResponse>> getRolById(String roleId) {
        return rolesDao.getRoleById(roleId)
                .map(rolesMapper::entityToRolResponse)
                .map(ResultResponse::ok);
    }

    @Override
    public Mono<ResultResponse<List<RoleResponse>>>  getRoles() {
        return rolesDao.findRolesActive()
                .map(rolesMapper::entityToRolResponse)
                .collectList()
                .map(ResultResponse::ok);
    }

    private RoleEntity buildRequestForUpdate(RoleEntity entity, RolePutRequest request, CommonHeaders headers) {
        entity.setRoleName(request.getRoleName());
        entity.getAudit().setStatusCode(request.getAudit().getStatusCode());
        entity.getAudit().setLastModifiedBy(headers.getUserCode());
        entity.getAudit().setLastModifiedDate(LocalDateTime.now());
        return entity;
    }

    private RoleEntity buildRequestForDelete(RoleEntity entity, CommonHeaders headers) {
        entity.getAudit().setLastModifiedBy(headers.getUserCode());
        entity.getAudit().setLastModifiedDate(LocalDateTime.now());
        entity.getAudit().setDeleted(Boolean.TRUE);
        return entity;
    }
}
