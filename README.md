# EasyShop Product Management API

## Project Overview
**EasyShop** is a robust e-commerce backend API designed to simplify product management, user authentication, and search functionalities. Built with Java, Spring Boot, and MySQL, this application provides seamless operations for online shopping systems.

---

## Features

### Authentication
- **JWT-based secure login and registration**.
- Role-based access for admins and standard users.

### Product Operations
- Search and filter by category, price range, and color.
- Full CRUD operations for administrators.

---

## Phases Overview

### Phase 1 - CategoriesController
- **Endpoints**:
  - `GET /categories`: Fetch all categories.
  - `POST /categories`: Add a new category (admin only).
  - `PUT /categories/{id}`: Update a category.
  - `DELETE /categories/{id}`: Delete a category.

### Phase 2 - Bug Fixes
1. **Bug 1**: Resolved inaccurate search results.
  - **Solution**: Optimized SQL query logic.
2. **Bug 2**: Prevented duplication during updates.
  - **Solution**: Ensured updates modify existing records.



### Phase 4 - Update Profile
1. **Update Profile (Optional)**:
  - Add and update user profiles.

---

## Components

### User Management
Handles user registration, login, and role-based access control. Uses JWT tokens for secure communication.

### Product Management
Manages product data and provides CRUD functionalities. Supports advanced filtering for search results based on product attributes like category, price range, and color.

### CategoriesController
Responsible for managing product categories. Includes API endpoints to create, read, update, and delete categories.

---

## Getting Started

### Prerequisites
1. Java Development Kit (JDK)
2. MySQL Database Server
3. Postman or similar API testing tool
4. Git

### Installation

#### 1. Clone the Repository:
```bash
git clone <repository-url>
cd <project-folder>
---------------------------------------------------------------------------------------------------------

### 2. Interesting Code Section:

One of the most interesting sections of the code is the **Product Search and Filter** functionality. This method allows users to search for products by category, price range, and color, and efficiently retrieves data from the database using optimized SQL queries.

### Code Snippet :
```java
public List<Product> searchProducts(String category, double minPrice, double maxPrice, String color) {
    String sql = "SELECT * FROM products WHERE category = ? AND price BETWEEN ? AND ? AND color = ?";
    return jdbcTemplate.query(sql, new Object[]{category, minPrice, maxPrice, color}, new ProductRowMapper());
}
--------------------------------------------------------------------------------------------------------

## License
This project is licensed under the MIT License.
