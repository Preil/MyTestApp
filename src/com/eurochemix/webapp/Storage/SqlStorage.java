package com.eurochemix.webapp.Storage;

import com.eurochemix.webapp.WebAppException;
import com.eurochemix.webapp.model.Resume;
import com.eurochemix.webapp.sql.Sql;
import com.eurochemix.webapp.sql.SqlExecutor;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Created by Ilya on 24.03.2016.
 * <p>
 * Этот класс используется работы с базой данных, реализует методы интерфеса IStorage: CRUD (create, read, update, delete)
 * WebAppConfig (конфигурационный класс) инстанциирует SqlStorage с параметрами доступа к базе данных
 * (dbUrl, dbUser, dbPassword), в конструкторе реализуется метод получения соединения с базой данных - через анонимный класс
 * или лямбда выражение, мы реализуем метод getConnection, передаем в драйвер менеджер строку с параметрами доступа к базе
 */
public class SqlStorage implements IStorage {
    public Sql sql;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sql = new Sql(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
//        sql = new Sql(new ConnectionFactory() {
//            @Override
//            public Connection getConnection() throws SQLException {
//                return DriverManager.getConnection(dbUrl,dbUser,dbPassword);
//            }
//        });
    }

    @Override
    public void clear() {
        sql.execute("DELETE FROM resume");
    }

    @Override
    public void save(final Resume r) throws IOException {
        sql.execute("INSERT INTO resume (uuid, full_name, location, home_page) VALUES (?,?,?,?)", ps -> {
            ps.setString(1, r.getUuid());
            ps.setString(2, r.getFullName());
            ps.setString(3, r.getLocation());
            ps.setString(4, r.getHomePage());
            ps.execute();
            return null;
        });

    }

    @Override
    public void update(final Resume r) {
        sql.execute("UPDATE resume SET full_name=?, location=?, home_page=? WHERE uuid=?", ps -> {
            ps.setString(1, r.getFullName());
            ps.setString(2, r.getLocation());
            ps.setString(3, r.getHomePage());
            ps.setString(4, r.getUuid());
            if (ps.executeUpdate() == 0) {
                throw new WebAppException("Resume not found");
            }
            return null;
        });

    }

    @Override
    public Resume load(final String uuid) {
        return sql.execute("SELECT * FROM resume r WHERE r.uuid=?", new SqlExecutor<Resume>() {
            @Override
            public Resume execute(PreparedStatement st) throws SQLException {
                st.setString(1, uuid);
                ResultSet rs = st.executeQuery();
                if (!rs.next()) {
                    throw new WebAppException("Resume " + uuid + " does not exist");
                }
                Resume r = new Resume(uuid,
                        rs.getString("full_name"),
                        rs.getString("location"),
                        rs.getString("home_page"));
                return r;
            }
        });
    }

    @Override
    public void delete(final String uuid) {
        sql.execute("DELETE FROM resume WHERE uuid=?", new SqlExecutor<Void>() {
            @Override
            public Void execute(PreparedStatement ps) throws SQLException {
                ps.setString(1, uuid);
                if (ps.executeUpdate() == 0) {
                    throw new WebAppException("Resume + " + uuid + " does not exist", uuid);
                }
                return null;
            }
        });

    }

    @Override
    public Collection<Resume> getAllSorted() {
        return null;
    }

    @Override
    public int size() {
        return sql.execute("SELECT count(*) FROM resume", new SqlExecutor<Integer>() {
            @Override
            public Integer execute(PreparedStatement ps) throws SQLException {
                ResultSet rs = ps.executeQuery();
                rs.next();
                return rs.getInt(1);
            }
        });
    }
}
