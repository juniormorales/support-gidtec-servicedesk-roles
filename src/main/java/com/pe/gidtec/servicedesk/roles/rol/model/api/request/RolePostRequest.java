package com.pe.gidtec.servicedesk.roles.rol.model.api.request;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "RolePostRequest")
public class RolePostRequest {

    @NotBlank(message = "El valor de 'roleName' no puede ser vacio.")
    @Schema(name = "roleName", description = "Nombre del rol a crear", example = "ADMIN")
    private String roleName;
}
