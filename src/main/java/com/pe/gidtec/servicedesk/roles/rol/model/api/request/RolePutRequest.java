package com.pe.gidtec.servicedesk.roles.rol.model.api.request;

import com.pe.gidtec.servicedesk.roles.audit.request.AuditPut;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "RolePutRequest")
public class RolePutRequest {

    @NotBlank(message = "El valor de 'roleId' no puede ser vacio.")
    @Schema(name = "roleId",
            description = "Identificador del rol a modificar",
            example = "1ab24sof5e1vxc")
    private String roleId;

    @NotBlank(message = "El valor de 'roleName' no puede ser vacio.")
    @Schema(name = "roleName",
            description = "Nombre del rol a modificar",
            example = "ADMIN")
    private String roleName;

    @NotNull(message = "El valor de 'audit' no puede ser nulo.")
    @Schema(name = "audit",
            description = "Datos de auditoria")
    private AuditPut audit;

}
