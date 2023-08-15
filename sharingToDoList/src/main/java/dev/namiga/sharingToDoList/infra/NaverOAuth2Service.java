package dev.namiga.sharingToDoList.infra;

import dev.namiga.sharingToDoList.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NaverOAuth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(NaverOAuth2Service.class);
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2UserService<OAuth2UserRequest,OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);

        String usernameAttributeName = userRequest
                .getClientRegistration()// 어떤 로그인 기능인지 카카오,네이버
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();

        oAuth2User.getAttributes();
        logger.info(oAuth2User.getAttributes().toString());
        return oAuth2User;
    }

}
