package sn.ucad.gestionstock.handlers;


import lombok.*;
import sn.ucad.gestionstock.exception.ErrorCodes;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Builder
public class ErrorDto {

    private  Integer httpCode;

    private ErrorCodes errorCodes;

    private  String message;

    private List<String> errors = new ArrayList<>();
}
