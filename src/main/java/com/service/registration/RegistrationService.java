package com.service.registration;

import com.data.x.U;
import com.response.Message;
import org.springframework.stereotype.Service;

@Service
public interface RegistrationService {
    Message registr(U u);
}
