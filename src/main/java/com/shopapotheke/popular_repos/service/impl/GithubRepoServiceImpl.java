package com.shopapotheke.popular_repos.service.impl;

import com.shopapotheke.popular_repos.controller.GithubRepoController;
import com.shopapotheke.popular_repos.model.GithubApiResponse;
import com.shopapotheke.popular_repos.model.GithubRepo;
import com.shopapotheke.popular_repos.service.GithubRepoService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

/**
 * @author mmehrotra
 */

@Service
public class GithubRepoServiceImpl implements GithubRepoService {

    private final RestTemplate restTemplate;

    private static final Logger logger = LogManager.getLogger(GithubRepoServiceImpl.class);

    public GithubRepoServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<GithubRepo> findPopularRepositories(String githubApiUrl) {

        try {
            ResponseEntity<GithubApiResponse> response = restTemplate.exchange(
                    githubApiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<GithubApiResponse>() {}
            );
            if (response.getStatusCode().is2xxSuccessful()) {
                return response.getBody().getItems();
            } else {
                logger.error("Failed to fetch popular repositories with status code {}", response.getStatusCode());
                return Collections.emptyList();
            }
        } catch (RestClientException e) {
            logger.error("Failed to fetch popular repositories: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
}