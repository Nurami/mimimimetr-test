package voting.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import voting.model.Cat;
import voting.model.Vote;
import voting.repository.CatRepository;
import voting.repository.VoteRepository;
import voting.service.VoteService;

@Component
public class VoteServiceImpl implements VoteService {
    private CatRepository catRepository;
    private VoteRepository voteRepository;

    @Autowired
    public VoteServiceImpl(CatRepository catRepository, VoteRepository voteRepository) {
        this.catRepository = catRepository;
        this.voteRepository = voteRepository;
    }

    @Override
    public void save(Long catId) {
        Vote vote = new Vote();
        vote.setCat(catRepository.findById(catId).orElse(new Cat()));
        voteRepository.save(vote);
    }
}
