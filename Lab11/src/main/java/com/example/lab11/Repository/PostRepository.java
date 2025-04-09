package com.example.lab11.Repository;


import com.example.lab11.Model.Post;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;



////
//@Query("select c from Coffee c where c.price >=?1 and c.price<=?2")
//List<Coffee>findPriceRange(Integer min, Integer max);

        public interface PostRepository extends JpaRepository<Post,Integer> {

    Post findPostById(Integer id);

    @Query("select  p from Post p where p.user_id =?1 ")
    Post findPostByUser_id(Integer id);



    @Query("select  p from Post p where p.user_id =?1 ")
    List<Post>  getAllPostsByUser(Integer user_id);


    //the title is not unique so its a list
    @Query("select  p from Post p where p.title=?1")
    List<Post>  getAllPostsByTittle(String title);

    @Query("select  p from Post p where p.publish_date<?1 ")
    List<Post>  getAllPostsBeforeDate(LocalDate date);


    List<Post> findPostByCategoryId( Integer categoryId);



}
