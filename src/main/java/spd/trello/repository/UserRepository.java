package spd.trello.repository;

import spd.trello.domain.User;
import spd.trello.repository.mapper.UserMapper;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepository implements IRepository<User> {

    private DataSource dataSource;
    private final String saveSTMT = "insert into users(id, created_date, first_name, last_name, email) values (?,?,?,?,?)";
    private final String findByIDSTMT = "SELECT * FROM users WHERE id=?";
    private final String findAllByIDSTMT = "SELECT * FROM users";
    private final String updateSTMT = "UPDATE users SET updated_date=?, first_name=?, last_name=?, email=? WHERE id =?";
    private final String deleteByIDSTMT = "DELETE FROM users WHERE id=? ";
    private final String deleteAllSTMT = "TRUNCATE TABLE users";

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public UserRepository() {

    }

    @Override
    public User findByID(UUID id) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(findByIDSTMT)) {
            stmt.setObject(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                UserMapper userMapper = new UserMapper();
                return userMapper.extractFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new IllegalStateException("UserRepository::findByID failed");
        }
        throw new IllegalStateException("User with ID: " + id.toString() + "doesn't exist");
    }

    @Override
    public List<User> findAll() {
        List<User> result = new ArrayList<>();
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(findAllByIDSTMT)) {
            ResultSet resultSet;
            resultSet = stmt.executeQuery();
            UserMapper userMapper = new UserMapper();
            while (resultSet.next()) {
                result.add(userMapper.extractFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new IllegalStateException("UserRepository::findALL failed");
        }
        return result;
    }

    @Override
    public User create(User entity) {

        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(saveSTMT)) {
            stmt.setObject(1, entity.getId());
            stmt.setTimestamp(2, Timestamp.valueOf(entity.getCreatedDate()));
            stmt.setString(3, entity.getFirstName());
            stmt.setString(4, entity.getLastName());
            stmt.setString(5, entity.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException("User creation failed", e);
        }
        return entity;
    }

    @Override
    public User update(User entity) {
        User updatedUser = null;
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(updateSTMT)) {
            stmt.setTimestamp(1, Timestamp.valueOf(entity.getUpdatedDate()));
            stmt.setString(2, entity.getFirstName());
            stmt.setString(3, entity.getLastName());
            stmt.setString(4, entity.getEmail());
            stmt.setObject(5, entity.getId());
            stmt.executeUpdate();
            updatedUser = findByID(entity.getId());
        } catch (SQLException e) {
            throw new IllegalStateException("User updating failed", e);
        }
        return updatedUser;
    }

    @Override
    public boolean deleteByID(UUID id) {
        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(deleteByIDSTMT)) {
            stmt.setObject(1, id);
            stmt.executeQuery();

        } catch (SQLException e) {
            throw new IllegalStateException("UserRepository::deleteByID failed");
        }
        return true;
    }

    @Override
    public boolean deleteAll() {

        try (PreparedStatement stmt = dataSource.getConnection().prepareStatement(deleteAllSTMT)) {
            stmt.executeQuery();

        } catch (SQLException e) {
            throw new IllegalStateException("UserRepository::deleteAll failed");
        }
        return true;
    }
}
