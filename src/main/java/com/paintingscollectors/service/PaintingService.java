package com.paintingscollectors.service;

import com.paintingscollectors.config.UserSession;
import com.paintingscollectors.model.dto.AddPaintingDTO;
import com.paintingscollectors.model.dto.PaintingDisplayDTO;
import com.paintingscollectors.model.entity.Painting;
import com.paintingscollectors.model.entity.Style;
import com.paintingscollectors.model.entity.User;
import com.paintingscollectors.repository.PaintingRepository;
import com.paintingscollectors.repository.StyleRepository;
import com.paintingscollectors.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaintingService {
    private final UserSession userSession;
    private final PaintingRepository paintingRepository;
    private final StyleRepository styleRepository;
    private final UserRepository userRepository;

    public PaintingService(UserSession userSession, PaintingRepository paintingRepository, StyleRepository styleRepository, UserRepository userRepository) {
        this.userSession = userSession;
        this.paintingRepository = paintingRepository;
        this.styleRepository = styleRepository;
        this.userRepository = userRepository;
    }

    public boolean addPainting(AddPaintingDTO paintingDTO, long userId) {
        if (!this.userSession.isLoggedIn()) {
            return false;
        }

        User user = userRepository.findById(userId).orElse(null);
        Style style = styleRepository.findByName(paintingDTO.getStyle()).orElse(null);

        Painting painting = new Painting();
        painting.setName(paintingDTO.getName());
        painting.setAuthor(paintingDTO.getAuthor());
        painting.setImageUrl(paintingDTO.getImageUrl());
        painting.setStyle(style);
        painting.setOwner(user);

        this.paintingRepository.save(painting);
        user.getPaintings().add(painting);
        this.userRepository.save(user);


        return true;
    }


    public List<PaintingDisplayDTO> getPaintingsForUser(Long userId) {
        return paintingRepository.findByOwnerId(userId)
                .stream()
                .map(PaintingDisplayDTO::new)
                .collect(Collectors.toList());

    }

    public List<PaintingDisplayDTO> getPaintingsForOtherUsers(Long userId) {
        return paintingRepository.findAll()
                .stream()
                .filter(p -> p.getOwner().getId() != userId)
                .map(PaintingDisplayDTO::new)
                .collect(Collectors.toList());

    }

    public boolean removePainting(Long paintingId) {
        Optional<Painting> optionalPainting = this.paintingRepository.findById(paintingId);

        if (optionalPainting.isEmpty()) {
            return false;
        }

        Painting painting = optionalPainting.get();
        if (!painting.isFavourite()) {
            return false;
        }
        User user = painting.getOwner();
        List<Painting> userPaintings = user.getPaintings();
        userPaintings.remove(optionalPainting);
        this.paintingRepository.deleteById(paintingId);
        return true;
    }

    public void addToFavorites(long userId, long paintingId) {
        Painting painting = paintingRepository.findById(paintingId).orElse(null);
        User currentUser = userRepository.getById(userId);
        painting.setFavourite(true);
        currentUser.getFavouritePaintings().add(painting);
        paintingRepository.save(painting);
        userRepository.save(currentUser);
    }

    @Transactional
    public void removeFromFavorites(long userId, long paintingId) {
        Painting painting = paintingRepository.findById(paintingId).orElse(null);
        User currentUser = userRepository.getById(userId);
        currentUser.getFavouritePaintings().remove(painting);
        painting.setFavourite(false);
        userRepository.save(currentUser);
        paintingRepository.save(painting);

    }

    public void deletePainting(long id) {
        Optional<Painting> byId = paintingRepository.findById(id);
        if (byId.isEmpty()) {
            return;
        }
        Painting painting = byId.get();
        if (painting.isFavourite()) {
            return;
        }
        userRepository.findAll().stream().map(User::getRatedPaintings)
                .filter(p -> p.contains(painting))
                .forEach(p -> p.remove(painting));

        paintingRepository.delete(painting);
    }

    public void vote(long userId, long paintingId) {
        User user = userRepository.findById(userId).orElse(null);
        Painting painting = paintingRepository.findById(paintingId).orElse(null);
        if (user.getRatedPaintings().contains(painting)) {
            return;
        }
        painting.setVotes(painting.getVotes() + 1);
        user.getRatedPaintings().add(painting);
        paintingRepository.save(painting);
        userRepository.save(user);
    }

    public Set<PaintingDisplayDTO> getMostRated() {
        return paintingRepository.findAll()
                .stream()
                .sorted(Comparator.comparing(Painting::getVotes)
                        .reversed()
                        .thenComparing(Painting::getName)
                        .reversed())
                .limit(2)
                .map(PaintingDisplayDTO::new)
                .collect(Collectors.toSet());
    }
}

