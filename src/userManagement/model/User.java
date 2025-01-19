package userManagement.model;

import java.util.Objects;
import java.util.UUID;

public class User {
    private final int id;
    private final UUID uuid;
    private String name;
    private String email;
    private boolean isDeleted;

    public User(Builder builder) {
        this.id = builder.id;
        this.uuid = builder.uuid;
        this.name = builder.name;
        this.email = builder.email;
        this.isDeleted = builder.isDeleted;
    }

    // Getters
    public int getId() {
        return id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    // Setters (only for mutable fields)
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        this.email = email;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public static class Builder {
        private int id;
        private UUID uuid;
        private String name;
        private String email;
        private boolean isDeleted;

        public Builder() {
            this.uuid = UUID.randomUUID();
            this.isDeleted = false; // Default value
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            if (email == null || email.trim().isEmpty()) {
                throw new IllegalArgumentException("Email cannot be null or empty");
            }
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public String toString() {
        return String.format("| %-5d | %-36s | %-20s | %-30s |",
                id, uuid, name, email);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof User)) return false;
        User other = (User) obj;
        return id == other.id && uuid.equals(other.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uuid);
    }
}