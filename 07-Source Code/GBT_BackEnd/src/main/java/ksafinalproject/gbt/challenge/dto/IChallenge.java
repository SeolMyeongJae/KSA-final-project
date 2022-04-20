package ksafinalproject.gbt.challenge.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class IChallenge {
    private Long id;
    private String title;
    private String startDate;
    private String endDate;
    private String method;
    private Long frequency;
    private String description;
    private String summary;
    private Long max;
    private Long point;
    private List<MultipartFile> img;
}
