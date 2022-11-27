package com.controller;

import com.DTO.ColorDTO;
import com.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@RequiredArgsConstructor
public class ColorController {

    private final ColorService colorService;

    @GetMapping("user/colors")
    public ResponseEntity<List<ColorDTO>> getAll(){
        return ResponseEntity.ok(colorService.findAll());
    }

    @PostMapping("admin/colors")
    public ResponseEntity<ColorDTO> createColor(@Valid @RequestBody ColorDTO colorDTO){
        return ResponseEntity.ok(colorService.create(colorDTO));
    }

    @PutMapping("admin/colors")
    public ResponseEntity<ColorDTO> updateColor(@Valid @RequestBody ColorDTO colorDTO){
        return ResponseEntity.ok(colorService.create(colorDTO));
    }

    @DeleteMapping("admin/colors")
    public ResponseEntity deleteColor(@RequestBody List<Integer> id){
        colorService.remove(id);
        return ResponseEntity.ok().build();
    }
}
