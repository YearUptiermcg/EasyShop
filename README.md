# EasyShop Product Management API

![EasyShop Logo](C:/Users/Student/Desktop/logo.png)

EasyShop is a robust e-commerce backend API designed to simplify product management, user authentication, and search functionalities. Built with Java, Spring Boot, and MySQL, this application provides seamless operations for online shopping systems.

---

## Table of Contents
1. [Application Structure](#application-structure)
2. [Features](#features)
3. [Phases Overview](#phases-overview)
4. [Getting Started](#getting-started)
5. [Authentication](#authentication)
6. [Interesting Code](#interesting-code)
7. [License](#license)

---

## Application Structure

### Core Components:
- **User Management**: Secure registration and login functionality with role-based access.
- **Product Management**: CRUD operations for products, with advanced search and filtering options.
- **Database Integration**: MySQL backend, structured to support efficient data retrieval and storage.

---

## Features

### Authentication
- **JWT-based secure login and registration**.
- Role-based access for admins and standard users.

### Product Operations
- Search and filter by category, price range, and color.
- Full CRUD operations for administrators.

### Shopping Cart (Optional)
- Add and remove items, view totals.
- Persistent cart data tied to user accounts.

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

   ![Bug Fix Demonstration](C:/Users/Student/Desktop/update.bug2/bug-fix.png)

---

## Getting Started

### Prerequisites:
1. Java Development Kit (JDK)
2. MySQL Database Server
3. Postman or similar API testing tool
4. Git

### Installation:

#### 1. Clone the Repository:
```bash
git clone <repository-url>
cd <project-folder>

# License

This project is licensed under the **MIT License**.  

*



