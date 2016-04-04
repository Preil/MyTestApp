package com.eurochemix.webapp.sql;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Ilya on 31.03.2016.
 *
 * Этот интерфейс объявлет метод получить доступ к базе,
 *
 */
public interface ConnectionFactory {
    Connection getConnection() throws SQLException;

}
