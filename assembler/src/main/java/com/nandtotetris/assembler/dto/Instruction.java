package com.nandtotetris.assembler.dto;

import com.nandtotetris.assembler.constants.InstructionType;
import lombok.Data;

@Data
public class Instruction {
    private InstructionType type;
    private String value;
}
