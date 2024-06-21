package org.auto_mapping_obj;


import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;

public class Main {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();

        Address address = new Address("Bulgaria", "Sofia");
        Person person = new Person("Joro", "Georgiev", "2001-02-04", address);

        PropertyMap<Person, PersonInfoDto> personToInfoDto = new PropertyMap<Person, PersonInfoDto>() {
            @Override
            protected void configure() {
                map().setBirthdate(source.getBirthday());
            }
        };
        modelMapper.addMappings(personToInfoDto);
        PersonInfoDto dto = modelMapper.map(person, PersonInfoDto.class);

        TypeMap<PersonInfoDto, Person> dtoToPersonMap = modelMapper.createTypeMap(PersonInfoDto.class, Person.class);
        dtoToPersonMap.addMappings(mapping -> mapping.map(
                PersonInfoDto::getBirthdate, (Person::setBirthday)));
        Person fromMap = dtoToPersonMap.map(dto);

        Person personFromDto = modelMapper.map(dto, Person.class);
        System.out.println();


    }
}

class PersonInfoDto {
    private String firstName;
    private String lastName;
    private String birthdate;

    private String addressCity;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

class Person {
    private String firstName;
    private String lastName;
    private String birthday;

    private Address address;

    public Person() {
    }

    public Person(String firstName, String lastName, String birthday, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}

class Address {
    private String country;

    private String city;

    public Address() {
    }

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
