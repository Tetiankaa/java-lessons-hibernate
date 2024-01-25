package hibernate.lesson7;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Table(name = "users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    @EqualsAndHashCode.Include
     int id;

    @ToString.Include
    @Column(name = "name")
     String name;

     @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     @JoinColumn(name = "passport_id")
     Passport passport;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "books_user",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
     List<Book> books;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
                    name = "cars_users",
                    joinColumns = @JoinColumn(name = "user_id"),
                    inverseJoinColumns = @JoinColumn(name="car_id")
            )
    List<Car> cars;

//     @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
//     List<Book> books;

//     public void setBooks(List<Book> books){
//         this.books = books;
//         books.forEach(book -> book.setUser(this));
//     }

}
