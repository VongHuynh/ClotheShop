package com.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ColorDTO implements Serializable {
    private Integer idColor;
    @NotBlank(message = "${NotBlank.ColorDTO.nameColor}")
    private String nameColor;
}
