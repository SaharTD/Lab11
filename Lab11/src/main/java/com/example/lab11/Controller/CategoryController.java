package com.example.lab11.Controller;

import com.example.lab11.Api.ApiResponse;
import com.example.lab11.Model.Category;
import com.example.lab11.Service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;



    @GetMapping("get")
    public ResponseEntity getAllCategories (){
        return ResponseEntity.status(200).body(categoryService.getAllCategories()) ;
    }

    @PostMapping("add")
    public ResponseEntity addCategory(@Valid @RequestBody Category c, Errors e ){

        if(e.hasErrors()){
            return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());
        }

        categoryService.addCategory(c);
        return ResponseEntity.status(200).body(new ApiResponse("The category is added successfully"));
    }


    @PutMapping("update/{id}")
    public ResponseEntity updateCategory(@Valid @RequestBody Category category, Errors e, @PathVariable Integer id){

        if(e.hasErrors()){
            return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());
        }


        if(categoryService.updateCategory(category,id)){
            return ResponseEntity.status(200).body(new ApiResponse("The category is added successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The category is not found"));


    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable Integer id){

        if(categoryService.deleteCategory(id)){
            return ResponseEntity.status(200).body(new ApiResponse("The category is deleted successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The category is not found"));

    }
}
