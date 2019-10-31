package voting.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;
import voting.model.Cat;
import voting.repository.CatRepository;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Queue;

@Component
@SessionScope
public class CatListHolder {
    private CatRepository catRepository;
    private Queue<Cat> catsQueue;

    @Autowired
    public CatListHolder(CatRepository catRepository) {
        this.catRepository = catRepository;
        this.catsQueue = new LinkedList<>();
    }

    @PostConstruct
    private void init() {
        Iterable<Cat> all = catRepository.findAllByRand();
        for (Cat cat : all) {
            catsQueue.add(cat);
        }
        if (catsQueue.size() % 2 != 0) {
            catsQueue.add(catsQueue.peek());
        }
    }

    public Queue<Cat> getCatsQueue() {
        return catsQueue;
    }
}
