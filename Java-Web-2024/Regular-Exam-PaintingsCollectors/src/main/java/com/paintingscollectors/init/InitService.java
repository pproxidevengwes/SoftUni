package com.paintingscollectors.init;

import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.StyleType;
import com.paintingscollectors.repository.StyleRepository;
//import com.plannerapp.model.entity.Priority;
//import com.plannerapp.model.entity.PriorityType;
//import com.plannerapp.repo.PriorityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Component
public class InitService implements CommandLineRunner {
    private final StyleRepository styleRepository;

    public InitService(StyleRepository styleRepository) {
        this.styleRepository = styleRepository;
    }

    private final Map<StyleType, String> descriptions = Map.of(
            StyleType.IMPRESSIONISM, "Impressionism is a painting style most commonly associated with the 19th century where small brush strokes are used to build up a larger picture.",
            StyleType.ABSTRACT, "Abstract art does not attempt to represent recognizable subjects in a realistic manner. ",
            StyleType.EXPRESSIONISM, "Expressionism is a style of art that doesn't concern itself with realism.",
            StyleType.SURREALISM, "Surrealism is characterized by dreamlike, fantastical imagery that often defies logical explanation.",
            StyleType.REALISM, "Also known as naturalism, this style of art is considered as 'real art' and has been the dominant style of painting since the Renaissance."
    );


    @Override
    public void run(String... args) throws Exception {
        if (this.styleRepository.count() > 0) {
            return;
        }
        List<Style> stileDescriptions = Arrays.stream(StyleType.values())
                .map(s -> new Style(s, descriptions.get(s)))
                .toList();

        this.styleRepository.saveAll(stileDescriptions);

    }
}
