package com.gaos.gaos.web.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;

import com.gaos.gaos.domain.services.AuthService;
import com.gaos.gaos.persistence.entity.Auth;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auths")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("")
    public ResponseEntity<Auth> createAuth(@RequestBody Auth auth) {
        return new ResponseEntity<>(authService.createAuth(auth), HttpStatus.CREATED);
    }    

    @GetMapping("")
    public ResponseEntity<List<Auth>> allAuths() {
        return new ResponseEntity<>(authService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auth> getAuth(@PathVariable("id") int authId) {
        return authService.getById(authId)
                .map(auth -> new ResponseEntity<>(auth, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auth> updateAuth(@PathVariable int authId, @RequestBody Auth newAuth) {
        return authService.updateAuth(authId, newAuth)
                .map(authUpdated -> new ResponseEntity<>(authUpdated, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuth(@PathVariable int authId) {
        if (authService.deleteAuth(authId)) {
            return new ResponseEntity<Void>(HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }
}
