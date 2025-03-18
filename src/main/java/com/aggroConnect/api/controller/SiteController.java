package com.aggroConnect.api.controller;

import com.aggroConnect.api.dto.ApiResponse;
import com.aggroConnect.api.model.Site;
import com.aggroConnect.api.service.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/site")
public class SiteController {
    private final SiteService siteService;

    @Autowired
    public SiteController(SiteService siteService) {
        this.siteService = siteService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Iterable<Site>>> getSites() {
        Iterable<Site> sites = siteService.getAllSites();
        return ResponseEntity.ok(new ApiResponse<>(200,"Liste des sites récupérée avec succès", sites));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Site>> getSite(@PathVariable long id) {
        Site site = siteService.getSiteById(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Site trouvé", site));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Site>> createSite(@RequestBody Site site) {
        Site createdSite = siteService.createSite(site);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(201, "Site créé avec succès", createdSite));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Site>> updateSite(@PathVariable long id, @RequestBody Site site) {
        Site updatedSite = siteService.updateSite(id, site);
        return ResponseEntity.ok(new ApiResponse<>(200, "Site mis à jour avec succès", updatedSite));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteSite(@PathVariable long id) {
        siteService.deleteSite(id);
        return ResponseEntity.ok(new ApiResponse<>(200, "Site supprimé avec succès", null));
    }
}
