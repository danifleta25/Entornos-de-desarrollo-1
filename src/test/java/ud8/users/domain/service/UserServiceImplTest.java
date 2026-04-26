package ud8.users.domain.service;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ud8.common.exception.ResourceNotFoundException;
import ud8.users.domain.entity.User;
import ud8.users.persistance.repository.UserRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private final static User USER = new User(1, "dani", "danifleta25@gmail.com");


    @Nested
    class FindById {
        @Test
        void givenExistingUser_whenFindById_thenReturnUser() throws ResourceNotFoundException {
            when(userRepository.findById(1)).thenReturn(USER);

            User userResult = userService.findById(1);

            assertEquals(USER, userResult);

            verify(userRepository).findById(1);
        }

        @Test
        void fivenNonExistingId_shouldRaiseResourceNotFound(){
            when(userRepository.findById(6)).thenReturn(null);

            assertThrows(ResourceNotFoundException.class,() -> userService.findById(6));
        }
    }

    @Nested
    class CreateUser {
        @Test
        void givenNewUser_shouldCreateUser() {
            User newUser = new User(2, "Jose", "jose@gmail.com");
            when(userRepository.existsByEmail(newUser.getEmail())).thenReturn(false);

            boolean created = userService.create(newUser);

            assertAll(
                    () -> assertTrue(created),
                    () -> verify(userRepository).create(newUser)
            );
        }

        @Test
        void givenInvalidEmail_shouldNotCreateUser(){
            User newUser = new User(9, "Vicente","@josefran@.asho@com");

            boolean created = userService.create(newUser);

            assertFalse(created);
            verify(userRepository, never()).create(newUser);
        }

        @Test
        void givenExistingEmail_shouldNotCreateUser() {
            String email = USER.getEmail();
            when(userRepository.existsByEmail(email)).thenReturn(true);

            boolean created = userService.create(USER);
            assertAll(
                    () -> assertFalse(created),
                    () -> verify(userRepository, never()).create(USER)
            );
        }
    }

    @Nested
    class UpdateUser {

        @Test
        void givenNewUser_ShouldNotUpdateUser(){
            User newUser = new User(2, "Jose", "jose@gmail.com");

            when(userRepository.existsById(newUser.getId())).thenReturn(false);

            boolean updated = userService.update(newUser);
            assertAll(
                    () -> assertFalse(updated),
                    () -> verify(userRepository, never()).update(newUser)
            );
        }

        @Test
        void givenExistingEmail_ShouldUpdateUser(){

            when(userRepository.existsById(USER.getId())).thenReturn(true);

            boolean updated = userService.update(USER);
            assertAll(
                    () -> assertTrue(updated),
                    () -> verify(userRepository).update(USER)
            );
        }


    }

    @Nested
    class DeleteUser {

        @Test
        void givenExistingId_ShouldDeleteUser(){

            when(userRepository.existsById(USER.getId())).thenReturn(true);

            boolean deleted = userService.delete(USER.getId());
            assertAll(
                    () -> assertTrue(deleted),
                    () -> verify(userRepository).delete(USER.getId())
            );
        }

        @Test
        void givenNonExistingId_ShouldNotDeleteUser(){
            when(userRepository.existsById(19)).thenReturn(false);

            boolean deleted = userService.delete(19);
            assertAll(
                    () -> assertFalse(deleted),
                    () -> verify(userRepository, never()).delete(19)
            );
        }




    }

    }