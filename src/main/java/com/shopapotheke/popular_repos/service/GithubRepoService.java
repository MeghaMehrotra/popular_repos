package com.shopapotheke.popular_repos.service;

import com.shopapotheke.popular_repos.model.GithubRepo;

import java.util.List;

/**
 * @author mmehrotra
 */

public interface GithubRepoService {

    List<GithubRepo> findPopularRepositories(String githubApiUrl);
}
