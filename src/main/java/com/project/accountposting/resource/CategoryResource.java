package com.project.accountposting.resource;

import com.project.accountposting.dto.CategoryDTO;
import com.project.accountposting.service.CategoryService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryResource {

    private CategoryService categoryService;

    @PostMapping
    public CategoryDTO create(@Validated @RequestBody CategoryDTO categoryDTO) {
        return categoryService.create(categoryDTO);
    }

    @GetMapping
    public Optional<CategoryDTO> findAll(@RequestParam(required = false) Optional<Long> id,
                                         @RequestParam(required = false) Optional<String> description,
                                         @RequestParam(defaultValue = "0") Integer page,
                                         @RequestParam(defaultValue = "10") Integer linesPerPage,
                                         @RequestParam(defaultValue = "description") String orderBy,
                                         @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        return categoryService.findAll(id, description, PageRequest.of(page, linesPerPage, Sort.by(direction, orderBy)));
    }

    @PutMapping
    public CategoryDTO update(@PathVariable Long id, @Validated @RequestBody CategoryDTO categoryDTO) {
        return categoryService.update(id, categoryDTO);
    }

    @DeleteMapping
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
