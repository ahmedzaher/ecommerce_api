package com.ecommerce.ecommerce_api.bootstrap;

import com.ecommerce.ecommerce_api.model.AuthUser;
import com.ecommerce.ecommerce_api.model.Category;
import com.ecommerce.ecommerce_api.model.Item;
import com.ecommerce.ecommerce_api.model.User;
import com.ecommerce.ecommerce_api.service.CartService;
import com.ecommerce.ecommerce_api.service.CategoryService;
import com.ecommerce.ecommerce_api.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataLoader implements CommandLineRunner {

    private CategoryService categoryService;
    private UserService userService;
    private CartService cartService;

    public DataLoader(CategoryService categoryService, UserService userService, CartService cartService) {
        this.categoryService = categoryService;
        this.userService = userService;
        this.cartService = cartService;
    }

    @Override
    public void run(String... args) {
        this.saveUsers();
        this.saveElectronics();
        this.savePhonesAndTablets();
        this.saveCarts();

    }

    private void saveCarts() {
        this.cartService.addItem(2L, 1L);
        this.cartService.addItem(2L, 2L);
        this.cartService.addItem(3L, 3L);
    }

    private void saveUsers() {
        final String password = "$2a$04$07X8/.GeXqaJeQhvUvg1yecyUOBbxDzFk3ZevSTt.q5WT8BTNtlmu"; // pass
        final String userRole = "ROLE_USER";

        AuthUser authUser1 = AuthUser.builder().username("admin")
                .password(password)
                .roles("ROLE_ADMIN")
                .enabled(true)
                .build();
        User user1 = new User("System Admin", authUser1);

        AuthUser authUser2 = AuthUser.builder().username("stephen")
                .password(password)
                .roles(userRole)
                .enabled(true)
                .build();
        User user2 = new User("Stephen Hawking", authUser2);

        AuthUser authUser3 = AuthUser.builder().username("max")
                .password(password)
                .roles(userRole)
                .enabled(true)
                .build();
        User user3 = new User("Max Born", authUser3);

        AuthUser authUser4 = AuthUser.builder().username("albert")
                .password(password)
                .roles(userRole)
                .enabled(true)
                .build();
        User user4 = new User("Albert Einstein", authUser4);

        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
    }

    private void savePhonesAndTablets() {
        Category c1 = Category.builder().name("Phones & Tablets").build();

        Item i1 = Item.builder()
                .name("Samsung Galaxy Note10 Lite")
                .price(8999F)
                .availableQuantity(10)
                .available(true)
                .build();

        Item i2 = Item.builder()
                .name("XIAOMI Redmi Note 8")
                .price(2950F)
                .availableQuantity(10)
                .available(true)
                .build();

        Item i3 = Item.builder()
                .name("Apple iPhone XR with FaceTime - 64GB")
                .price(12700F)
                .availableQuantity(10)
                .available(true)
                .build();

        c1.addItem(i1);
        c1.addItem(i2);
        c1.addItem(i3);

        categoryService.save(c1);
    }

    private void saveElectronics() {
        Category c1 = Category.builder().name("Electronics").build();

        Item i1 = Item.builder()
                .name("ATA 32-inch HD LED Smart Monitor 1")
                .price(1725F)
                .availableQuantity(10)
                .available(true)
                .build();

        Item i2 = Item.builder()
                .name("ATA 55-inch Ultra HD 4K Monitor")
                .price(4535F)
                .availableQuantity(10)
                .available(true)
                .build();

        Item i3 = Item.builder()
                .name("Canon PowerShot SX620 HS Camera - Black")
                .price(3649F)
                .availableQuantity(10)
                .available(true)
                .build();

        c1.addItem(i1);
        c1.addItem(i2);
        c1.addItem(i3);

        categoryService.save(c1);
    }
}
