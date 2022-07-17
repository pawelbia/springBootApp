package springboot.library;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springboot.library.model.*;
import springboot.library.model.role_permission.AppUserRole;
import springboot.library.model.role_permission.Permission;
import springboot.library.model.role_permission.Role;
import springboot.library.model.role_permission.RolePermission;
import springboot.library.repository.AppUserRepository;
import springboot.library.repository.AuthorRepository;
import springboot.library.repository.BookRepository;
import springboot.library.repository.StudentCardRepository;
import springboot.library.repository.role_permission_rep.*;
import springboot.library.service.AppUserService;
import springboot.library.service.role_permisson_ser.BookAuthorService;
import springboot.library.service.role_permisson_ser.RoleService;

import java.util.Random;

@SpringBootApplication
public class LibraryApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);

    }


    @Bean
    CommandLineRunner commandLineRunner(AppUserRepository appUserRepository,
                                        RoleRepository roleRepository,
                                        BookRepository bookRepository,
                                        BCryptPasswordEncoder bCryptPasswordEncoder,
                                        AppUserRoleRepository appUserRoleRepository,
                                        PermissionRepository permissionRepository,
                                        RolePermissionRepository rolePermissionRepository,
                                        AuthorRepository authorRepository,
                                        StudentCardRepository studentCardRepository,
                                        BookAuthorService bookAuthorService,
                                        AppUserService appUserService) {
        return args -> {

            //Saving roles
            Role role = new Role("ROLE_USER");
            Role role2 = new Role("ROLE_EMPLOYEE");
            Role role3 = new Role("ROLE_ADMIN");
            roleRepository.save(role);
            roleRepository.save(role2);
            roleRepository.save(role3);

            //Creating Users with ROLE_USER
            Faker faker = new Faker();
            for (int i = 0; i < 10; i++) {
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                String email = String.format("%s.%s@gmail.com", firstName, lastName);
                String password = String.format("%s%s", firstName, lastName);

                AppUser appUser = new AppUser(email, password, firstName, lastName, true, true, true, true);
                appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));

                AppUserRole appUserRole = new AppUserRole(appUser.getId(), roleRepository.findRoleByName("ROLE_USER").orElseThrow(() -> new IllegalStateException("role not found")).getId(),
                        appUser, roleRepository.findRoleByName("ROLE_USER").orElseThrow(() -> new IllegalStateException("role not found")));

                appUserRepository.save(appUser);
                appUserRoleRepository.save(appUserRole);
            }

            //Creating Users with ROLE_EMPLOYEE
            for (int i = 0; i < 5; i++) {
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                String email = String.format("%s.%s@gmail.com", firstName, lastName);
                String password = String.format("%s%s", firstName, lastName);

                AppUser appUser = new AppUser(email, password, firstName, lastName, true, true, true, true);
                appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));

                AppUserRole appUserRole = new AppUserRole(appUser.getId(), roleRepository.findRoleByName("ROLE_EMPLOYEE").orElseThrow(() -> new IllegalStateException("role not found")).getId(),
                        appUser, roleRepository.findRoleByName("ROLE_ADMIN").orElseThrow(() -> new IllegalStateException("role not found")));

                appUserRepository.save(appUser);
                appUserRoleRepository.save(appUserRole);
            }

            //Creates SoftCover books
            Random random = new Random();
            for (int i = 0; i < 5; i++) {
                com.github.javafaker.Book book = faker.book();
                springboot.library.model.Book book1 = new springboot.library.model.Book(book.title(), book.publisher(), random.nextInt(100,1000), "SoftCover" );
                bookRepository.save(book1);
            }
            //Creates HardCover books
            for (int i = 0; i < 5; i++) {
                com.github.javafaker.Book book = faker.book();
                springboot.library.model.Book book2 = new springboot.library.model.Book(book.title(), book.publisher(), random.nextInt(100,1000), "HardCover" );
                bookRepository.save(book2);
            }

            //Creates authors
            for (int i = 0; i < 10; i++) {
                String firstName = faker.name().firstName();
                String lastName = faker.name().lastName();
                Author author = new Author(firstName, lastName);
                authorRepository.save(author);

            }

            //Creates Relations Book with author
            bookAuthorService.addAuthorToBook(
                    bookRepository.findById(1L).orElseThrow(() -> new IllegalStateException("book not found")),
                    authorRepository.findById(1L).orElseThrow(() -> new IllegalStateException("author not found"))
            );
            bookAuthorService.addAuthorToBook(
                    bookRepository.findById(2L).orElseThrow(() -> new IllegalStateException("book not found")),
                    authorRepository.findById(2L).orElseThrow(() -> new IllegalStateException("author not found"))
            );
            bookAuthorService.addAuthorToBook(
                    bookRepository.findById(3L).orElseThrow(() -> new IllegalStateException("book not found")),
                    authorRepository.findById(3L).orElseThrow(() -> new IllegalStateException("author not found"))
            );
            bookAuthorService.addAuthorToBook(
                    bookRepository.findById(4L).orElseThrow(() -> new IllegalStateException("book not found")),
                    authorRepository.findById(4L).orElseThrow(() -> new IllegalStateException("author not found"))
            );
            bookAuthorService.addAuthorToBook(
                    bookRepository.findById(5L).orElseThrow(() -> new IllegalStateException("book not found")),
                    authorRepository.findById(5L).orElseThrow(() -> new IllegalStateException("author not found"))
            );
            bookAuthorService.addAuthorToBook(
                    bookRepository.findById(6L).orElseThrow(() -> new IllegalStateException("book not found")),
                    authorRepository.findById(6L).orElseThrow(() -> new IllegalStateException("author not found"))
            );
            bookAuthorService.addAuthorToBook(
                    bookRepository.findById(7L).orElseThrow(() -> new IllegalStateException("book not found")),
                    authorRepository.findById(7L).orElseThrow(() -> new IllegalStateException("author not found"))
            );
            bookAuthorService.addAuthorToBook(
                    bookRepository.findById(8L).orElseThrow(() -> new IllegalStateException("book not found")),
                    authorRepository.findById(8L).orElseThrow(() -> new IllegalStateException("author not found"))
            );
            bookAuthorService.addAuthorToBook(
                    bookRepository.findById(9L).orElseThrow(() -> new IllegalStateException("book not found")),
                    authorRepository.findById(9L).orElseThrow(() -> new IllegalStateException("author not found"))
            );
            bookAuthorService.addAuthorToBook(
                    bookRepository.findById(10L).orElseThrow(() -> new IllegalStateException("book not found")),
                    authorRepository.findById(10L).orElseThrow(() -> new IllegalStateException("author not found"))
            );

            //Add student cards to some users
            StudentCard studentCard1 = new StudentCard(12345, "1Y1G", appUserService.findById(1L));
            studentCardRepository.save(studentCard1);
            StudentCard studentCard2 = new StudentCard(12346, "1Y1G", appUserService.findById(3L));
            studentCardRepository.save(studentCard2);
            StudentCard studentCard3 = new StudentCard(12347, "1Y1G", appUserService.findById(5L));
            studentCardRepository.save(studentCard3);

            StudentCard studentCard4 = new StudentCard(12348, "1Y2G", appUserService.findById(2L));
            studentCardRepository.save(studentCard4);
            StudentCard studentCard5 = new StudentCard(12349, "1Y2G", appUserService.findById(4L));
            studentCardRepository.save(studentCard5);
            StudentCard studentCard6 = new StudentCard(12350, "1Y2G", appUserService.findById(6L));
            studentCardRepository.save(studentCard6);

            //Add permission
            Permission permission1 = new Permission("ROLE:MANAGEMENT", "Permission allows for crud operations on roles");
            Permission permission2 = new Permission("PERMISSION:MANAGEMENT", "Permission allows for crud operations on permissions, also" +
                    "can add/remove permissions for roles");
            permissionRepository.save(permission1);
            permissionRepository.save(permission2);

            //Add permissions to ROLE_ADMIN
            RolePermission rolePermission1 = new RolePermission(3L,1L,role3,permission1);
            RolePermission rolePermission2 = new RolePermission(3L,2L,role3,permission2);

            rolePermissionRepository.save(rolePermission1);
            rolePermissionRepository.save(rolePermission2);


        };
   }
}
