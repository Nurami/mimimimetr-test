package voting;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import voting.model.Cat;
import voting.repository.CatRepository;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoadDatabase {
    private CatRepository catRepository;

    @Autowired
    public LoadDatabase(CatRepository catRepository) {
        this.catRepository = catRepository;
        run();
    }

    private void run(){
        Faker faker = new Faker();
        ClassPathResource cpr;
        List<String> images = getTestImages();
        for (String image : images) {
            try {
                cpr = new ClassPathResource(image);
                byte[] data = FileCopyUtils.copyToByteArray(cpr.getInputStream());
                catRepository.save(new Cat(faker.cat().name(), data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> getTestImages(){
        List<String> list = new ArrayList<>();
        list.add("images/1.png");
        list.add("images/2.png");
        list.add("images/3.png");
        list.add("images/4.png");
        list.add("images/5.png");
        list.add("images/6.png");
        list.add("images/7.png");
        list.add("images/8.png");
        list.add("images/9.png");
        list.add("images/10.png");
        return list;
    }




}
