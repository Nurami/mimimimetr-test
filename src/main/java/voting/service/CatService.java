package voting.service;

import voting.dto.CatDto;
import voting.model.Cat;

import java.util.List;
import java.util.Queue;

public interface CatService {
    Queue<Cat> findAllByRand();

    Iterable<Cat> findAll();

    List<CatDto> getTop10Cats();

    List<CatDto> getPairCats();
}
