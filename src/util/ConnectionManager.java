package util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;

@UtilityClass // Делает класс final и добавляет private конструктор без параметров
public class ConnectionManager {
    private static final String URL_KEY = "db.url";
    private static final String USERNAME_KEY = "db.user";
    private static final String PASSWORD_KEY = "db.password";

    static {
        loadDrivers();
    }

    private static void loadDrivers() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows // По смыслу оборачивает в try но чуть хитрее
    public static Connection open() {
        return DriverManager.getConnection(PropertiesManager.get(URL_KEY),
                                           PropertiesManager.get(USERNAME_KEY),
                                           PropertiesManager.get(PASSWORD_KEY));

    }
}
