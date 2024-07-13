package com.example.cardealer.services;

import com.example.cardealer.model.entity.Part;

import java.io.IOException;

public interface PartService {
    void seedParts() throws IOException;
    Part getRandomPart();
}
