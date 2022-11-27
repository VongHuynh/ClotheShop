package com.controller;

import com.DTO.ColorDTO;
import com.DTO.ProductColorsDTO;
import com.DTO.SizeDTO;
import com.service.ProductColorsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class ProductColorsController {


    private final ProductColorsService productColorsService;


    @GetMapping("user/productcolor/detail/{idProduct}/{idColor}")
    public ResponseEntity<ProductColorsDTO>  detailColorProduct(@PathVariable Integer idProduct,
                                                                @PathVariable Integer  idColor){
        ProductColorsDTO productColorsDTO = productColorsService.findByIdColorAndIdProduct(idColor,idProduct);
        return ResponseEntity.ok(productColorsDTO);
    }


    @GetMapping("user/productcolor")
    public ResponseEntity<Map<String,Object>> getAllProductByColor(@RequestParam Integer idColor,
                                                                   @RequestParam Optional<Integer> pageNum,
                                                                   @RequestParam Optional<Integer> size){
        return ResponseEntity.ok(productColorsService.findByColor(idColor,pageNum.orElse(0),size.orElse(10)));
    }

    @PostMapping("admin/productcolor")
    public ResponseEntity<ProductColorsDTO> addNewProductColor(@Valid @RequestBody ProductColorsDTO productColorsDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productColorsService.create(productColorsDTO));
    }

    @PutMapping("admin/productcolor")
    public ResponseEntity<ProductColorsDTO> updateProductColor(@Valid @RequestBody ProductColorsDTO productColorsDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(productColorsService.create(productColorsDTO));
    }

    @DeleteMapping("admin/productcolor/{idProductColor}")
    public ResponseEntity deleteProductColor(@PathVariable Integer idProductColor){
        productColorsService.remove(idProductColor);
        return ResponseEntity.ok().build();
    }


    @GetMapping("user/colors/byproduct/{idProduct}")
    public ResponseEntity<List<ColorDTO>>  getColorsByIdProduct(@PathVariable Integer idProduct){
        return ResponseEntity.ok(productColorsService.findByIdProduct(idProduct));
    }

    @GetMapping("user/productcolor/bysize")
    public ResponseEntity<Map<String,Object>> getProductColorBySize(@RequestParam  Integer idSize,
                                                                        @RequestParam Optional<Integer> pageNum,
                                                                        @RequestParam Optional<Integer> size){
        return ResponseEntity.ok(productColorsService.fillBySize(idSize,pageNum.orElse(0),size.orElse(10)));
    }

}
