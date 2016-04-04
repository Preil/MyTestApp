package com.eurochemix.webapp.sql;

import com.eurochemix.webapp.WebAppException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Ilya on 31.03.2016.
 * <p>
 * Этот класс используется для выполнение запросов к базе, инициализации экземпляр получает соединение к базе,
 * через реализацию метода интерфейса ConnectionFactory через ананимный класс(или лямбда выражение)
 */
public class Sql {
    private final ConnectionFactory connectionFactory;

    public Sql(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public void execute(String sql) {
        execute(sql, new SqlExecutor<Void>() {
            @Override
            public Void execute(PreparedStatement ps) throws SQLException {
                ps.execute();
                return null;
            }
        });
    }

    public <T> T execute(String sql, SqlExecutor<T> executor) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            return executor.execute(ps);
        } catch (SQLException e) {
            throw new WebAppException("SQL failed: " + sql, e);
        }
    }
}
