package com.controller;



import com.DTO.CategoryDTO;
import com.DTO.ProductDTO;
import com.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RequestMapping("api/v1")
@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("user/categories")
    public ResponseEntity<List<CategoryDTO>>  getAllCategory() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @GetMapping("/user/categories/byid/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable("id") Integer id) {
    	if (categoryService.findById(id)==null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found category");
    	}
        return ResponseEntity.ok(categoryService.findById(id));
    }

    @PostMapping("/admin/categories")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoryDTO category) {
    	if (categoryService.findById(category.getIdCategory())!=null) {
    		return ResponseEntity.badRequest().body("Category already exist");
    	}
        return ResponseEntity.ok(categoryService.create(category));
    }

    @PutMapping("/admin/categories")
    public ResponseEntity<?> updateCategory(@RequestBody CategoryDTO category){
    	if (categoryService.findById(category.getIdCategory())==null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found category");
    	}
        return ResponseEntity.ok(categoryService.update(category));
    }

    @DeleteMapping("admin/categories/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable("id") Integer id) {
    	if (categoryService.findById(id)==null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found category");
    	}
        categoryService.remove(id);
        return ResponseEntity.ok(id);
    }
    @DeleteMapping("admin/categories")
    public ResponseEntity<?> deleteCategories(@RequestParam("id") List<Integer> id) {
        categoryService.removeAll(id);
        return ResponseEntity.ok().build();
    }
}
