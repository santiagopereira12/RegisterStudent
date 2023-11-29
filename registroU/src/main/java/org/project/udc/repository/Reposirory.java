package org.project.udc.repository;

import java.sql.SQLException;
import java.util.List;

public interface Reposirory <T>{
    List<T> findAll() throws SQLException;
    T getById(Integer id) throws SQLException;
    void save(T t) throws SQLException;
    void delete(Integer id) throws SQLException;
}
