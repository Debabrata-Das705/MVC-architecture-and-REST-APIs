package com.mvc.MVCDemo.controllers;

import com.mvc.MVCDemo.Exception.ResourceNotFoundException;
import com.mvc.MVCDemo.dto.EmpDTO;
import com.mvc.MVCDemo.service.EmpService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@Slf4j
public class EmpControler {

    private final EmpService service;

    public EmpControler(EmpService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    public ResponseEntity<EmpDTO> getById(@PathVariable Long id){
          Optional<EmpDTO> entity=service.getById(id);
          if(entity.isPresent())
              return new ResponseEntity<>(entity.get(),HttpStatus.OK);

          else
              log.error("Execption occurred {}",id);
              throw new ResourceNotFoundException("Resourse not found id"+id);


//        return entity.map(entity1 ->ResponseEntity.ok(entity1))
//                .orElse(ResponseEntity.notFound().build());
    }
    //=======================================================loacl exc handlinng
//    @ExceptionHandler(NoSuchElementException.class)
//    public ResponseEntity<String> handleEmployeeNotFound(NoSuchElementException exc){
//        return new ResponseEntity<>("Employee not found",HttpStatus.NOT_FOUND);
//    }



    @GetMapping("/all/{pageno}")
    public ResponseEntity<List<EmpDTO>> getAllEmp(@PathVariable int pageno){
        log.info("Sucessfully retrived.... {}","rahul");
        return new ResponseEntity<>(service.getAllEmp(pageno),HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<EmpDTO> createNewEmp2(@RequestBody @Valid EmpDTO inseert){

        return  new ResponseEntity<>(service.createNewEmp2(inseert), HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<EmpDTO> updateById(@PathVariable Long id,@RequestBody EmpDTO dto){
        return new ResponseEntity<>(service.updateById(id,dto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        boolean gotDeleted=service.deleteByid(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();

    }
    @PatchMapping("/{id}")
    public ResponseEntity<EmpDTO> updatePartialyById(@PathVariable Long id,@RequestBody Map<String,Object> update){
        return ResponseEntity.ok(service.updatePartialyById(id,update));

    }

}
