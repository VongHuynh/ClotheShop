package com.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class SizeDTO implements Serializable {
    private Integer idSize;

    @NotBlank(message = "${NotBlank.RoleDTO.nameRole}")
    private String nameSize;
}
