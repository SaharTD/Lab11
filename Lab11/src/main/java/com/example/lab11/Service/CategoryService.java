package com.example.lab11.Service;

import com.example.lab11.Model.Category;
import com.example.lab11.Model.Comment;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {




    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }


    public void addCategory(Category c){
        categoryRepository.save(c);
    }


    public Boolean updateCategory (Category category, Integer id)
    {
        Category category1= categoryRepository.findCategoryById(id);

        if(category1!=null){
            category1.setName(category.getName());

            categoryRepository.save(category1);
            return true;
        }
        return false;
    }



    public Boolean deleteCategory(Integer id){

        if(categoryRepository.findCategoryById(id)!=null){
            categoryRepository.delete(categoryRepository.findCategoryById(id));
            return true;
        }
        return false;
    }






}
