package com.project.accountposting.resource;

import com.project.accountposting.dto.TitleDTO;
import com.project.accountposting.service.TitleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/title")
public class TitleResource {

    private TitleService titleService;

    @PostMapping
    public TitleDTO create(@Validated @RequestBody TitleDTO titleDTO) {
        return titleService.create(titleDTO);
    }

    @GetMapping
    public Page<TitleDTO> findAll(@RequestParam(required = false) Optional<Long> id,
                                  @RequestParam(required = false) Optional<String> situation,
                                  @RequestParam(defaultValue = "0") Integer page,
                                  @RequestParam(defaultValue = "10") Integer linesPerPage,
                                  @RequestParam(defaultValue = "expirationDate") String orderBy,
                                  @RequestParam(defaultValue = "ASC") Sort.Direction direction) {
        return titleService.findAll(id, situation, PageRequest.of(page, linesPerPage, Sort.by(direction, orderBy)));
    }

    @PutMapping("/{id}")
    public TitleDTO update(@PathVariable Long id, @Validated @RequestBody TitleDTO titleDTO) {
        return titleService.update(id, titleDTO);
    }

    //preciso verificar esse metodo ainda
//    @PutMapping("/payment{id}")
//    public TitleDTO update(@PathVariable Long id, @Validated @RequestBody TitleDTO titleDTO) {
//        return titleService.update(id, titleDTO);
//    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        titleService.delete(id);
    }
}
