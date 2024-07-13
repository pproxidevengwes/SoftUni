package org._17_json_.dtos;

import com.google.gson.annotations.Expose;

import java.util.List;

public class PersonDTO {
    @Expose
    private String firstName;
    private String lastName;
    private int age;
    @Expose
    private boolean isMarried;

    private List<Integer> lotteryNumbers;

    private AddressDTO address;

    public PersonDTO(String firstName, String lastName, int age, boolean isMarried, List<Integer> lotteryNumbers, AddressDTO address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.isMarried = isMarried;
        this.lotteryNumbers = lotteryNumbers;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMarried() {
        return isMarried;
    }

    public void setMarried(boolean married) {
        isMarried = married;
    }

    public List<Integer> getLotteryNumbers() {
        return lotteryNumbers;
    }

    public void setLotteryNumbers(List<Integer> lotteryNumbers) {
        this.lotteryNumbers = lotteryNumbers;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public void validate(){}

    @Override
    public String toString() {
        return "PersonDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", isMarried=" + isMarried +
                ", lotteryNumbers=" + lotteryNumbers +
                ", address=" + address +
                '}';
    }
}
