package com.nandtotetris.assembler.helper;

import com.nandtotetris.assembler.constants.InstructionType;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import static org.assertj.core.api.Assertions.assertThat;
public class InstructionHelperTest {
    @InjectMocks
    InstructionHelper instructionHelper;

    @Test
    public void getInstructionTypeTest(){
        MockitoAnnotations.openMocks(this);
        assertThat(instructionHelper.getInstruction("@i")).isEqualTo(InstructionType.A_INSTRUCTION);
        assertThat(instructionHelper.getInstruction("@i   ")).isEqualTo(InstructionType.A_INSTRUCTION);
        assertThat(instructionHelper.getInstruction("@i //")).isEqualTo(InstructionType.A_INSTRUCTION);
        assertThat(instructionHelper.getInstruction("@i  // @i")).isEqualTo(InstructionType.A_INSTRUCTION);
        assertThat(instructionHelper.getInstruction("@i @ // @i")).isNull();
        assertThat(instructionHelper.getInstruction("A=M+1")).isEqualTo(InstructionType.C_INSTRUCTION);
        assertThat(instructionHelper.getInstruction("A=M+1   ")).isEqualTo(InstructionType.C_INSTRUCTION);
        assertThat(instructionHelper.getInstruction("A=M+1   //")).isEqualTo(InstructionType.C_INSTRUCTION);
        assertThat(instructionHelper.getInstruction("A=M+1;   //")).isEqualTo(InstructionType.C_INSTRUCTION);
        assertThat(instructionHelper.getInstruction("A=M+1;JMP   //")).isEqualTo(InstructionType.C_INSTRUCTION);
        assertThat(instructionHelper.getInstruction("A=M+1;JMP   //A=M+1;JMP ")).isEqualTo(InstructionType.C_INSTRUCTION);
        assertThat(instructionHelper.getInstruction("A=M+1;JMP @")).isNull();
    }
}
