package com.aggroConnect.api.service;


import com.aggroConnect.api.exception.EntityDeletionException;
import com.aggroConnect.api.model.Site;
import com.aggroConnect.api.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SiteService {
    SiteRepository siteRepository;

    @Autowired
    public SiteService(SiteRepository siteRepository) {
        this.siteRepository = siteRepository;
    }

    public Iterable<Site> getAllSites() {
        return siteRepository.findAll();
    }

    public Optional<Site> getSiteById(Long id) {
        return siteRepository.findById(id);
    }

    public Site createSite(Site site) {
        return siteRepository.save(site);
    }

    public Site updateSite(Long id, Site updatedSite) {
        return siteRepository.findById(id).map(site -> {
            if(updatedSite.getCity() != null) {
                site.setCity(updatedSite.getCity());
            }
            return siteRepository.save(site);
        }).orElseThrow(() -> new RuntimeException("Site not found with id: " + id));
    }

    public void deleteSite(Long id) {
        try {
            if (siteRepository.existsById(id)) {
                siteRepository.deleteById(id);
            } else {
                throw new RuntimeException("Le site n'a pas été trouvé avec cet id : " + id);
            }
        } catch (DataIntegrityViolationException e) {
            throw new EntityDeletionException("Ce site est encore affecté à des employés.");
        }
    }
}

