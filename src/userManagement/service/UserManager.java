package userManagement.service;

import userManagement.model.User;
import userManagement.tele.telegramNoti;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.ArrayList;

public class UserManager {
    private List<User> users;
    private int currentId;
    private telegramNoti telegramNotifier;

    public UserManager() {
        this.users = new ArrayList<>();
        this.currentId = 1;
        this.telegramNotifier = new telegramNoti(); // Initialize the notifier
    }

    public User addNewUser(String name, String email) {
        User user = new User.Builder()
                .setId(currentId++) // Use currentId for unique ID
                .setName(name) // Set name
                .setEmail(email) // Set email
                .build(); // Build the user with UUID generated in the builder
        users.add(user);

        // Send notification
        telegramNotifier.sendNotification("New user created: Name = " + name + ", Email = " + email);
        return user; // Return the created user
    }

    public Optional<User> findByUUID(UUID uuid) {
        return users.stream()
                .filter(user -> user.getUuid().equals(uuid))
                .findFirst();
    }

    public boolean updateUser(UUID uuid, String name, String email, Boolean isDeleted) {
        Optional<User> userOpt = findByUUID(uuid);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            if (name != null) user.setName(name);
            if (email != null) user.setEmail(email);
            if (isDeleted != null) user.setDeleted(isDeleted);
            return true;
        }
        return false;
    }

    public boolean deleteUser(UUID uuid) {
        return updateUser(uuid, null, null, true);
    }

    public List<User> getActiveUsers(int page, int pageSize) {
        return users.stream()
                .filter(user -> !user.isDeleted())
                .skip((page - 1) * pageSize)
                .limit(pageSize)
                .toList();
    }

    public int getTotalPages(int pageSize) {
        long activeUserCount = users.stream()
                .filter(user -> !user.isDeleted())
                .count();
        return (int) Math.ceil((double) activeUserCount / pageSize);
    }
}