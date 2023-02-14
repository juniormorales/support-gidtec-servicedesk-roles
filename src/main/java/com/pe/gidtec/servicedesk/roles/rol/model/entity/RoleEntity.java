package com.pe.gidtec.servicedesk.roles.rol.model.entity;

import com.pe.gidtec.servicedesk.roles.common.model.entity.AuditEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "roles")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoleEntity {

    @Id
    private String roleId;

    @Field(name = "role_name")
    private String roleName;

    private AuditEntity audit;

}
