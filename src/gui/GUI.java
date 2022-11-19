package gui;

import database.ProductDB;
import database.UserDB;
import model.Product;
import model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class GUI {

    public static final BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));
    public static void showMenu1() {
        System.out.println("1. Rejestracja");
        System.out.println("2. Zaloguj się");
    }

    public static void showMenu2() {
        System.out.println("1. Wyświetl listę produktów");
        System.out.println("2. Kup produkt");
        System.out.println("3. Dodaj produkt");
        System.out.println("4. Zmień rolę użytkownikowi");
        System.out.println("5. Exit");
    }

    public static void showProductsList(List<Product> products){
        for (Product element : products){
            if(element.getQuantity()>0){
            System.out.println(element.getProductCode()+" "+ element.getName()+" "+ element.getQuantity()+" "+ element.getPrice());
            }
        }
    }
    public static User readLoginAndPassword() throws IOException {

        System.out.println("Login:");
        String login = reader.readLine();
        System.out.println("Password:");
        String password = reader.readLine();
        return new User (login,password);
    }

    public static void addProduct (ProductDB productDB) throws IOException{

        System.out.println("Kod produktu:");
        int code = Integer.parseInt(reader.readLine());

        if (code > productDB.products.size()){
            Product newproduct = new Product();
            newproduct.setProductCode(code);
            System.out.println("Nazwa: ");
            String name = reader.readLine();
            newproduct.setName(name);
            System.out.println("Cena: ");
            newproduct.setPrice(Double.parseDouble(reader.readLine()));
            System.out.println("Ilość: ");
            newproduct.setQuantity(Integer.parseInt(reader.readLine()));
            productDB.addProduct(newproduct);
            System.out.println("Product added!!");
        }
        else {
            productDB.changeQuantityProduct(code);
                }
            }
    public static void addUser(UserDB userDB) throws IOException{

        System.out.println("Nadaj login:");
        String log = reader.readLine();

        if (userDB.findUserByLogin(log) == null) {
            User newUser = new User();
            newUser.setLogin(log);
            System.out.println("Nadaj hasło: ");
            String pass = reader.readLine();
            newUser.setPassword(pass+Authenticator.seed);
            newUser.setRole(User.Role.USER);
            userDB.addUser(log,newUser);
            System.out.println("Konto zostało założone !");

        }
        else {
            System.out.println("Taki login już istnieje!");
            addUser(userDB);
        }
    }

    public static void changeRole(UserDB userDB) throws IOException{

        for(int i =0;i<3;i++){
        System.out.println("Podaj login użytkownika, któremu chcesz nadać rolę ADMINA:");
        String log = reader.readLine();
        User userFromDB = userDB.findUserByLogin(log);
        if(userFromDB !=null){
            userFromDB.setRole(User.Role.ADMIN);
            userDB.persistToFile();
            break;
        }
            System.out.println("Taki login nie istnieje!");
    }}
        }
