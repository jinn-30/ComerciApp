package models;

public class Products {
    public int barcode;
    public String productName;
    public String trademark;
    public String category;
    public float gross_price;
    public float net_price;
    public int lot;
    public int count;
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public float getGross_price() {
        return gross_price;
    }

    public void setGross_price(float gross_price) {
        this.gross_price = gross_price;
    }

    public float getNet_price() {
        return net_price;
    }

    public void setNet_price(float net_price) {
        this.net_price = net_price;
    }

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }
    
}
