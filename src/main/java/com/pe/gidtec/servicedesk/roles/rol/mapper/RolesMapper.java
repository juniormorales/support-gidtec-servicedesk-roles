package com.pe.gidtec.servicedesk.roles.rol.mapper;

import com.pe.gidtec.servicedesk.roles.common.model.header.CommonHeaders;
import com.pe.gidtec.servicedesk.roles.common.util.MappingHelper;
import com.pe.gidtec.servicedesk.roles.rol.model.api.request.RolePostRequest;
import com.pe.gidtec.servicedesk.roles.rol.model.api.response.RoleResponse;
import com.pe.gidtec.servicedesk.roles.rol.model.entity.RoleEntity;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring",
        uses = {MappingHelper.class},
        builder = @Builder(disableBuilder = true)
)
public interface RolesMapper {

    @Mapping(target = "audit", expression = "java(null)")
    @Mapping(target = "audit.status.description", source = "entity", qualifiedByName = "getAuditStatusDescriptionFromRole")
    @Mapping(target = "audit.status.code", source = "entity.audit.statusCode")
    @Mapping(target = "audit.deleted", source = "entity.audit.deleted")
    @Mapping(target = "audit.createdDate", source = "entity.audit.createdDate")
    @Mapping(target = "audit.createdBy", source = "entity.audit.createdBy")
    @Mapping(target = "audit.lastModifiedDate", source = "entity.audit.lastModifiedDate")
    @Mapping(target = "audit.lastModifiedBy", source = "entity.audit.lastModifiedBy")
    @Mapping(target = "roleId", source = "entity.roleId")
    @Mapping(target = "roleName", source = "entity.roleName")
    RoleResponse entityToRolResponse(RoleEntity entity);

    @Mapping(target = "audit.statusCode", constant = "AC")
    @Mapping(target = "audit.createdBy", source = "headers.userCode")
    @Mapping(target = "audit.createdDate", expression = "java(getLocalDateTime())")
    @Mapping(target = "audit.deleted", constant = "false")
    @Mapping(target = "roleName", source = "request.roleName")
    RoleEntity rolePostRequestToEntity(RolePostRequest request, CommonHeaders headers);

    default LocalDateTime getLocalDateTime() { return LocalDateTime.now(); }

}
