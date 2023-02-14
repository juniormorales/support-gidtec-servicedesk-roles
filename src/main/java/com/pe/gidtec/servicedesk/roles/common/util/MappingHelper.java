package com.pe.gidtec.servicedesk.roles.common.util;

import com.pe.gidtec.servicedesk.roles.config.AuditProperties;
import com.pe.gidtec.servicedesk.roles.rol.model.entity.RoleEntity;
import lombok.AllArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MappingHelper {

    private final AuditProperties properties;

    @Named("getAuditStatusDescriptionFromRole")
    public String getAuditStatusDescriptionFromRole(RoleEntity entity) {
        return properties.getStatusCode().get(entity.getAudit().getStatusCode());
    }
}
