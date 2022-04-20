package ksafinalproject.gbt.smoking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class TotalSmoking {
    private Long total;
    private List<OSmoking> smokingList;
}
