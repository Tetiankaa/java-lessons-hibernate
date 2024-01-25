package hibernate.lesson7;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Table(name = "books")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @ToString.Include
    int id;

    @ToString.Include
    @EqualsAndHashCode.Include
    @Column(name = "book_name")
    String bookName;

//    @ManyToOne()
//    @JoinColumn(name = "user_id")
//    User user;

    @ManyToOne
    @JoinTable(
                    name = "books_user",
                    joinColumns = @JoinColumn(name = "book_id"),
                    inverseJoinColumns = @JoinColumn(name = "user_id")
            )
    User user;



    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
