package am.developer.oauth2.payload.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class SearchRequest {

    @Size(min = 5, max = 5, message = "code field's size must be 5")
    private String code;

    @Size(max = 8, message = "name field's size must be max 8")
    private String name;


}
