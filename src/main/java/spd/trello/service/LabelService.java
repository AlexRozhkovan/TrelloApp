package spd.trello.service;

import org.springframework.stereotype.Service;
import spd.trello.domain.Label;
import spd.trello.repository.LabelRepository;

@Service
public class LabelService extends AbstractService<Label, LabelRepository> {
    public LabelService(LabelRepository repository) {
        super(repository);
    }
}