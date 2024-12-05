package se.ifmo.ru.firstservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.ifmo.ru.firstservice.model.Nomination;
import se.ifmo.ru.firstservice.repositories.NominationRepository;


@Service
//@RibbonClient(name = "basic", configuration = RibbonClientConfig.class)
public class NominationService {
    @Autowired
    private NominationRepository nominationRepository;

    public Nomination createNomination(Nomination nomination) {
        return nominationRepository.save(nomination);
    }
}
