package com.devgang.marketduck.auth.service;

import com.devgang.marketduck.auth.dto.RefreshDto;
import com.devgang.marketduck.auth.entity.RefreshEntity;
import com.devgang.marketduck.auth.repository.RefreshRepository;
import com.devgang.marketduck.constant.ErrorCode;
import com.devgang.marketduck.exception.ServiceLogicException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RefreshService {

    private final RefreshRepository refreshRepository;

    public RefreshDto createRefresh(String username, String refreshToken) {
        RefreshEntity entity = new RefreshEntity(username, refreshToken);
        return RefreshDto.of(refreshRepository.save(entity));
    }

    public RefreshDto getRefresh(String username) {
        RefreshEntity entity = refreshRepository.findById(username)
                .orElseThrow(() -> new ServiceLogicException(ErrorCode.EXPIRED_REFRESH_TOKEN));
        return RefreshDto.of(entity);
    }

    public void deleteRefresh(String username) {
        refreshRepository.deleteById(username);
    }

    public List<RefreshDto> getAll() {
        ArrayList<RefreshEntity> list = new ArrayList<>();
        refreshRepository.findAll().forEach(list::add);
        return list.stream().map(RefreshDto::of)
                .collect(Collectors.toList());
    }
}
