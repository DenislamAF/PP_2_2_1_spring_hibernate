package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      for (int i = 1; i < 5; i++) {
         User user = new User("User" + i, "Lastname" + i, "user" + i + "@mail.ru");
         Car car = new Car("Car" + i, i);
         user.setCar(car);
         userService.add(user);
      }

      List<User> users = userService.listUsers();
      for (User u : users) {
         System.out.println("Id = "+u.getId());
         System.out.println("First Name = "+u.getFirstName());
         System.out.println("Last Name = "+u.getLastName());
         System.out.println("Email = "+u.getEmail());
         System.out.println("Car model = "+u.getCar().getModel());
         System.out.println("Series = "+u.getCar().getSeries());
         System.out.println();
      }

      System.out.println(userService.getUserWithCar("Car3", 3).getFirstName());

      context.close();
   }
}
