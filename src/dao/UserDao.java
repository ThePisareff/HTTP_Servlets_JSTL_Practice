package dao;

import entity.User;
import lombok.SneakyThrows;
import util.ConnectionManager;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer,User>{

    private static final UserDao INSTANCE = new UserDao();
    private static final String SAVE_SQL = """
            INSERT INTO users (name, birthday, email, password, role, gender)
            VALUES (?,?,?,?,?,?);
            """;

    private UserDao(){}

    public static UserDao getInstance(){return INSTANCE;}

    @Override
    @SneakyThrows
    public User save(User entity) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setObject(1,entity.getName());
            prepareStatement.setObject(2,entity.getBirthday());
            prepareStatement.setObject(3,entity.getEmail());
            prepareStatement.setObject(4,entity.getPassword());
            prepareStatement.setObject(5,entity.getRole().name());
            prepareStatement.setObject(6,entity.getGender().name());

            prepareStatement.executeUpdate();

            var generatedKeys = prepareStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getInt("id"));

            return entity;
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public Optional<User> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }
}
