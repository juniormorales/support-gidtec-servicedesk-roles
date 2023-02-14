package com.pe.gidtec.servicedesk.roles.controller;

import com.pe.gidtec.servicedesk.lib.annotation.HttpHeadersMapping;
import com.pe.gidtec.servicedesk.roles.common.model.api.response.ResultResponse;
import com.pe.gidtec.servicedesk.roles.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.roles.rol.model.api.request.RolePostRequest;
import com.pe.gidtec.servicedesk.roles.rol.model.api.request.RolePutRequest;
import com.pe.gidtec.servicedesk.roles.rol.model.api.response.RoleResponse;
import com.pe.gidtec.servicedesk.roles.rol.service.RolesService;
import com.pe.gidtec.servicedesk.roles.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/support/api/roles")
@Tag(name = "Roles", description = "Gestiona la información de los roles")
@Validated
public class RolesController {

    private final RolesService rolesService;

    private final ResponseUtil responseUtil;

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = RoleResponse.class)))
    },
            summary = "Permite obtener una lista de roles",
            method = "GET")
    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ResultResponse<List<RoleResponse>>>> getRoles() {
        return rolesService.getRoles()
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = RoleResponse.class)))
    },
            summary = "Permite obtener un rol mediante su identificador 'roleId'",
            method = "GET")
    @GetMapping(value = "/search/{roleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ResultResponse<RoleResponse>>> getRol(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Parameter(name = "roleId", example = "s58xe8d0x6", description = "Identificador del rol")
            @PathVariable("roleId")
            @NotBlank
            String roleId
    ) {
        return rolesService.getRolById(roleId)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite registrar la información de un rol",
            method = "POST")
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<ResultResponse<RoleResponse>>> create(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Validated
            @RequestBody
                    RolePostRequest request
    ) {
        return rolesService.createRol(request, headers)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite actualizar la información de un rol",
            method = "PUT")
    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public  Mono<ResponseEntity<ResultResponse<RoleResponse>>> update(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Validated
            @RequestBody
                    RolePutRequest request
    ) {
        return rolesService.updateRol(request, headers)
                .map(responseUtil::getResponseEntityStatus);
    }

    @Operation(responses = {
            @ApiResponse(responseCode = "200", description = "Proceso Satisfactorio.",
                    content = @Content(schema = @Schema(implementation = ResultResponse.class)))
    },
            summary = "Permite eliminar un rol del registro",
            method = "DELETE")
    @DeleteMapping(value = "/{roleId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public  Mono<ResponseEntity<ResultResponse<RoleResponse>>> delete(
            @ParameterObject
            @HttpHeadersMapping
                    CommonHeaders headers,
            @Parameter(name = "roleId", example = "s58xe8d0x6", description = "Identificador del rol")
            @PathVariable("roleId")
            @NotBlank
                    String roleId
    ) {
        return rolesService.deleteRol(roleId, headers)
                .map(responseUtil::getResponseEntityStatus);
    }

}
