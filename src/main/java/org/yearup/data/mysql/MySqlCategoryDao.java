package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {

    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories;";

        try (Connection connection = getConnection(); PreparedStatement query = connection.prepareStatement(sql); ResultSet results = query.executeQuery()) {
            while (results.next()) {
                int catId = results.getInt(1);
                String catName = results.getString(2);
                String catDesc = results.getString(3);
                categories.add(new Category(catId, catName, catDesc));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return categories;
    }

    @Override
    public Category getById(int categoryId) {
        String sql = "SELECT category_id, name, description FROM categories WHERE category_id = ?";

        try (Connection connection = getConnection(); PreparedStatement query = connection.prepareStatement(sql)) {
            query.setInt(1, categoryId);
            try (ResultSet result = query.executeQuery()) {
                return result.next() ? mapRow(result) : null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category create(Category category) {
        String sql = "INSERT INTO categories(name, description) VALUES (?,?);";

        try (Connection connection = getConnection(); PreparedStatement query = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            query.setString(1, category.getName());
            query.setString(2, category.getDescription());
            int i = query.executeUpdate();
            if (i > 0) {
                ResultSet generatedKeys = query.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int generatedId = generatedKeys.getInt(1);
                    return getById(generatedId);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return null;
    }

    @Override
    public void update(int categoryId, Category category) {
        String sql = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";

        try (Connection connection = getConnection(); PreparedStatement query = connection.prepareStatement(sql)) {
            query.setString(1, category.getName());
            query.setString(2, category.getDescription());
            query.setInt(3, categoryId);
            query.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void delete(int categoryId) {
        String sql = "DELETE FROM categories WHERE category_id = ?;";

        try (Connection connection = getConnection(); PreparedStatement query = connection.prepareStatement(sql)) {
            query.setInt(1, categoryId);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Category mapRow(ResultSet row) throws SQLException {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setName(name);
        category.setDescription(description);

        return category;
    }
}
