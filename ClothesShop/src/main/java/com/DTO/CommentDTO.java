package com.DTO;

import com.entity.Comment;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
public class CommentDTO implements Serializable {
    private Integer idComment;
    private String description;
    private Date commentDate;
    private Integer idProduct;
    private String idUsernameAccount;
}
