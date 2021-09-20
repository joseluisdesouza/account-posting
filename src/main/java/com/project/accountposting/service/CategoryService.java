package com.project.accountposting.service;

import com.project.accountposting.domain.Category;
import com.project.accountposting.dto.CategoryDTO;
import com.project.accountposting.repository.CategoryRepository;
import com.project.accountposting.specification.CategorySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDTO create(CategoryDTO categoryDTO) {
        return new CategoryDTO(categoryRepository.save(toEntity(categoryDTO))
                .withActivated(1));
    }

    public Page<CategoryDTO> findAll(Optional<Long> id,
                                     Optional<String> description,
                                     PageRequest pageRequest) {
        return categoryRepository.findAll(CategorySpecification.builder()
                .id(id)
                .description(description)
                .build(), pageRequest).map(CategoryDTO::new);
    }

    public CategoryDTO update(Long id, CategoryDTO categoryDTO) {
        var category = getCategoryById(id);
        category.setId(categoryDTO.getId());
        category.setDescription(categoryDTO.getDescription());
        return new CategoryDTO(categoryRepository.save(category));
    }

    /**
     * Para a especificação funcional esta errado
     * DELETE: para desativação do registro, note que nesse caso vamos passar o valor 0 para o
     * atributo “activated”
     *
     * @param id
     */
    public CategoryDTO deactivation(final Long id, CategoryDTO categoryDTO) {
        var category = getCategoryById(id);
        categoryDTO.setActivated(0);
        return new CategoryDTO(categoryRepository.save(category));
    }

    private Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private Category toEntity(CategoryDTO categoryDTO) {
        return Category.builder()
                .id(categoryDTO.getId())
                .activated(categoryDTO.getActivated())
                .description(categoryDTO.getDescription())
                .build();
    }
}
