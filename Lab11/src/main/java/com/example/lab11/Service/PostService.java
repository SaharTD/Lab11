package com.example.lab11.Service;

import com.example.lab11.Model.Post;
import com.example.lab11.Repository.CategoryRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {


    private final PostRepository postRepository;
private final CategoryRepository categoryRepository;
private final UserRepository userRepository;




    public List<Post> getAllPost(){
        return postRepository.findAll();
    }


    public String  addPost(Post post){

        if(categoryRepository.findCategoryById(post.getCategoryId())==null) {
            return "categoryNotF";
        }
        if(userRepository.findUserById(post.getUser_id())==null){
            return "UserNotF";
        }
            post.setPublish_date(LocalDate.now());
            postRepository.save(post);
        return "true";
        }





    public Boolean updatePost (Post post, Integer user_id, Integer post_id)
    {
        Post oldPost= postRepository.findPostById(post_id);

        if(oldPost!=null && postRepository.findPostByUser_id(user_id)!=null){

            oldPost.setCategoryId(post.getCategoryId());
            oldPost.setTitle(post.getTitle());
            oldPost.setContent(post.getContent());
            oldPost.setPublish_date(post.getPublish_date());

            postRepository.save(oldPost);
            return true;
        }
        return false;
    }


//check if the  the post exist  and if the user made that post
    public Boolean deletePost(Integer id , Integer user_id){

        if(postRepository.findPostById(id)!=null  && postRepository.findPostByUser_id(user_id)!=null){
            postRepository.delete(postRepository.findPostById(id));
            return true;
        }
        return false;
    }

/// 1
    public List<Post> getAllPostsByUser(Integer user_id){
        return postRepository.getAllPostsByUser(user_id);
    }

/// 2
public List<Post> getAllPostsByTittle(String tittle){
        return postRepository.getAllPostsByTittle(tittle);
}

public List<Post> getAllPostBeforeDate(LocalDate date){
        return postRepository.getAllPostsBeforeDate(date);
}
/// 3
public List<Post> getPostByCategory_id (Integer category_id){
    return postRepository.findPostByCategoryId(category_id);

}



}
