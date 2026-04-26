package ud8.users.persistance.dao;

import ud8.users.domain.entity.User;

public interface UserDao {
    User findById(int userId);
    User findByEmail(String email);
    int insert(User user);
    int update(User user);
    int delete(int userId);
}
