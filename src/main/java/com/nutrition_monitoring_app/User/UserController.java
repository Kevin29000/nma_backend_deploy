package com.nutrition_monitoring_app.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private NutritionalNeedService nutritionalNeedService;

    // Endpoint pour calculer les besoins nutritionnels
    @GetMapping("/{id}/nutrition")
    public ResponseEntity<NutritionCalculations> calculateNutrition(@PathVariable int id) {
        Optional<User> userOptional = userService.getUserProfile(id);

        if (userOptional.isPresent()) {
            NutritionCalculations calculations = nutritionalNeedService.calculateNutrition(userOptional.get());
            return ResponseEntity.ok(calculations);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour l'inscription d'un nouvel utilisateur
    @PostMapping("/create-credentials")
    public ResponseEntity<User> createUserCredentials(@RequestBody UserLogin credentials) {
        if (credentials.getEmail() == null || credentials.getEmail().isEmpty() || 
            credentials.getPassword() == null || credentials.getPassword().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // Mauvaise requête si les champs sont vides
        }

        try {
            User createdUser = userService.createUserCredentials(credentials.getEmail(), credentials.getPassword());
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // Conflit si l'email est déjà utilisé
        }
    }

    // Endpoint pour l'authentification d'un utilisateur
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLogin userLogin) {
        Optional<User> user = userService.login(userLogin.getEmail(), userLogin.getPassword());
        if (user.isPresent()) {
            return new ResponseEntity<>(user.get(),HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // Endpoint pour obtenir le profil utilisateur
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserProfile(@PathVariable int id) {
        Optional<User> user = userService.getUserProfile(id);
        return user.map(ResponseEntity::ok) // Retourne 200 OK avec l'utilisateur
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Endpoint pour mettre à jour le profil utilisateur
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUserProfile(@PathVariable int id, @RequestBody User user) {
        user.setId(id); //////////////////////////////////////////// A modifier ?! Pk set ??
        User updateUser = userService.updateUserProfile(user);
        return ResponseEntity.ok(updateUser);
    }

    @PutMapping("/{id}/update-credentials")
    public ResponseEntity<User> updateCredentials(@PathVariable int id, @RequestBody UpdateCredentialsRequest credentials) {
        Optional<User> userOptional = userService.getUserProfile(id);
        
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Utilisateur non trouvé
        }

        User user = userOptional.get();
        
        // Vérification du mot de passe actuel
        if (!userService.verifyPassword(user, credentials.getCurrentPassword())) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED); // Mot de passe actuel incorrect
        }
        
        // Sauvegarder l'utilisateur mis à jour
        User updatedUser = userService.updateCredentials(id, credentials.getEmail(), credentials.getNewPassword());
        
        return ResponseEntity.ok(updatedUser);
}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
