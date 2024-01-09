package am.developer.oauth2.controller.v1;

import am.developer.oauth2.payload.request.SearchRequest;
import am.developer.oauth2.payload.response.SearchResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@Slf4j
@RequiredArgsConstructor
@Validated
@RestController
@RequestMapping("/api/info")
public class ManagementController {

    @PostMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<SearchResponse> search(@RequestBody @Valid SearchRequest request) {
        return ResponseEntity.status(OK).body(new SearchResponse());
    }

}
