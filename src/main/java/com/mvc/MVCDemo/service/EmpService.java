package com.mvc.MVCDemo.service;

import com.mvc.MVCDemo.Exception.ResourceNotFoundException;
import com.mvc.MVCDemo.dto.EmpDTO;
import com.mvc.MVCDemo.entities.EmployeeEntity;
import com.mvc.MVCDemo.reop.EmployeeRepo;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmpService {
    private final EmployeeRepo repo;

    private final ModelMapper mapper;


    EmployeeEntity e=new EmployeeEntity();

    public EmpService(EmployeeRepo repo, ModelMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    public Optional<EmpDTO> getById(Long id){

       Optional<EmployeeEntity> entity=  repo.findById(id);
       return entity.map(entity1 ->mapper.map(entity1,EmpDTO.class));


       //Eta v hei pare jadi empdto retun karibaku chaha
//       if(entity.isPresent()){
//           return mapper.map(entity.get(), EmpDTO.class);
//       }
//       else throw new ResourceNotFoundException("Not found");
    }


    public List<EmpDTO> getAllEmp(int pageno){
        PageRequest page = PageRequest.of(pageno,3);
       Page<EmployeeEntity> entities=repo.findAll(page);
        log.error("Error for emp ",e.getName());
//        log.warn("warn for emp ",e.getName());
//        log.info("info for emp ",e.getName());
//        log.debug("Debug for emp ",e.getName());
        log.trace("Trace for emp ",e.getName());
      return entities.stream()
               .map(entity1 ->mapper.map(entity1, EmpDTO.class))
               .collect(Collectors.toList());
    }


    public EmpDTO createNewEmp2( EmpDTO inseert){
        EmployeeEntity toSave=mapper.map(inseert, EmployeeEntity.class);
        EmployeeEntity entity=repo.save(toSave);
        return mapper.map(entity, EmpDTO.class);
    }

    public EmpDTO updateById(Long id,EmpDTO dto){
        boolean isExist=repo.existsById(id);
        if(! isExist) throw new ResourceNotFoundException("Resourse not found id"+id);


        EmployeeEntity updateTo=mapper.map(dto,EmployeeEntity.class);
        updateTo.setId(id);
        EmployeeEntity entity=repo.save(updateTo);
        return mapper.map(entity, EmpDTO.class);
    }


    public boolean deleteByid(Long id) {
        boolean isExist=repo.existsById(id);
        if(! isExist) throw new ResourceNotFoundException("Resourse not found id "+id);
        repo.deleteById(id);
        return true;
    }


    public EmpDTO updatePartialyById(Long id,  Map<String,Object> updates){
        boolean isExist=repo.existsById(id);
        if(!isExist) throw new ResourceNotFoundException("Resourse not found id"+id);



        EmployeeEntity entity=repo.findById(id).orElse(null);
        updates.forEach((field,value) ->{
            Field fieldToBeUpdate= ReflectionUtils.findField(EmployeeEntity.class,field);
            fieldToBeUpdate.setAccessible(true);
            ReflectionUtils.setField(fieldToBeUpdate,entity,value);
        });
        EmployeeEntity toSave=repo.save(entity);
        return mapper.map(toSave,EmpDTO.class);
    }
}
