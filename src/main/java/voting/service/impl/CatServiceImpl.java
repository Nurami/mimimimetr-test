package voting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;
import voting.converter.DtoConverter;
import voting.dto.CatDto;
import voting.model.Cat;
import voting.repository.CatRepository;
import voting.repository.VoteRepository;
import voting.service.CatService;
import voting.util.CatListHolder;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

@Component
public abstract class CatServiceImpl implements CatService {
    private CatListHolder catListHolder;
    private CatRepository catRepository;
    private VoteRepository voteRepository;
    private DtoConverter dtoConverter;

    @Autowired
    public CatServiceImpl(CatListHolder catListHolder, CatRepository catRepository,
                          VoteRepository voteRepository, DtoConverter dtoConverter) {
        this.catListHolder = catListHolder;
        this.catRepository = catRepository;
        this.voteRepository = voteRepository;
        this.dtoConverter = dtoConverter;
    }

    @Override
    public Queue<Cat> findAllByRand() {
        return catListHolder.getCatsQueue();
    }

    @Override
    public Iterable<Cat> findAll() {
        return catRepository.findAll();
    }

    @Override
    public List<CatDto> getTop10Cats() {
        List<CatDto> catList = new ArrayList<>();
        Iterable<Long> top10Id = voteRepository.findTop10CatsIdOrderByDesc();
        for (Object id : top10Id) {
            if (id instanceof BigInteger) {
                Long catId = ((BigInteger) id).longValue();
                catList.add(dtoConverter.getCatDtoFromCat(catRepository.findById(catId).orElse(new Cat())));
            }
        }
        return catList;
    }

    @Override
    public List<CatDto> getPairCats() {
        Cat firstCat = catListHolder.getCatsQueue().poll();
        Cat secondCat = catListHolder.getCatsQueue().poll();
        if (firstCat == null || secondCat == null) {
            return Collections.emptyList();
        }
        List<CatDto> cats = new ArrayList<>();
        cats.add(dtoConverter.getCatDtoFromCat(firstCat));
        cats.add(dtoConverter.getCatDtoFromCat(secondCat));
        return cats;
    }

    @Lookup
    public abstract CatListHolder catListHolderLookupMethod();

}
