package com.niteshsinha.candid.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("candid")
public class Candid {

    @Id
    private Integer id;
    private String name;
    private String status;
    private Long duration;
}
