package com.example.lab11.Service;

import com.example.lab11.Model.Comment;
import com.example.lab11.Model.User;
import com.example.lab11.Repository.CommentRepository;
import com.example.lab11.Repository.PostRepository;
import com.example.lab11.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {



    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }


    public String  addComment(Comment comment){

        if(userRepository.findUserById(comment.getUser_id())==null) {
            return "UserNotF";
        }
        if (postRepository.findPostById(comment.getPost_id())==null){
            return "PostNotF";
        }
            comment.setComment_date(LocalDate.now());
            commentRepository.save(comment);
            return "true";
        }





    public Boolean updateComment ( Comment comment,Integer comment_id, Integer user_id)
    {
        Comment oldComment= commentRepository.findCommentById(comment_id);

        if(oldComment!=null && commentRepository.findCommentByUser_id(user_id)!=null ){
            oldComment.setContent(comment.getContent());
            oldComment.setComment_date(comment.getComment_date());

            commentRepository.save(oldComment);
            return true;
        }
        return false;
    }



    public Boolean deleteComment (Integer comment_id,Integer user_id){

        if(commentRepository.findCommentById(comment_id)!=null && commentRepository.findCommentByUser_id(user_id)!=null){
            commentRepository.delete(commentRepository.findCommentById(comment_id));
            return true;
        }
        return false;
    }

/// 4

    public List<Comment> findCommentsByPost_id(Integer post_id){
        return commentRepository.findCommentsByPost_id(post_id);
    }



/// 5

public List<Comment> findCommentByUser_id(Integer user_id){
    return commentRepository.findCommentByUser_id(user_id);
}




}
