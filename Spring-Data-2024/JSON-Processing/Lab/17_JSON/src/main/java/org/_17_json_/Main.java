package org._17_json_;

import com.google.gson.Gson;
import org._17_json_.dtos.AddressDTO;
import org._17_json_.dtos.PersonDTO;
import org._17_json_.entities.Person;
import org._17_json_.services.PersonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Main implements CommandLineRunner {
    private Gson gson;
    private PersonService personService;
    private ModelMapper modelMapper;

    public Main(@Qualifier("withoutNulls") Gson gson, @Value("${yourproject.yourkey.config1}") String config1, PersonService personService, ModelMapper modelMapper) {
        this.gson = gson;
        this.personService = personService;
        this.modelMapper = modelMapper;
    }

    @Override
    public void run(String... args) throws Exception {
//        printJson(gson);
        readJson(gson);

    }

    private void readJson(Gson gson) {
        String json = """
                [{
                  "firstName": "Lana",
                  "lastName": "DelRey",
                  "age": 28,
                  "isMarried": true,
                  "lotteryNumbers": [
                    1,
                    4,
                    6,
                    32,
                    8
                  ],
                  "address": {
                    "country": "US",
                    "city": "NYC"
                  }
                },
                {
                  "firstName": "Paul",
                  "lastName": "Sanders",
                  "age": 23,
                  "isMarried": false,
                  "lotteryNumbers": [
                    22,
                    4
                  ],
                  "address": {
                    "country": "US",
                    "city": "NYC"
                  }
                }]""";
        PersonDTO[] personDTO = gson.fromJson(json, PersonDTO[].class);
        List<Person> realPeople = new ArrayList<>();
        for (PersonDTO dto : personDTO) {
            Person person = modelMapper.map(dto, Person.class);
            realPeople.add(person);
        }
        for (Person realPerson : realPeople) {
            System.out.println(realPerson);
        }
    }

    private static void printJson(Gson gson) {
        PersonDTO personDTO = new PersonDTO("Lana", null, 28, true,
                List.of(1, 4, 6, 32, 8),
                new AddressDTO("US", "NYC"));
        String json = gson.toJson(personDTO);
        System.out.println(json);
    }
}
