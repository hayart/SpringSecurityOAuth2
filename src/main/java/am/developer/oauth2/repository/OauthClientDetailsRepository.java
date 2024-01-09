package am.developer.oauth2.repository;


import am.developer.oauth2.models.OauthClientDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetail, String> {

    Optional<OauthClientDetail> findByClientId(String clientId);
}
