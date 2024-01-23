package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class HibernateDemo {
    public static void main(String[] args) {

        try (StandardServiceRegistry registryBuilder =  new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build()) {

            Metadata metadata = new MetadataSources(registryBuilder)
                    .addAnnotatedClass(Product.class)
                    .getMetadataBuilder()
                    .build();

                try (SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
                     Session session = sessionFactory.openSession()){
//                        session.beginTransaction();
                    Transaction transaction = session.beginTransaction();
                    Product product = new Product();
                    product.setName("cake");

                    session.persist(product);
                    transaction.commit();

//                    session.getTransaction().commit();
                    List<Product> list = session.createNativeQuery("select * from productss", Product.class).list();
                    System.out.println(list);
                }
        }
    }
}
