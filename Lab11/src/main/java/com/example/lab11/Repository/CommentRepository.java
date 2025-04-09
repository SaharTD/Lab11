package com.example.lab11.Repository;


import com.example.lab11.Model.Comment;
import com.example.lab11.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Integer> {


    Comment findCommentById(Integer id);

    @Query("select c from Comment c where c.user_id=?1")
   List<Comment>  findCommentByUser_id(Integer user_id);



    @Query("select c from Comment c where c.post_id=?1")
    List<Comment> findCommentsByPost_id(Integer post_id) ;


}
