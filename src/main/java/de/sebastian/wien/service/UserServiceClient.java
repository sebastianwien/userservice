package de.sebastian.wien.service;

import de.sebastian.wien.model.User;
import de.sebastian.wien.model.UserPost;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

@Slf4j
@Component
public class UserServiceClient {

    @Value("${api.endpoint}")
    private String endpoint;

    private WebClient webClient;

    @PostConstruct
    public void init() {
        webClient = WebClient.builder().baseUrl(endpoint).build();
    }

    /**
     * Fetch data from external endpoint via WebClient to provide asynchronicity and timeouts.
     * The responses of /users/{id} and /posts?userId={id} are merged to provide a comprehensive result.
     *
     * @param id
     * @return
     */
    public Optional<User> fetchUserByIdWithPosts(Long id) {
        try {
            User fetchedUser = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/users/{id}").build(id))
                .retrieve()
                .bodyToMono(User.class)
                .block(Duration.ofSeconds(5));

            if (fetchedUser != null) {
                UserPost[] postsArray = webClient.get()
                    .uri(uriBuilder -> uriBuilder.path("/posts").queryParam("userId", id).build())
                    .retrieve()
                    .bodyToMono(UserPost[].class)
                    .block(Duration.ofSeconds(5));

                if (postsArray != null) {
                    fetchedUser.setPosts(Arrays.asList(postsArray));
                }
            }
            return Optional.ofNullable(fetchedUser);
        } catch (WebClientResponseException e) {
            return Optional.empty();
        }
    }
}
