package com.pe.gidtec.servicedesk.roles.common.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {

    OK("00", "la solicitud ha tenido éxito."),
    ERROR_SAVE_ROLE("01", "Ocurrió un error al intentar guardar el rol."),
    ERROR_ID_ROLE_NOT_EXISTS("02","El identificador del rol proporcionado no existe."),
    ERROR_GET_ROLE("03","Ocurrió un error al intentar listar los roles"),
    OK_DONT_EXIST_ROLE("04","No se encontró un rol con el identificador enviado"),
    ERROR_ROLE_NAME_ALREADY_EXISTS("05", "El nombre del rol enviado ya está registrado");

    private final String code;
    private final String description;
}
