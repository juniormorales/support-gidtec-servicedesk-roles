package com.pe.gidtec.servicedesk.roles.rol.service;

import com.pe.gidtec.servicedesk.roles.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.roles.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.roles.rol.model.api.request.RolePostRequest;
import com.pe.gidtec.servicedesk.roles.rol.model.api.request.RolePutRequest;
import com.pe.gidtec.servicedesk.roles.rol.model.api.response.RoleResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RolesService {

    Mono<ResultResponse<RoleResponse>> createRol(RolePostRequest request, CommonHeaders headers);

    Mono<ResultResponse<RoleResponse>> updateRol(RolePutRequest request, CommonHeaders headers);

    Mono<ResultResponse<RoleResponse>> deleteRol(String roleId, CommonHeaders headers);

    Mono<ResultResponse<RoleResponse>> getRolById(String roleId);

    Mono<ResultResponse<List<RoleResponse>>> getRoles();

}
