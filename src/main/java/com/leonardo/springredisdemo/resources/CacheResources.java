package com.leonardo.springredisdemo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cars")

//Anotação responsável por configurar todas as anotações de cache desta classe, assim evitando repetição de código.
//Nesse caso, estou definindo o nome do cache dessa entidade. Assim evitando precisar incluir o atributo cacheNames em todas as anotações
//dessa class.
@CacheConfig(cacheNames = {"cars"})
public class CacheResources {
    
    private static List<String> cars = new ArrayList<>(
        Arrays.asList(
            "Corsa", 
            "Chevette", 
            "Fusca"
        )
    );

    @GetMapping
    //Anotação que define que esse método irá gerar cache, mas sempre irá executar o método
    @CachePut
    public List<String> getAll(){
        System.out.println("Rodou sem cache (all)");
        return cars;
    }

    @GetMapping("/{id}")
    //Anotação que define que esse método utilizará cache, não irá executar o método se houver cache com o nome informado
    //apenas irá retornar o valor do cache
    @Cacheable
    public String get(@PathVariable Integer id){
        System.out.println("Rodou sem cache (by id)");
        return cars.get(id);
    }

    @GetMapping("/add/{car}")
    //Anotação que o cache irá ser limpo sempre que esse método for executado
    @CacheEvict(allEntries = true)
    public void addCar(@PathVariable String car){
        cars.add(car);
    }

}
