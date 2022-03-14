package com.itchenyang.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public enum FlagToValueEnum {

    T_MODEL_TYPE("type","t_model_type"),
    T_MODEL_NAME("name","t_model_name");

    private String flag;
    private String tableName;

    public static String getTableNameByFlag(String flag) {
        for (FlagToValueEnum flagEnum :values()){
            if (flagEnum.getFlag().equals(flag)) {
                return flagEnum.getTableName();
            }
        }
        return null;
    }
}
