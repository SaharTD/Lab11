package com.example.lab11.Controller;

import com.example.lab11.Api.ApiResponse;
import com.example.lab11.Model.Comment;
import com.example.lab11.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;



    @GetMapping("get")
    public ResponseEntity addComment (){
        return ResponseEntity.status(200).body(commentService.getAllComments()) ;
    }

    @PostMapping("add")
    public ResponseEntity addPost(@Valid @RequestBody Comment comment,Errors e ){


        if(e.hasErrors()){
            return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());
        }


        if(commentService.addComment(comment).equals("PostNotF")){
            return ResponseEntity.status(400).body(new ApiResponse("The post id is not found"));

        }else if (commentService.addComment(comment).equals("UserNotF")){
            return ResponseEntity.status(400).body(new ApiResponse("The user id is not found"));

        }else commentService.addComment(comment);
        return ResponseEntity.status(200).body(new ApiResponse("The comment is added successfully"));

    }


    @PutMapping("update/{comment_id}/{user_id}")
    public ResponseEntity updateComment(@Valid @RequestBody Comment comment, Errors e, @PathVariable Integer comment_id , @PathVariable Integer user_id){

        if(e.hasErrors()){
            return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());
        }


        if(commentService.updateComment(comment,comment_id,user_id)){
            return ResponseEntity.status(200).body(new ApiResponse("The Comment is added successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The comment or the user are not found"));


    }


    @DeleteMapping("delete/{comment_id}/{user_id}")
    public ResponseEntity deleteComment(@PathVariable Integer comment_id,@PathVariable Integer user_id){

        if(commentService.deleteComment(comment_id,user_id)){
            return ResponseEntity.status(200).body(new ApiResponse("The comment is deleted successfully"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("The comment or the user are not found"));

    }




    @GetMapping("getPosts/{post_id}")
    public ResponseEntity findCommentsByPost_id (@PathVariable Integer post_id){
        if (commentService.findCommentsByPost_id(post_id).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("This post does not have any comments")) ;
        }
        return ResponseEntity.status(200).body(commentService.findCommentsByPost_id(post_id)) ;
    }



    @GetMapping("getUser/{user_id}")
    public ResponseEntity findCommentByUser_id (@PathVariable Integer user_id){
        if (commentService.findCommentByUser_id(user_id).isEmpty()){
            return ResponseEntity.status(400).body(new ApiResponse("This post does not have any comments")) ;
        }
        return ResponseEntity.status(200).body(commentService.findCommentByUser_id(user_id)) ;
    }





}






