package com.project.accountposting.service;

import com.project.accountposting.domain.Title;
import com.project.accountposting.dto.TitleDTO;
import com.project.accountposting.repository.TitleRepository;
import com.project.accountposting.specification.TitleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TitleService {

    private final TitleRepository titleRepository;

    public TitleDTO create(TitleDTO titleDTO) {
        return new TitleDTO(titleRepository.save(toEntity(titleDTO)));
    }

    public Page<TitleDTO> findAll(Optional<Long> id,
                                  Optional<String> situation,
                                  final PageRequest pageRequest) {
        return titleRepository.findAll(TitleSpecification.builder()
                .id(id)
                .situation(situation).build(), pageRequest).map(TitleDTO::new);
    }

    public TitleDTO update(Long id, TitleDTO titleDTO) {
        var title = getTitleById(id);
        title.setId(titleDTO.getId());
        title.setCategoryId(titleDTO.getCategoryId());
        title.setCreateDate(titleDTO.getCreateDate());
        title.setPaymentDate(titleDTO.getPaymentDate());
        title.setExpirationDate(titleDTO.getExpirationDate());
        title.setType(titleDTO.getType());
        title.setSituation(titleDTO.getSituation());
        title.setDescription(titleDTO.getDescription());
        title.setDiscount(titleDTO.getDiscount());
        title.setValue(titleDTO.getValue());
        return new TitleDTO(titleRepository.save(title));
    }

    public void delete(Long id) {
        getTitleById(id);
        titleRepository.deleteById(id);
    }

    private Title getTitleById(final Long id) {
        return titleRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private Title toEntity(TitleDTO titleDTO) {
        return Title.builder()
                .id(titleDTO.getId())
                .categoryId(titleDTO.getCategoryId())
                .createDate(titleDTO.getCreateDate())
                .paymentDate(titleDTO.getPaymentDate())
                .expirationDate(titleDTO.getExpirationDate())
                .build();
    }
}
