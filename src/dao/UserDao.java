package dao;

import entity.Gender;
import entity.Role;
import entity.User;
import lombok.SneakyThrows;
import util.ConnectionManager;
import util.LocalDateFormatter;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User> {

    private static final UserDao INSTANCE = new UserDao();
    private static final String SAVE_SQL = """
            INSERT INTO users (name, birthday, email, password, role, gender,image)
            VALUES (?,?,?,?,?,?,?);
            """;

    private static final String GET_BY_ID_AND_EMAIL = """
            SELECT *
            FROM users
            WHERE email = ? AND password = ?;
            """;

    private UserDao() {
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }

    @SneakyThrows
    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(GET_BY_ID_AND_EMAIL)) {

            prepareStatement.setObject(1,email);
            prepareStatement.setObject(2,password);

            var resultSet = prepareStatement.executeQuery();
            User user = null;
            if (resultSet.next()){
                user = buildUser(resultSet);
            }
            return Optional.ofNullable(user);
        }
    }


    @Override
    @SneakyThrows
    public User save(User entity) {
        try (var connection = ConnectionManager.open();
             var prepareStatement = connection.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            prepareStatement.setObject(1, entity.getName());
            prepareStatement.setObject(2, entity.getBirthday());
            prepareStatement.setObject(3, entity.getEmail());
            prepareStatement.setObject(4, entity.getPassword());
            prepareStatement.setObject(5, entity.getRole().name());
            prepareStatement.setObject(6, entity.getGender().name());
            prepareStatement.setObject(7, entity.getImage());


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

    private static User buildUser(ResultSet resultSet) throws SQLException {
        User user;
        user = User.builder()
                .id(resultSet.getObject("id",Integer.class))
                .name(resultSet.getObject("name",String.class))
                .email(resultSet.getObject("email",String.class))
                .password(resultSet.getObject("password",String.class))
                .birthday(resultSet.getObject("birthday",Date.class).toLocalDate())
                .image(resultSet.getObject("image",String.class))
                .role(Role.valueOf(resultSet.getObject("role",String.class)))
                .gender(Gender.valueOf(resultSet.getObject("gender",String.class)))
                .build();
        return user;
    }

}
