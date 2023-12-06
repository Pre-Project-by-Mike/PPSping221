package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 v Создайте соединение к своей базе данных и схему. Запустите приложение. Проверьте, что оно полностью работает.

 V Создайте сущность Car с полями String model и int series, на которую будет ссылаться User с помощью связи one-to-one.

 V Добавьте этот класс в настройки hibernate.

 V Создайте несколько пользователей с машинами, добавьте их в базу данных, вытащите обратно.

 V В сервис добавьте метод, который с помощью hql-запроса будет доставать юзера, владеющего машиной по ее модели и серии.

 **/

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      User user1 = new User("User1", "Lastname1", "user1@mail.ru");
      User user2 = new User("User2", "Lastname2", "user2@mail.ru");
      User user3 = new User("User3", "Lastname3", "user3@mail.ru");

      user1.setCar(new Car("BMW", 7));
      user2.setCar(new Car("Mercedes", 124));
      user3.setCar(new Car("Mercedes", 123));

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("carId = " + user.getCar().getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
      }

      System.out.println(userService.getUser("BMW", 7));
      context.close();
   }
}
