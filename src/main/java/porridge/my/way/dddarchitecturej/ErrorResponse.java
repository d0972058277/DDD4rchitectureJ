package porridge.my.way.dddarchitecturej;

import lombok.Data;

import java.io.Serializable;

@Data
public class ErrorResponse implements Serializable {
    public final String message;
}
