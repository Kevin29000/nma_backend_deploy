package com.nutrition_monitoring_app.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {// Création des méthodes CRUD
    @Autowired // Dit à spring d'injecter le service dans cette variable
    private UserRepository userRepository;

    // Authentification
    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user; // Authentification réussie
        }
        return Optional.empty(); // Echec de l'authentification
    }

    // Méthode pour l'inscription
    public User createUserCredentials(String email, String password) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email déjà utilisé");
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(password);

        return userRepository.save(newUser);
    }

    public Optional<User> getUserProfile(int id) {
        return userRepository.findById(id);
    }

    public User updateCredentials(int id, String email, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(email);
            user.setPassword(newPassword);

            return userRepository.save(user);
        } else {
            throw new RuntimeException("Utilisateur non trouvé avec l'id " + id);
        }
    }

    public boolean verifyPassword(User user, String currentPassword) {
        return user.getPassword().equals(currentPassword);
    }

    public User updateUserProfile(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    public Optional<User> findUserById(int id) {
        return userRepository.findById(id);
    }
}