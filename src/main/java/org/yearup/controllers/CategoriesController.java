package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import java.util.List;

@RestController
@RequestMapping("categories")
@CrossOrigin
public class CategoriesController {

    private CategoryDao categoryDao;

    @Autowired
    public CategoriesController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @GetMapping("")
    public ResponseEntity<List<Category>> getAll() {
        // Find and return all categories
        List<Category> allCategories = categoryDao.getAllCategories();
        if (allCategories.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allCategories);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getById(@PathVariable int id) {
        // Get the category by id
        Category category = categoryDao.getById(id);
        if (category == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(category);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Category addCategory(@RequestBody Category category) {
        // Insert the category
        return categoryDao.create(category);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Void> updateCategory(@PathVariable int id, @RequestBody Category category) {
        try {
            categoryDao.update(id, category);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating category", ex);
        }
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable int id) {
        try {
            categoryDao.delete(id);
            return ResponseEntity.noContent().build();  // Return 204 No Content
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error deleting category", ex);
        }
    }
}
