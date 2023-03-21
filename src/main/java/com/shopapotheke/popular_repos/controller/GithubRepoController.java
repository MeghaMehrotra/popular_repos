package com.shopapotheke.popular_repos.controller;

import com.shopapotheke.popular_repos.model.GithubRepo;
import com.shopapotheke.popular_repos.service.GithubRepoService;
import com.shopapotheke.popular_repos.service.UrlBuilderService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

/**
 * @author mmehrotra
 */

@RestController
public class GithubRepoController {

    private static final Logger logger = LogManager.getLogger(GithubRepoController.class);
    private final GithubRepoService githubRepoService;
    private final UrlBuilderService urlBuilderService;

    public GithubRepoController(GithubRepoService githubRepoService, UrlBuilderService urlBuilderService) {
        this.githubRepoService = githubRepoService;
        this.urlBuilderService = urlBuilderService;
    }

    @GetMapping("/repos/popular")
    public ResponseEntity<List<GithubRepo>> getPopularGithubRepos(@Validated @RequestParam(defaultValue = "10") int perPage,
                                                                  @RequestParam(defaultValue = "stars") String sort,
                                                                  @RequestParam(defaultValue = "desc") String order,
                                                                  @RequestParam(required = false) String language,
                                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdSince
    ) {
        String githubApiUrl = urlBuilderService.buildUrlFromParams(language,createdSince,sort,order,perPage);
        if(!githubApiUrl.isEmpty()){
            List<GithubRepo> githubRepos = githubRepoService.findPopularRepositories(githubApiUrl);
            if (githubRepos.isEmpty()) {
                logger.debug("Repos not found", HttpStatus.NOT_FOUND);
                return ResponseEntity.notFound().build();
            }
            logger.info("Returning list of githubRepos with status code {}", HttpStatus.OK);
            return ResponseEntity.ok(githubRepos);
        }
            return ResponseEntity.notFound().build();
    }

}
