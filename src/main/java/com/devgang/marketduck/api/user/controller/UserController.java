package com.devgang.marketduck.api.user.controller;

import com.devgang.marketduck.api.user.dto.UserResponseDto;
import com.devgang.marketduck.domain.user.entity.User;
import com.devgang.marketduck.dto.PageResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController implements UserControllerIfs{
    @Override
    @GetMapping("/list")
    public ResponseEntity<PageResponseDto<List<UserResponseDto>>> getUserList(int page, User user) {
        return null;
    }
}
