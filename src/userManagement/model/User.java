package userManagement.model;
import java.util.UUID;

public class User {
    private int id;
    private UUID uuid;
    private String name;
    private String email;
    boolean isDeleted;

    private User(Builder builder) {
        this.id = builder.id;
        this.uuid = builder.uuid;
        this.name = builder.name;
        this.email = builder.email;
        this.isDeleted = builder.isDeleted;
    }

    // Getters
    public int getId() { return id; }
    public UUID getUuid() { return uuid; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public boolean isDeleted() { return isDeleted; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setDeleted(boolean deleted) { isDeleted = deleted; }

    public static class Builder {
        private int id;
        private UUID uuid;
        private String name;
        private String email;
        private boolean isDeleted;

        public Builder() {
            this.uuid = UUID.randomUUID();
            this.isDeleted = false;
        }

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return String.format("| %-5d | %-36s | %-20s | %-30s |",
                id, uuid, name, email);
    }
}