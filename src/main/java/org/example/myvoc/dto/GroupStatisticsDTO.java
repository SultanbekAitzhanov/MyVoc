package org.example.myvoc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupStatisticsDTO {
    private int masteredCount;
    private int learningCount;
    private int reviewingCount;
    private int total;
}
