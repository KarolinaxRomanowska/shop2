package database;

import model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ProductDB {
    public final List<Product> products = new ArrayList<>();
    private final String DB_FILE = "db.txt";
    public ProductDB(){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(DB_FILE));
            String line;
            while ((line=reader.readLine())!=null) {
            Product product = convertDataToProduct(line);
            if (product!=null){
                this.products.add(product);
            }
            }
            reader.close();
        } catch (IOException e){
            System.out.println("Plik nie działa");
        }
    }

    public boolean buyProduct(Integer productCode, Integer ilosc){
        for(Product element : this.products){
            if(element.getProductCode().equals(productCode) && element.getQuantity()>=ilosc && element.getQuantity()>0){
                    element.setQuantity(((element.getQuantity())-ilosc));
                    return true;
            }
        }
        return false;
    }

    /*public void addProduct(Product product){
        Product[] newProducts = new Product[this.products.length+1];
        for (int i =0; i<this.products.length;i++){
            newProducts[i]=this.products[i];
        }
        newProducts[newProducts.length-1]=product;
        this.products = newProducts;
    }*/

    public void addProduct(Product product){
        this.products.add(product);
    }

    public void changeQuantityProduct(int productCode){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ilość");
        int ilosc = scanner.nextInt();
        for (Product element : products){
            if(element.getProductCode().equals(productCode) && ilosc>0){
                element.setQuantity(element.getQuantity() + ilosc);
                break;
            }
        }
    }
    public List<Product> getProducts() {
        return products;
    }

    public void persistToFile() {
        try{
        BufferedWriter writer = new BufferedWriter
                (new FileWriter(this.DB_FILE,true));
        //boolean flag = false;
        for (Product product:products) {
            //if(flag){
                writer.newLine();
            //}
            //flag=true;
            writer.write(product.convertToData());
        }
        writer.close();
        } catch (IOException e){
            System.out.println("Coś się zepsuło podczas zapisu.");
            e.printStackTrace();
        }
    }

    private Product convertDataToProduct(String data) {
        String[] productData = data.split(";");
        if (productData[0].equals("Product")) {
            String[] productData2 = new String[4];
            productData2[0]=productData[1];
            productData2[1]=productData[2];
            productData2[2]=productData[3];
            productData2[3]=productData[4];
            return new Product(productData2);
        }
        return null;
    }
}
