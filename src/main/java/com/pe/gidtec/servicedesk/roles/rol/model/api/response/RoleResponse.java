package com.pe.gidtec.servicedesk.roles.rol.model.api.response;

import com.pe.gidtec.servicedesk.roles.audit.response.Audit;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name= "RoleResponse")
public class RoleResponse {

    @Schema(name = "roleId",
            description = "Identificador del rol",
            example = "1ab24sof5e1vxc")
    private String roleId;

    @Schema(name = "roleName",
            description = "Nombre del rol",
            example = "ADMIN")
    private String roleName;

    @Schema(name = "audit",
            description = "Objeto de auditoria")
    private Audit audit;

}
