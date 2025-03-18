package com.aggroConnect.api.service;

import com.aggroConnect.api.exception.EntityDeletionException;
import com.aggroConnect.api.exception.ResourceNotFoundException;
import com.aggroConnect.api.model.Site;
import com.aggroConnect.api.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class SiteService {
    private final SiteRepository siteRepository;

    @Autowired
    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public Iterable<Site> getAllSites() {
        return siteRepository.findAll();
    }

    public Site getSiteById(Long id) {
        return siteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ce site", id));
    }

    public Site createSite(Site site) {
        return siteRepository.save(site);
    }

    public Site updateSite(Long id, Site updatedSite) {
        Site existingSite = siteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ce site", id));

        if (updatedSite.getCity() != null) {
            existingSite.setCity(updatedSite.getCity());
        }

        return siteRepository.save(existingSite);
    }

    public void deleteSite(Long id) {
        Site site = siteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ce site", id));

        try {
            siteRepository.delete(site);
        } catch (DataIntegrityViolationException e) {
            throw new EntityDeletionException("Ce site est encore affecté à des employés.");
        }
    }
}
