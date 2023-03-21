package com.shopapotheke.popular_repos.service.impl;

import com.shopapotheke.popular_repos.model.GithubApiResponse;
import com.shopapotheke.popular_repos.model.GithubRepo;
import com.shopapotheke.popular_repos.service.GithubRepoService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author mmehrotra
 */

@Service
public class GithubRepoServiceImpl implements GithubRepoService {

    RestTemplate restTemplate;

    public GithubRepoServiceImpl(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @Override
    public List<GithubRepo> findPopularRepositories(String githubApiUrl) {

        ResponseEntity<GithubApiResponse> response = restTemplate.getForEntity(githubApiUrl,GithubApiResponse.class);

        return response.getBody().getItems();

    }
}
