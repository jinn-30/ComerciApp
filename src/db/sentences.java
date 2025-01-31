package db;

import models.User;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import models.Category;
import models.Products;
import models.Trademark;

public class sentences {

    private DatabaseConnection dbSentences;

    public sentences() {
        this.dbSentences = new DatabaseConnection();
    }

    
    //VENTANA LOGIN
    public boolean validateUser(String username, String password) {
        try {
            String sql = "SELECT * FROM usuarios WHERE user = ? AND password = ?";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            
            boolean isValid = resultSet.next();
            resultSet.close();
            statement.close();
            return isValid;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //VENTANA SIGNIN
    public boolean registerUser(User user) {
        try {
            String sql = "INSERT INTO usuarios (id, name, user, password, rol, photo) VALUES (?, ?, ?, ?, ?, ?)";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, user.id);
            statement.setString(2, user.name);
            statement.setString(3, user.username);
            statement.setString(4, user.password);
            statement.setString(5, user.role);
            statement.setBytes(6, user.photo);

            statement.executeUpdate();
            statement.close();
            return true;

        } catch (Exception e) {
            //Mesajes de error
            e.printStackTrace();
            return false;
        }
    }

    //VENTANA TRADEMARK
    //Agregar marca
    public boolean addTrademark(Trademark trademark) {
        try {
            String sql = "INSERT INTO marcas (trademark_code, name) VALUES (?, ?)";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, trademark.trademark_code);
            statement.setString(2, trademark.name);

            statement.executeUpdate();
            statement.close();

            return true;
        } catch (Exception e) {
            return false;
            //Mensajes de error
        }
    }

    //Carga en el combo box
    public List<String> nameTrademask() {
        List<String> trademarkNames = new ArrayList<>();
        try {
            String sql = "SELECT name FROM marcas";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                trademarkNames.add(resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
        }
        return trademarkNames;
    }

    //Enlistar en la tabla
    public List<Trademark> getAllTrademarks() {
        List<Trademark> trademarks = new ArrayList<>();
        try {
            String sql = "SELECT trademark_code, name FROM marcas";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Trademark trademark = new Trademark();
                trademark.setTrademark_code(resultSet.getInt("trademark_code"));
                trademark.setName(resultSet.getString("name"));
                trademarks.add(trademark);
            }

            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trademarks;
    }

    //Eliminar de la tabla
    public boolean deleteTrademark(int trademark_code) {
        try {
            String sql = "DELETE FROM marcas WHERE trademark_code = ?";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, trademark_code);
            int affectedRows = statement.executeUpdate();
            statement.close();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //VENTANA CATERGORY
    //Agrega la categoria
    public boolean addCategory(Category category) {
        try {
            String sql = "INSERT INTO categoria (category_code, name) VALUES (?, ?)";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, category.category_code);
            statement.setString(2, category.name);

            statement.executeUpdate();
            statement.close();
            return true;
        } catch (Exception e) {
            //Mesajes de error
            return false;
        }
    }

    //Carga en el combo box
    public List<String> nameCategory() {
        List<String> categoryNames = new ArrayList<>();
        try {
            String sql = "SELECT name FROM categoria";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                categoryNames.add(resultSet.getString("name"));
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
        }
        return categoryNames;
    }

    //Enlistar dentro de la tabla
    public List<Category> getAllCategory() {
        List<Category> categorys = new ArrayList<>();
        try {
            String sql = "SELECT category_code, name FROM categoria";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();
                category.setCategory_code(resultSet.getInt("category_code"));
                category.setName(resultSet.getString("name"));
                categorys.add(category);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorys;
    }

    //Eliminar de la tabla
    public boolean deleteCategory(int category_code) {
        try {
            String sql = "DELETE FROM categoria WHERE category_code = ?";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, category_code);
            int affectedRows = statement.executeUpdate();
            statement.close();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //VENTANA REGISTER PRODUCT
    //Agregar producto
    public boolean addProduct(Products products) {
        try {
            String sql = "INSERT INTO productos (barcode, product_name, trademark, category, gross_price, net_price, lot) VALUES (?, ?, ?, ?, ?, ?, ?)";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, products.barcode);
            statement.setString(2, products.productName);
            statement.setString(3, products.trademark);
            statement.setString(4, products.category);
            statement.setFloat(5, products.gross_price);
            statement.setFloat(6, products.net_price);
            statement.setInt(7, products.lot);

            statement.executeUpdate();
            statement.close();

            return true;
        } catch (Exception e) {
            return false;
            //Mensajes de error
        }
    }

    //VENTANA INVENTARIO
    //Enlistar productos
    public List<Products> getAllProducts() {
        List<Products> product = new ArrayList<>();
        try {
            String sql = "SELECT barcode, product_name, gross_price, net_price, lot, trademark FROM productos";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Products products = new Products();
                products.setBarcode(resultSet.getInt("barcode"));
                products.setProductName(resultSet.getString("product_name"));
                products.setGross_price(resultSet.getFloat("gross_price"));
                products.setNet_price(resultSet.getFloat("net_price"));
                products.setLot(resultSet.getInt("lot"));
                products.setTrademark(resultSet.getString("trademark"));

                product.add(products);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    //VENTANA MODIFICAR
    //Buscar productos
    public Products searchProduct(int barcode) {
        String sql = "SELECT * FROM productos WHERE barcode=?";
        try {
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, barcode);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Products product = new Products();
                product.setBarcode(resultSet.getInt("barcode"));
                product.setProductName(resultSet.getString("product_name"));
                product.setTrademark(resultSet.getString("trademark"));
                product.setCategory(resultSet.getString("category"));
                product.setGross_price(resultSet.getFloat("gross_price"));
                product.setNet_price(resultSet.getFloat("net_price"));
                product.setLot(resultSet.getInt("lot"));

                resultSet.close();
                statement.close();
                return product;
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //Modificar producto
    public boolean updateProduct(Products products) {
        String sql = "UPDATE productos SET product_name=?, trademark=?, category=?, gross_price=?, net_price=?, lot=? WHERE barcode=?";
        try {
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, products.getProductName());
            statement.setString(2, products.getTrademark());
            statement.setString(3, products.getCategory());
            statement.setFloat(4, products.getGross_price());
            statement.setFloat(5, products.getNet_price());
            statement.setInt(6, products.getLot());
            statement.setInt(7, products.getBarcode()); // Cambiado a setInt

            int rowsUpdated = statement.executeUpdate();
            System.out.println("Rows updated: " + rowsUpdated);
            statement.close();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    //VENTANA ELIMINAR
    //Listar productos
    public List<Products> getProducts() {
        List<Products> product = new ArrayList<>();
        try {
            String sql = "SELECT barcode, product_name, gross_price, net_price, lot, trademark, category FROM productos";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Products products = new Products();
                products.setBarcode(resultSet.getInt("barcode"));
                products.setProductName(resultSet.getString("product_name"));
                products.setGross_price(resultSet.getFloat("gross_price"));
                products.setNet_price(resultSet.getFloat("net_price"));
                products.setLot(resultSet.getInt("lot"));
                products.setTrademark(resultSet.getString("trademark"));
                products.setCategory(resultSet.getString("category"));

                product.add(products);
            }
            resultSet.close();
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    //Eliminar producto
    public boolean deleteProduct(int barcode) {
        try {
            String sql = "DELETE FROM productos WHERE barcode = ?";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, barcode);
            int affectedRows = statement.executeUpdate();
            statement.close();
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    //VENTANA VENTAS
    //
    public Products sealsProduct(int barcode) {
        Products product = new Products();
        try {
            String sql = "SELECT * FROM productos WHERE barcode = ?";
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setInt(1, barcode);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                product.setBarcode(resultSet.getInt("barcode"));
                product.setProductName(resultSet.getString("product_name"));
                product.setNet_price(resultSet.getFloat("net_price"));
                product.setLot(resultSet.getInt("lot"));
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void updateProductLot(int barcode, int newLot) {
        String sql = "UPDATE productos SET lot = ? WHERE barcode = ?";
        try {
            Connection connection = this.dbSentences.getConexion();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, newLot);
            statement.setInt(2, barcode);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected == 0) {
                System.out.println("No se encontró el producto con el código de barras: " + barcode);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al actualizar el producto con el código de barras: " + barcode);
        }
    }
}
