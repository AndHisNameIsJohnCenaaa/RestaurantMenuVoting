package com.artamonov.restaurantmenuvoting.service;

import com.artamonov.restaurantmenuvoting.model.User;
import com.artamonov.restaurantmenuvoting.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

import static com.artamonov.restaurantmenuvoting.util.ValidationUtil.checkNotFound;
import static com.artamonov.restaurantmenuvoting.util.ValidationUtil.checkNotFoundWithId;

@Service
@AllArgsConstructor
public class UserService {

    private static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC, "name", "email");

    private final UserRepository crudUserRepository;


    @CacheEvict(value = "users", allEntries = true)
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
        return crudUserRepository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void delete(int id) {
        checkNotFoundWithId(crudUserRepository.delete(id) != 0, id);
    }

    public User get(int id) {
        return checkNotFoundWithId(crudUserRepository.findById(id).orElse(null), id);
    }

    public User getByEmail(String email) {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(crudUserRepository.getByEmail(email), "email=" + email);
    }

    @Cacheable("users")
    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }

    @CacheEvict(value = "users", allEntries = true)
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        checkNotFoundWithId(crudUserRepository.save(user), user.id());
    }
}
