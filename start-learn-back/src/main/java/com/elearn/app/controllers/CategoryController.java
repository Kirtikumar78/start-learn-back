package com.elearn.app.controllers;

import com.elearn.app.config.AppConstants;
import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.dtos.CustomMessage;
import com.elearn.app.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")

public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //create category
    @PostMapping
    public ResponseEntity<CategoryDto> create(
            @RequestBody  CategoryDto categoryDto
    ){
        CategoryDto createdDto = categoryService.insert(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);

    }

    //get all category
    @GetMapping
    public List<CategoryDto>getAll(
            @RequestParam(value = "pageNumber",required = false,defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNumber,
            @RequestParam(value = "pageSize",required = false,defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize
    ){
        return categoryService.getall(pageNumber,pageSize);
    }

    //get one category
    @GetMapping("/{categoryId}")
    public CategoryDto getSingle (
            @PathVariable String categoryId
    ){
        return categoryService.get(categoryId);

    }

    //delete category
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CustomMessage>delete(
            @PathVariable String categoryId
    ){
         categoryService.delete(categoryId);
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage("category deleted !!");
        customMessage.setSuccess(true);
        return ResponseEntity.status(HttpStatus.OK).body(customMessage);


    }

    //update category
    @PutMapping("/{categoryId}")
    public CategoryDto update(
            @PathVariable String categoryId,
            @RequestBody CategoryDto categoryDto
    ){
        return categoryService.update(categoryDto,categoryId);

    }
}
