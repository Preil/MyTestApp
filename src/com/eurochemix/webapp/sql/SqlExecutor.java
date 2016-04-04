package com.eurochemix.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Ilya on 01.04.2016.
 */
public interface SqlExecutor<T>{
    T execute(PreparedStatement ps) throws SQLException;
}
