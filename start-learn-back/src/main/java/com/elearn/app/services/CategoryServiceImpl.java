package com.elearn.app.services;

import com.elearn.app.dtos.CategoryDto;
import com.elearn.app.entities.Category;
import com.elearn.app.exceptions.ResourceNotFoundException;
import com.elearn.app.repositories.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service

public class CategoryServiceImpl implements CategoryService{
    private CategoryRepo repo;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepo repo, ModelMapper modelMapper) {
        this.repo = repo;
        this.modelMapper = modelMapper;
    }

    @Override
    public CategoryDto insert(CategoryDto categoryDto) {
        //create cat Id
        String catId= UUID.randomUUID().toString();
        categoryDto.setId(catId);
        //cat date
        categoryDto.setAddedDate(new Date());
        //convert dto to entity
        Category category = modelMapper.map(categoryDto, Category.class);
        Category savedCat = repo.save(category);
        return modelMapper.map(savedCat,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getall(int pageNumber,int pageSize) {
        List<Category> all = repo.findAll();
        return all.stream().map(cat -> modelMapper.map(cat, CategoryDto.class)).toList();
    }

    @Override
    public CategoryDto get(String categoryId) {
        Category category = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found "));
        return modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public void delete(String categoryId) {
        Category category = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found "));
        repo.delete(category);


    }

    @Override
    public CategoryDto update(CategoryDto categoryDto, String categoryId) {
        Category category = repo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category not found "));
        category.setTitle(categoryDto.getTitle());
        category.setDesc(categoryDto.getDesc());
        Category savedCategory = repo.save(category);
        return modelMapper.map(savedCategory,CategoryDto.class);

    }
}
