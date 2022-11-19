package database;

import model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDB {
    private final Map<String, User> users = new HashMap<>();
    private final String DB_FILE = "db.txt";
    public UserDB(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DB_FILE));
            String line;
            while ((line=reader.readLine())!=null) {
                String[] params = line.split(";");
                if (params[0].equals("User")){
                    User user = new User(params[1],params[2], User.Role.valueOf(params[3]));
                    if (user!=null){
                        this.users.put(params[0],user);
                    }
                }
            }
            reader.close();
        } catch (IOException e){
            System.out.println("Plik nie działa");
        }
    }
    public void addUser(String login, User user){
        this.users.put(login,user);
    }
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
        /*List<User> temp = new ArrayList<>();
        temp.addAll(users.values());
        return temp;*/
    }

    /*public User findUserByLogin(String login){
        this.users.get(login);){
            if (user.getLogin().equals(login)){
                return user;
            }
        }
        return null;
    }*/
    public User findUserByLogin(String login){
        return this.users.get(login);
    }

    public void persistToFile() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(this.DB_FILE, true));
            //boolean flag = false;
            for (User user : this.users.values()){
                //if(flag){
                    writer.newLine();
                //}
                //flag=true;
               writer.write(user.convertToData());
            }
            writer.close();
        } catch (IOException e){
            System.out.println("Coś się zepsuło podczas zapisu");
            e.printStackTrace();
        }
    }
}
