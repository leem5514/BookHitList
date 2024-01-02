package com.example.project.config.auth;

import com.example.project.entity.UserEntity;
import com.example.project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2UserCustomService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        return super.loadUser(userRequest);
        OAuth2User user = super.loadUser(userRequest);
        saveOrUpdate(user);
        return user;
    }

    private UserEntity saveOrUpdate(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        UserEntity user = userRepository.findByEmail(email)
                .map(entity -> entity.update(name))
                .orElse(UserEntity.builder()
                        .email(email)
                        .name(name)
                        .build());
        return userRepository.save(user);
    }

}
