package bw5team1.epicenergyservices.payloads;

import java.util.Date;
import java.util.List;

public record ErrorsRespDTO(String message, Date timeStamp, List<String> errorsList) {
}
