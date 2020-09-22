package com.internet.shop.dao.jdbc;

import com.internet.shop.dao.ProductDao;
import com.internet.shop.lib.Dao;
import com.internet.shop.model.Product;
import com.internet.shop.util.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Dao
public class ProductDaoJDBCImpl implements ProductDao {

    Connection connection = ConnectionUtil.getConnection();

    @Override
    public Product create(Product element) {

        String query = "INSERT INTO internet_shop.products (name, price) VALUES (?, ?);";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, element.getName());
            statement.setInt(2, (int) element.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Can not create item! ", e);
        }
        return element;
    }

    @Override
    public Optional<Product> get(Long id) {

        String query = "SELECT * FROM internet_shop.poducts where id=?;";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long productId = resultSet.getLong("id");
                String name = resultSet.getString("name");
                int price = resultSet.getInt("price");

                Product product = new Product();
                product.setId(productId);
                product.setName(name);
                product.setPrice(price);

                return Optional.of(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Can not get item with id " + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public Product update(Product element) {
        return null;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }
}
