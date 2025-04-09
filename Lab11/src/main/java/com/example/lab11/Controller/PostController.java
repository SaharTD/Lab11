package com.example.lab11.Controller;


import com.example.lab11.Api.ApiResponse;
import com.example.lab11.Model.Post;
import com.example.lab11.Service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("api/v1/post")
@RequiredArgsConstructor
public class PostController {


    private final PostService postService;



    @GetMapping("get")
    public ResponseEntity getAllPosts (){
        return ResponseEntity.status(200).body(postService.getAllPost()) ;
    }

    @PostMapping("add")
    public ResponseEntity addPost(@Valid @RequestBody Post post,Errors e ){

        if (e.hasErrors()){
            return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());

        }
        if(postService.addPost(post).equals("categoryNotF")){
            return ResponseEntity.status(400).body(new ApiResponse("The post category id is not found"));

        }else if (postService.addPost(post).equals("UserNotF")){
            return ResponseEntity.status(400).body(new ApiResponse("The user id is not found"));

        }else postService.addPost(post);
        return ResponseEntity.status(200).body(new ApiResponse("The post is added successfully"));
    }


    @PutMapping("update/{user_id}/{post_id}")
    public ResponseEntity updatePost(@Valid @RequestBody Post post, Errors e, @PathVariable Integer user_id , @PathVariable Integer post_id){

        if(e.hasErrors()){
            return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());
        }


        if(postService.updatePost(post,user_id,post_id)){
            return ResponseEntity.status(200).body(new ApiResponse("The post is added successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The post or the user are not found"));


    }


    @DeleteMapping("delete/{id}/{user_id}")
    public ResponseEntity deletepost(@PathVariable Integer id,@PathVariable Integer user_id){

        if(postService.deletePost(id,user_id)){
            return ResponseEntity.status(200).body(new ApiResponse("The post is deleted successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The post or the user are not found"));

    }




    @GetMapping("getUser/{user_id}")
    public ResponseEntity getAllPostsByUser (@PathVariable Integer user_id){
        if (postService.getAllPostsByUser(user_id).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("The user does not have any posts")) ;
        }
        return ResponseEntity.status(200).body(postService.getAllPostsByUser(user_id)) ;
    }



    @GetMapping("getTittle/{tittle}")
    public ResponseEntity getAllPostsByTittle (@PathVariable String tittle){
        if (postService.getAllPostsByTittle(tittle).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("This tittle does not have any posts")) ;
        }
        return ResponseEntity.status(200).body(postService.getAllPostsByTittle(tittle)) ;
    }



    @GetMapping("getDate/{date}")
    public ResponseEntity getAllPostBeforeDate (@PathVariable LocalDate date){
        if (postService.getAllPostBeforeDate(date).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("This date  does not have any posts before it")) ;
        }
        return ResponseEntity.status(200).body(postService.getAllPostBeforeDate(date)) ;
    }


    @GetMapping("getCategory/{category_id}")
    public ResponseEntity getPostByCategory_id (@PathVariable Integer category_id){
        if (postService.getPostByCategory_id(category_id).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("This category does not have any posts")) ;
        }
        return ResponseEntity.status(200).body(postService.getPostByCategory_id(category_id)) ;
    }







}
