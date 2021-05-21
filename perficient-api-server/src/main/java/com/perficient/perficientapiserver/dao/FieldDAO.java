package com.perficient.perficientapiserver.dao;

import com.perficient.perficientapiserver.model.Field;

import java.util.List;

public interface FieldDAO {
    List<Field> getFields();
    void createField(Field field);
}
