package com.shopapotheke.popular_repos.controller;

import com.shopapotheke.popular_repos.model.GithubRepo;
import com.shopapotheke.popular_repos.service.GithubRepoService;
import com.shopapotheke.popular_repos.service.UrlBuilderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * @author mmehrotra
 */
@RunWith(MockitoJUnitRunner.class)
public class GithubRepoControllerTest {

    @Mock
    private GithubRepoService githubRepoService;

    @Mock
    private UrlBuilderService urlBuilderService;

    @InjectMocks
    private GithubRepoController githubRepoController;

    @Test
    public void getPopularGithubReposTest() {
        // Set up test data
        List<GithubRepo> expectedRepos = new ArrayList<>();
        expectedRepos.add(new GithubRepo("repo1", "https://github.com/repo1"));
        expectedRepos.add(new GithubRepo("repo2", "https://github.com/repo2"));

        String expectedUrl = UriComponentsBuilder.fromHttpUrl("https://api.github.com/search/repositories")
                .queryParam("q", "stars:>0")
                .queryParam("sort", "stars")
                .queryParam("order", "desc")
                .queryParam("per_page", 10)
                .build().toUri().toString();

        // Set up mock objects
        when(githubRepoService.findPopularRepositories(expectedUrl)).thenReturn(expectedRepos);
        when(urlBuilderService.buildUrlFromParams("Java", LocalDate.of(2019, 1, 11), "stars", "desc", 10))
                .thenReturn(expectedUrl);

        // Send request to controller
        ResponseEntity<List<GithubRepo>> actualResponse = githubRepoController.getPopularGithubRepos(10, "stars", "desc",
                "Java", LocalDate.of(2019, 1, 11));

        // Verify response
        assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
        assertEquals(expectedRepos, actualResponse.getBody());

}
}

