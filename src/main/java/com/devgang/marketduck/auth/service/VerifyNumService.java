package com.devgang.marketduck.auth.service;

import com.devgang.marketduck.auth.entity.VerifyNumEntity;
import com.devgang.marketduck.auth.repository.VerifyNumRepository;
import com.devgang.marketduck.constant.ErrorCode;
import com.devgang.marketduck.exception.ServiceLogicException;
import com.devgang.marketduck.openapi.auth.dto.VerifyNumDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VerifyNumService {

    private final VerifyNumRepository verifyNumRepository;

    public VerifyNumDto createNum(String email, String verifyNum) {
        VerifyNumEntity entity = new VerifyNumEntity(email, verifyNum);
        return VerifyNumDto.of(verifyNumRepository.save(entity));
    }

    public VerifyNumDto getNum(String email) {
        VerifyNumEntity entity = verifyNumRepository.findById(email)
                .orElseThrow(() -> new ServiceLogicException(ErrorCode.EXPIRED_OR_NOT_FOUND_VERIFY_NUM));
        return VerifyNumDto.of(entity);
    }

    public void deleteRefresh(String email) {
        verifyNumRepository.deleteById(email);
    }

    public List<VerifyNumDto> getAll() {
        ArrayList<VerifyNumDto> list = new ArrayList<>();
        verifyNumRepository.findAll().forEach(a-> list.add(VerifyNumDto.of(a)));
        return list;
    }
}
