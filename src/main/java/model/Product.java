package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Product {
    private int id;
    private String name;
    private int price;
    private int stock;
    private static ArrayList<Product> AllProducts = new ArrayList();
    private Product userSelectedProduct;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public ArrayList<Product> getProducts(){
        return AllProducts;
    }

    public Product getUserSelectedProduct() {
        return userSelectedProduct;
    }

    public void setUserSelectedProduct(Product userSelectedProduct) {
        this.userSelectedProduct = userSelectedProduct;
    }

    private void setProducts(int id, String name, int price, int stock){
        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);
        AllProducts.add(product);
    }

    public void readProductFromFile() {
        File name = new File("Items.txt");
        FileReader fReader;
        String linePerLine[];

        try {
            fReader = new FileReader(name);
            BufferedReader line = new BufferedReader(fReader);
            String lineCurrentlyReading = line.readLine();
            while (lineCurrentlyReading != null) {

                // Marshalling
                linePerLine = lineCurrentlyReading.split(",");
                int tempId = Integer.parseInt(linePerLine[0]);
                String tempName = linePerLine[1];
                int tempPrice = Integer.parseInt(linePerLine[2]);
                int tempStock = Integer.parseInt(linePerLine[3]);


                setProducts(tempId, tempName, tempPrice, tempStock);

                lineCurrentlyReading = line.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean productToPurchase(){
        Scanner productToPurchaseSc = new Scanner(System.in);
        int userSelectedId = productToPurchaseSc.nextInt();

        boolean selectedValidId = false;

        for (int i = 0; i < AllProducts.size(); i++) {
            if (userSelectedId == AllProducts.get(i).getId()){
                setUserSelectedProduct(AllProducts.get(i));
                selectedValidId = true;
            }
        }
        return selectedValidId;
    }

    public void updateTextFile() {
        try {
            FileWriter fw = new FileWriter("Items.txt");
            for (int i = 0; i < AllProducts.size(); i++) {
                fw.write(
                        AllProducts.get(i).getId() + "," +
                                AllProducts.get(i).getName() + "," +
                                AllProducts.get(i).getPrice() + "," +
                                AllProducts.get(i).getStock() + "\n"
                );
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Saved and Quitting");
    }
}
