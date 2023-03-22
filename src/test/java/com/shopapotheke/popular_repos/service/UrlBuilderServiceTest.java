package com.shopapotheke.popular_repos.service;

/**
 * @author mmehrotra
 */

import com.shopapotheke.popular_repos.service.impl.UrlBuilderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UrlBuilderServiceTest {

    @InjectMocks
    private UrlBuilderService urlBuilderService = new UrlBuilderServiceImpl();

    @Test
    public void buildUrlFromParams() {
        String language = "java";
        LocalDate createdSince = LocalDate.of(2022, 1, 1);
        String sort = "stars";
        String order = "desc";
        Integer perPage = 50;

        String expectedUrl = "https://api.github.com/search/repositories?q=stars:>0+language:java+created:>2022-01-01&sort=stars&order=desc&per_page=50";
        String actualUrl = urlBuilderService.buildUrlFromParams(language, createdSince, sort, order, perPage);

        assertNotNull(actualUrl);
        assertEquals(expectedUrl, actualUrl);
    }
}
