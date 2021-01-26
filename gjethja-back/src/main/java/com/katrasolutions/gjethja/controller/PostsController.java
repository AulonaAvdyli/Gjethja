package com.katrasolutions.gjethja.controller;

import com.katrasolutions.gjethja.model.PostsModel;
import com.katrasolutions.gjethja.service.PostsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
public class PostsController {

    private final PostsService postsService;

    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/{id}")
    public PostsModel findById(@PathVariable Long id) {
        return postsService.getById(id);
    }

    @GetMapping("/current-user")
    public List<PostsModel> getByUserId() {
        return postsService.getByUserId();
    }

    @GetMapping
    public List<PostsModel> findAll() {
        return postsService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostsModel create(@RequestBody @Valid PostsModel postsModel) {
        return postsService.create(postsModel);
    }

    @PutMapping("/{id}")
    public PostsModel update(@PathVariable Long id, @RequestBody @Valid PostsModel postsModel) {
        return postsService.update(id, postsModel);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        postsService.delete(id);
    }
}
