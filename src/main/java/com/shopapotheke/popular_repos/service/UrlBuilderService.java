package com.shopapotheke.popular_repos.service;

import java.time.LocalDate;

/**
 * @author mmehrotra
 */
public interface UrlBuilderService {

    String buildUrlFromParams(String language, LocalDate createdSince, String sort, String order, Integer perPage);
}
