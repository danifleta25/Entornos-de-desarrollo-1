package ud8.users.domain.service;

import ud8.common.exception.ResourceNotFoundException;
import ud8.users.domain.entity.User;
import ud8.users.domain.service.UserService;
import ud8.users.persistance.repository.UserRepository;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(int id) throws ResourceNotFoundException {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return user;
    }

    public boolean isEmailValid(String email){
        String pattern = "^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
        return email.matches(pattern);
    }

    @Override
    public boolean create(User user) {
        if (!isEmailValid(user.getEmail()))
            return false;

        if (userRepository.existsByEmail(user.getEmail()))
            return false;

        userRepository.create(user);
        return true;
    }

    @Override
    public boolean update(User user) {
        if (!userRepository.existsById(user.getId())) {
            return false;
        }
        userRepository.update(user);
        return true;
    }

    @Override
    public boolean delete(int id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.delete(id);
        return true;
    }
}
