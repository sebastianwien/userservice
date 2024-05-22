package de.sebastian.wien.service;

import de.sebastian.wien.model.User;
import de.sebastian.wien.model.UserPost;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Component
public class UserServiceClient {

    public static final int TIMEOUT_IN_MILLIS = 100;

    @Value("${api.endpoint}")
    private String endpoint;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder().baseUrl(endpoint).build();
    }

    /**
     * Fetch data from external endpoint via WebClient to provide asynchronicity and handle timeouts.
     * The responses of /users/{id} and /posts?userId={id} are merged to provide a comprehensive result.
     *
     * @param id
     * @return
     */
    public Optional<User> fetchUserByIdWithPosts(Long id) {
        try {
            User user = fetchUser(id).get(TIMEOUT_IN_MILLIS, TimeUnit.MILLISECONDS);
            try {
                fetchUserPosts(id).thenAccept(posts -> user.setPosts(Arrays.asList(posts))).get(TIMEOUT_IN_MILLIS, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException | TimeoutException e) {
                log.warn(String.format("Timeout while fetching users %s posts within %s ms", id, TIMEOUT_IN_MILLIS));
            }
            return Optional.of(user);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            log.warn(String.format("Unable to fetch user %s", id));
            return Optional.empty();
        }
    }

    private CompletableFuture<User> fetchUser(Long userId) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/users/{id}").build(userId))
            .retrieve()
            .bodyToMono(User.class)
            .toFuture();
    }

    private CompletableFuture<UserPost[]> fetchUserPosts(Long userId) {
        return webClient.get()
            .uri(uriBuilder -> uriBuilder.path("/posts").queryParam("userId", userId).build())
            .retrieve()
            .bodyToMono(UserPost[].class)
            .toFuture();
    }
}
