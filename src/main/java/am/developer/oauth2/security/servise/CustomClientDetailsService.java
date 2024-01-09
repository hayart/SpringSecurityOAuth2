package am.developer.oauth2.security.servise;

import am.developer.oauth2.models.OauthClientDetail;
import am.developer.oauth2.repository.OauthClientDetailsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomClientDetailsService implements ClientDetailsService {

    private final OauthClientDetailsRepository clientDetailsRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${user.oauth.accessTokenValidity}")
    private int accessTokenValidity;

    @Value("${user.oauth.refreshTokenValidity}")
    private int refreshTokenValidity;

    public CustomClientDetailsService(OauthClientDetailsRepository clientDetailsRepository,
                                      PasswordEncoder passwordEncoder) {
        this.clientDetailsRepository = clientDetailsRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public org.springframework.security.oauth2.provider.ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Optional<OauthClientDetail> clientDetailsOptional = clientDetailsRepository.findByClientId(clientId);

        if (clientDetailsOptional.isEmpty()) {
            throw new ClientRegistrationException("Client not found.");
        }
        OauthClientDetail oauthClientDetail = clientDetailsOptional.get();
        // Parse roles and create authorities
        String[] roles = oauthClientDetail.getRoles().split(",");
        List<GrantedAuthority> authorities = Arrays.stream(roles)
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());

        // Build and return a OauthClientDetail object based on your database records
        BaseClientDetails baseClientDetails = new BaseClientDetails();
        baseClientDetails.setClientId(clientId);
        baseClientDetails.setScope(List.of("all"));
        baseClientDetails.setAuthorizedGrantTypes(List.of("client_credentials"));
        baseClientDetails.setAuthorities(authorities);
        baseClientDetails.setClientSecret(this.passwordEncoder.encode(oauthClientDetail.getClientSecret()));
        baseClientDetails.setAccessTokenValiditySeconds(this.accessTokenValidity);
        baseClientDetails.setRefreshTokenValiditySeconds(this.refreshTokenValidity);
        baseClientDetails.setResourceIds(List.of("api"));
        return baseClientDetails;
    }
}
