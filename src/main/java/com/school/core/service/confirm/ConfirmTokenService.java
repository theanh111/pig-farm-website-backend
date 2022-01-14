package com.school.core.service.confirm;

import com.school.core.entity.ConfirmToken;
import com.school.core.repository.ConfirmTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmTokenService {
    @Autowired
    private ConfirmTokenRepository confirmTokenRepository;

    public ConfirmToken findTokenByToken(String token) {
        return confirmTokenRepository.findTokenByToken(token);
    }

    public void saveToken(ConfirmToken confirmToken) {
        confirmTokenRepository.saveToken(confirmToken);
    }

    public void updateConfirmedAt(String token, String confirmAt) {
        confirmTokenRepository.updateConfirmedAt(token, confirmAt);
    }
}
