package com.artamonov.restaurantmenuvoting.service;

import com.artamonov.restaurantmenuvoting.model.Dish;
import com.artamonov.restaurantmenuvoting.model.Restaurant;
import com.artamonov.restaurantmenuvoting.model.User;
import com.artamonov.restaurantmenuvoting.model.Vote;
import com.artamonov.restaurantmenuvoting.repository.RestaurantRepository;
import com.artamonov.restaurantmenuvoting.repository.UserRepository;
import com.artamonov.restaurantmenuvoting.repository.VoteRepository;
import com.artamonov.restaurantmenuvoting.util.exception.NotFoundException;
import com.artamonov.restaurantmenuvoting.util.exception.VoteBorderTimePassException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.artamonov.restaurantmenuvoting.util.ValidationUtil.checkNotFound;
import static com.artamonov.restaurantmenuvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class VoteService {

    protected final Logger log = LoggerFactory.getLogger(getClass());
    private static final LocalTime BORDER_TIME = LocalTime.of(11, 0);

    private VoteRepository voteRepository;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    @Transactional
    public Vote vote(int userId, int restaurantId) {
        User user = checkNotFoundWithId(userRepository.getReferenceById(userId), userId);
        Restaurant restaurant = checkNotFoundWithId(restaurantRepository.getReferenceById(restaurantId), restaurantId);
        LocalDateTime currentDateTime = LocalDateTime.now();
            try {
                Vote vote = get(userId, currentDateTime.toLocalDate());
                if (currentDateTime.toLocalTime().isAfter(BORDER_TIME)) {
                    throw new VoteBorderTimePassException("You can't change your vote after 11:00 a.m.");
                } else {
                    if (vote.getRestaurant().id() != restaurantId) {
                        log.debug("vote from user {} with restaurant {} change to restaurant {}", userId, vote.getRestaurant().id(), restaurantId);
                        vote.setRestaurant(restaurant);
                        return voteRepository.save(vote);
                    }
                    log.debug("vote from user {} - no changes");
                    return vote;
                }
            } catch (NotFoundException e) {
                log.debug("new vote from user {} to restaurant {}", userId, restaurantId);
                return voteRepository.save(new Vote(currentDateTime.toLocalDate(), user, restaurant));
            }
    }

    private Vote get(int userId, LocalDate cast) {
        return checkNotFound(voteRepository.findByUserIdAndCast(userId,  cast), "userId=" + userId + "cast=" + cast.toString());
    }
}
