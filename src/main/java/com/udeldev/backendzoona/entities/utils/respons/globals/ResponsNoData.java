package com.udeldev.backendzoona.entities.utils.respons.globals;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponsNoData {
    Integer status;
    Integer code;
    String message;
}
