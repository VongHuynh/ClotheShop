package com.serviceImpl;

import com.DAO.CommentDAO;
import com.DTO.CommentDTO;
import com.entity.Comment;
import com.service.CommentService;
import com.utils.Convert;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentDAO commentDAO;
    private final Convert convert;
    @Override
    public List<CommentDTO> findAll() {

        return null;
    }

    @Override
    public CommentDTO findById(Integer id) {
        return null;
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public CommentDTO create(CommentDTO commentDto) {
        Comment comment = convert.toEntity(commentDto,Comment.class);
        CommentDTO commentDTO = convert.toDto(commentDAO.save(comment),CommentDTO.class);
        return commentDTO;
    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void update(CommentDTO CommentDto) {

    }

    @Override @Transactional(rollbackFor = {Exception.class, Throwable.class})
    public void remove(Integer id) {

    }

    @Override
    public List<CommentDTO> findAllByIdProduct(Integer id) {
        List<Comment> comments = commentDAO.findAllByIdProduct(id);
        List<CommentDTO> commentDTOS = comments.stream()
                .map(commentEntity -> convert.toDto(commentEntity,CommentDTO.class))
                .collect(Collectors.toList());
        return commentDTOS;
    }
}
