package com.pbl5.helpers.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IMapper<T> {
    T mapRow(ResultSet result) throws SQLException;
}
