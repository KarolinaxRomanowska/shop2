package core;

import database.ProductDB;
import database.UserDB;
import gui.Authenticator;
import gui.GUI;
import model.User;

import java.io.IOException;


public class Engine {
    public static void start() throws IOException {
        final ProductDB productDB = new ProductDB();
        final UserDB userDB = new UserDB();

        GUI.showMenu1();
            switch (GUI.reader.readLine()){
                case "1":
                    GUI.addUser(userDB);
                    userDB.persistToFile();
                    start();
                case "2":
                    boolean isWorking = Authenticator.authenticate(userDB);
                    while(isWorking) {
                        GUI.showMenu2();
                        switch(GUI.reader.readLine()) {
                            case "1":
                                GUI.showProductsList(productDB.getProducts());
                                break;
                            case "2":
                                System.out.println("Product Code:");
                                String code = GUI.reader.readLine();
                                System.out.println("Ilość: ");
                                String ilosc = GUI.reader.readLine();
                                if(productDB.buyProduct(Integer.parseInt(code),Integer.parseInt(ilosc))) {
                                    System.out.println("You have buy this product !!!");
                                    break;
                                } else {
                                    System.out.println("Buy error !!");
                                    break;
                                }
                                // break;
                            case "5":
                                GUI.reader.close();
                                productDB.persistToFile();
                                userDB.persistToFile();
                                isWorking = false;
                                break;
                            case "3":
                                if(Authenticator.loggedUser.getRole() == User.Role.ADMIN) {
                                    GUI.addProduct(productDB);
                                    break;
                                }
                                else{
                                    System.out.println("Brak dostępu");
                                    break;
                                }
                            case "4":
                                if(Authenticator.loggedUser.getRole() == User.Role.ADMIN) {
                                    GUI.changeRole(userDB);
                                    break;
                                }
                                else{
                                    System.out.println("Brak dostępu");
                                    break;
                                }
                            default:
                                System.out.println("Wrong choose !!");
                                break;
                        }
                    }
            }


    }
}
