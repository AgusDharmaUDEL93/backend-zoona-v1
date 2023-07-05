package com.udeldev.backendzoona.entities.utils.respons.globals;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponsListData<T> {
    Integer status;
    Integer code;
    String message;
    List<T> data = new ArrayList<T>();
}
