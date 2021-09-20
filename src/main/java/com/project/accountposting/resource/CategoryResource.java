package com.project.accountposting.resource;

import com.project.accountposting.dto.CategoryDTO;
import com.project.accountposting.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/categorys")
public class CategoryResource {

    private CategoryService categoryService;

    @PostMapping
    public CategoryDTO create(@Validated @RequestBody CategoryDTO categoryDTO) {
        return categoryService.create(categoryDTO);
    }

    @GetMapping
    public Page<CategoryDTO> findAll(@RequestParam(required = false) Optional<Long> id,
                                     @RequestParam(required = false) Optional<String> description,
                                     @RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(defaultValue = "10") Integer linesPerPage,
                                     @RequestParam(defaultValue = "description") String orderBy,
                                     @RequestParam(defaultValue = "ASC") Sort.Direction direction) {

        return categoryService.findAll(id, description, PageRequest.of(page, linesPerPage, Sort.by(direction, orderBy)));
    }

    @PutMapping("/{id}")
    public CategoryDTO update(@PathVariable Long id, @Validated @RequestBody CategoryDTO categoryDTO) {
        return categoryService.update(id, categoryDTO);
    }

    @PutMapping("/{id}")
    public void deactivation(@PathVariable Long id, @Validated @RequestBody CategoryDTO categoryDTO) {
        categoryService.deactivation(id, categoryDTO);
    }
}
