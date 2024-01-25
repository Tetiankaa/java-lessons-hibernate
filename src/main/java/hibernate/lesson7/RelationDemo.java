package hibernate.lesson7;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class RelationDemo {
    public static void main(String[] args) {
        try (StandardServiceRegistry registryBuilder =  new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build()) {

            Metadata metadata = new MetadataSources(registryBuilder)
                    .addAnnotatedClass(User.class)
//                    .addAnnotatedClass(Card.class)
                    .addAnnotatedClass(Passport.class)
                    .addAnnotatedClass(Book.class)
                    .addAnnotatedClass(Car.class)
                    .getMetadataBuilder()
                    .build();

            try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
                 Session session = sessionFactory.openSession()){

                Transaction transaction = session.beginTransaction();

                // OneToOne Bidirectional
//                User user1 = new User();
//                user1.setName("Marina");
//
//                Passport passport = new Passport();
//                passport.setSeries("PPP555");
//
//                user1.setPassport(passport);
//                session.persist(user1);

                // OneToOne Unidirectional
//                Passport passport = new Passport();
//                passport.setSeries("128963");
//
//                User user1 = new User();
//                user1.setName("Pavlo");
//                user1.setPassport(passport);
//
//                session.persist(passport);
//                session.persist(user1);

//                List<User> list = session.createNativeQuery("select * from users", User.class).list();
//                System.out.println("products selected");
//                System.out.println(list);

                //ManyToOne - Unidirectional

//                User user = new User();
//                user.setName("Miroslava");
//
//                Book book = new Book();
//                book.setBookName("Harry Potter");
//                book.setUser(user);
//
//                session.persist(book);

//                User user = new User();
//                user.setName("Boris");
//
//                List<Book> books = new ArrayList<>();
//               Book book = new Book();
//               Book book2 = new Book();
//               Book book3 = new Book();
//               book.setBookName("book1");
//               book2.setBookName("fnjdn");
//
//               books.add(book);
//               books.add(book2);
//               books.add(book3);

//                user.setBooks(books);
//
//                session.persist(user);

//                List<User> list = session.createNativeQuery("select * from users", User.class).list();
//                System.out.println(list);
//                list.stream()
//                                .filter(user -> user.getBooks() != null)
//                                .flatMap(u -> u.getBooks().stream())
//                                .forEach(System.out::println);

                //ManyToMany

                Car car1 = new Car();
                Car car2 = new Car();
                Car car3 = new Car();
                car1.setBrand("Mazda");
                car2.setBrand("Honda");
                car3.setBrand("Audi");

                session.persist(car1);
                session.persist(car2);
                session.persist(car3);

        User user = new User();
        User user1 = new User();
        user.setCars(List.of(car2,car3));
        user1.setCars(List.of(car2,car3,car1));

        session.persist(user1);
        session.persist(user);
                transaction.commit();




            }
        }
    }
}
