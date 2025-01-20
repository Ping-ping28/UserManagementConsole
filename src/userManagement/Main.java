package userManagement;

import userManagement.controller.userControl;
import userManagement.model.User;
import userManagement.service.UserManager;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        User model = new User();
        UserManager service = new UserManager();
        userControl controller = new userControl();
        controller.start();
    }

}