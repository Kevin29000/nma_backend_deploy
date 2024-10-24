package com.nutrition_monitoring_app.User;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nutrition_monitoring_app.Food.DefaultFood;
import com.nutrition_monitoring_app.Food.DefaultFoodRepository;
import com.nutrition_monitoring_app.Food.Food;
import com.nutrition_monitoring_app.Food.FoodRepository;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

@Service
public class UserService {// Création des méthodes CRUD
    @Autowired // Dit à spring d'injecter le service dans cette variable
    private UserRepository userRepository;

    @Autowired
    private DefaultFoodRepository defaultFoodRepository;

    @Autowired
    private FoodRepository foodRepository;

    private final Argon2 argon2 = Argon2Factory.create();

    // Authentification
    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        //if (user.isPresent() && user.get().getPassword().equals(password)) { // v1 avant hachage
        if (user.isPresent() && argon2.verify(user.get().getPassword(), password.toCharArray())) {
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
        //newUser.setPassword(password); // v1 avant hachage
        char[] passwordChars = password.toCharArray();
        String hashedPassword = argon2.hash(10, 65536, 1, passwordChars);
        newUser.setPassword(hashedPassword);

        User createdUser = userRepository.save(newUser);

        List<DefaultFood> defaultFoods = defaultFoodRepository.findAll();

        for (DefaultFood defaultFood : defaultFoods) {
            Food food = new Food();
            food.setUser(createdUser);
            food.setDefaultFood(defaultFood);
            food.setName(defaultFood.getName());
            food.setCalories(defaultFood.getCalories());
            food.setProteins(defaultFood.getProteins());
            food.setCarbohydrates(defaultFood.getCarbohydrates());
            food.setLipids(defaultFood.getLipids());
            food.setIsDefault(true);

            // Sauvegarder l'aliment dans la base de données
            Food savedFood = foodRepository.save(food);
        }

        return createdUser;
    }

    public Optional<User> getUserProfile(int id) {
        return userRepository.findById(id);
    }

    public User updateCredentials(int id, String email, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEmail(email);
            //user.setPassword(newPassword); // v1 avant hachage
            char[] newPasswordChars = newPassword.toCharArray();
            String hashedPassword = argon2.hash(10, 65536, 1, newPasswordChars);
            user.setPassword(hashedPassword);

            return userRepository.save(user);
        } else {
            throw new RuntimeException("Utilisateur non trouvé avec l'id " + id);
        }
    }

    public boolean verifyPassword(User user, String currentPassword) {
        //return user.getPassword().equals(currentPassword); // v1 avant hachage
        return argon2.verify(user.getPassword(), currentPassword.toCharArray());
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
