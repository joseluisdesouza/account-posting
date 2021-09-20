package com.project.accountposting.service;

import com.project.accountposting.domain.Title;
import com.project.accountposting.dto.TitleDTO;
import com.project.accountposting.enums.SituationEnum;
import com.project.accountposting.repository.TitleRepository;
import com.project.accountposting.specification.TitleSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TitleService {

    private final TitleRepository titleRepository;

    /**
     * POST: para cadastro, nesse caso sempre que um título é cadastrado, deve ser registrado
     * um histórico, informando a data e o usuário que inseriu o título.
     *
     * @param titleDTO
     * @return
     *
     * "informa a data de criação do titulo";
     * "todo titulo que passa aqui ele entra como pendente";
     */
    public TitleDTO create(TitleDTO titleDTO) {
        var title = titleRepository.save(toEntity(titleDTO))
                .withCreateDate(Instant.now())
                .withSituation(SituationEnum.PENDING);
        // inserir historico
        return new TitleDTO(title);
    }

    public Page<TitleDTO> findAll(Optional<Long> id,
                                  Optional<String> situation,
                                  final PageRequest pageRequest) {
        return titleRepository.findAll(TitleSpecification.builder()
                .id(id)
                .situation(situation).build(), pageRequest).map(TitleDTO::new);
    }


    /**
     * PUT: para atualização das informações do título, nesse caso sempre que um título é
     * atualizado, deve registar o histórico, informando o usuário que alterou, e o que foi
     * alterado.
     *
     * @param id
     * @param titleDTO
     * @return
     */
    public TitleDTO update(Long id, TitleDTO titleDTO) {
        var title = getTitleById(id);
        title.setId(titleDTO.getId());
        title.setCategoryId(titleDTO.getCategoryId());
        title.setPaymentDate(titleDTO.getPaymentDate());
        title.setExpirationDate(titleDTO.getExpirationDate());
        title.setType(titleDTO.getType());
        title.setDescription(titleDTO.getDescription());
        title.setDiscount(titleDTO.getDiscount());
        title.setValue(titleDTO.getValue());

        //tem que salvar o historico

        return new TitleDTO(titleRepository.save(title));
    }

    /**
     * PUT(“/payment”): nesse caso será feito o pagamento do título, onde será atualizada a
     * situação de “PENDENTE” para “PAGO”
     */





    /**
     * DELETE: para desativação do registro, note que nesse caso vamos passar o valor “CANCEL”
     * para o atributo “situation"
     *
     * desafio: identificar uma situação onde o titulo não pode ser cancelado
     *
     * @param id
     */
    public TitleDTO deactivation(Long id, TitleDTO titleDTO) {
        var title = getTitleById(id);
        title.setSituation(SituationEnum.CANCELED);
        return new TitleDTO(titleRepository.save(title));
    }

    private Title getTitleById(final Long id) {
        return titleRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    private Title toEntity(TitleDTO titleDTO) {
        return Title.builder()
                .id(titleDTO.getId())
                .categoryId(titleDTO.getCategoryId())
                .paymentDate(titleDTO.getPaymentDate())
                .expirationDate(titleDTO.getExpirationDate())
                .build();
    }
}
