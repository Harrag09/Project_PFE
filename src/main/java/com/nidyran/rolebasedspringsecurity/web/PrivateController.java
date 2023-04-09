package com.nidyran.rolebasedspringsecurity.web;

import com.nidyran.rolebasedspringsecurity.utils.BackendUtils;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/private-ressources")
@Tag(name = "Private Resource")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class PrivateController {

    @GetMapping("/")
    public ResponseEntity<String> privateRessource() {
        return ResponseEntity.ok(BackendUtils.getCurrentUsername());
    }
}