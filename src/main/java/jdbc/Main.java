package jdbc;


import java.sql.*;

public class Main {
    public static void main(String[] args) {

//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }

        String url = "jdbc:mysql://localhost:3307/users";
        String login = "tanya";
        String password = "tanya";

        // NOT SAFE
//        try(Connection connection   = DriverManager.getConnection(url, login, password)) {
//            boolean valid = connection.isValid(2);
//            System.out.println("Connected " + valid);
//
//            Statement statement = connection.createStatement();
////            statement.execute("create table products (id int primary key auto_increment, name varchar(255))");
//            statement.execute("insert into products (name) values ('cherry pie')");
//
//            ResultSet resultSet = statement.executeQuery("select * from products");
//
//            while (resultSet.next()){
//                String name = resultSet.getString("name");
//                System.out.println("Name " + name);
//
//            }
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }

        // SAFE
        try {
            Connection connection = DriverManager.getConnection(url, login, password);
            PreparedStatement preparedStatement = connection.prepareStatement("insert into products (name) values (?)");
            preparedStatement.setString(1,"coffee");
            preparedStatement.execute();

            preparedStatement.close();
            connection.close();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}