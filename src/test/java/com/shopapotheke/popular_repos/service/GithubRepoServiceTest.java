package com.shopapotheke.popular_repos.service;

import com.shopapotheke.popular_repos.model.GithubApiResponse;
import com.shopapotheke.popular_repos.model.GithubRepo;
import com.shopapotheke.popular_repos.service.impl.GithubRepoServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * @author mmehrotra
 */
@RunWith(MockitoJUnitRunner.class)
public class GithubRepoServiceTest {



    @Mock
    private RestTemplate restTemplate = new RestTemplate();


    @Test
    public void testFindPopularRepositories_Success() {
        GithubApiResponse response = new GithubApiResponse();
        List<GithubRepo> items = Collections.singletonList(new GithubRepo("testRepo","https://test_url.com"));
        response.setItems(items);
        ResponseEntity<GithubApiResponse> responseEntity = new ResponseEntity<>(response, HttpStatus.OK);
        String githubApiUrl = "https://api.github.com/testurl";

        Mockito.when(restTemplate.exchange(eq(githubApiUrl), eq(HttpMethod.GET), any(), eq(new ParameterizedTypeReference<GithubApiResponse>() {})))
                .thenReturn(responseEntity);

         GithubRepoService githubRepoService = new GithubRepoServiceImpl(restTemplate);
        // Act
        List<GithubRepo> popularRepos = githubRepoService.findPopularRepositories(githubApiUrl);

        // Assert
        assertEquals(items, popularRepos);
    }

    @Test
    public void testFindPopularRepositories_Failure() {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ResponseEntity<GithubApiResponse> responseEntity = new ResponseEntity<>(httpStatus);
        String githubApiUrl = "https://api.github.com/repositories";

        Mockito.when(restTemplate.exchange(eq(githubApiUrl), eq(HttpMethod.GET), any(), eq(new ParameterizedTypeReference<GithubApiResponse>() {})))
                .thenReturn(responseEntity);


        GithubRepoService githubRepoService = new GithubRepoServiceImpl(restTemplate);

        List<GithubRepo> popularRepos = githubRepoService.findPopularRepositories(githubApiUrl);

        assertTrue(popularRepos.isEmpty());
    }

    @Test
    public void testFindPopularRepositories_Exception() {
        RestClientException exception = new RestClientException("Error occurred while fetching data");
        String githubApiUrl = "https://api.github.com/repositories";

        Mockito.when(restTemplate.exchange(eq(githubApiUrl), eq(HttpMethod.GET), any(), eq(new ParameterizedTypeReference<GithubApiResponse>() {})))
                .thenThrow(exception);

        GithubRepoService githubRepoService = new GithubRepoServiceImpl(restTemplate);

        List<GithubRepo> popularRepos = githubRepoService.findPopularRepositories(githubApiUrl);

        assertTrue(popularRepos.isEmpty());
    }
}
