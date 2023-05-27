    package com.nidyran.rolebasedspringsecurity.web;

    import com.nidyran.rolebasedspringsecurity.enmus.AuthorityEnum;
    import com.nidyran.rolebasedspringsecurity.service.AuthenticationService;
    import com.nidyran.rolebasedspringsecurity.service.model.category.CategoryDto;
    import com.nidyran.rolebasedspringsecurity.service.model.user.*;
    import io.swagger.v3.oas.annotations.tags.Tag;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequiredArgsConstructor
    @Tag(name = "Authentication Resource")
    @RequestMapping("/authentication-management")
    public class AuthenticationController {
        private final AuthenticationService authenticationService;

        @PostMapping("/login")
        public ResponseEntity<LoginResponseDto> login(@RequestBody final LoginRequestDto loginRequestDto) {
            return ResponseEntity.ok(authenticationService.login(loginRequestDto));
        }

        @PostMapping("/register")
        public ResponseEntity<RegisterResponseDto> register(@RequestBody final RegisterRequestDto registerRequestDto) {
            return ResponseEntity.ok(authenticationService.register(registerRequestDto));
        }
        @PutMapping("/{id}/authority")
        public ResponseEntity<UserDto> updateAuthority(@PathVariable("id") long id, @RequestBody boolean auth) {
            return ResponseEntity.ok(authenticationService.updateAuthByUserName(id,auth));
        }


    }
