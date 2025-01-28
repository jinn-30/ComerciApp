package models;

public class Trademark {
    
    public int trademark_code;
    public String name;
    
    //Getter and Setter
    public int getTrademark_code() {
        return trademark_code;
    }

    public void setTrademark_code(int trademark_code) {
        this.trademark_code = trademark_code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
