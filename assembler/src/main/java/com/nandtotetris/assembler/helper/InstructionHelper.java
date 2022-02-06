package com.nandtotetris.assembler.helper;

import com.nandtotetris.assembler.Utils.StringUtils;
import com.nandtotetris.assembler.constants.InstructionConstants;
import com.nandtotetris.assembler.constants.InstructionType;
import com.nandtotetris.assembler.dto.Instruction;

import static com.nandtotetris.assembler.Utils.StringUtils.isEmpty;
import static com.nandtotetris.assembler.Utils.StringUtils.isNotNull;
import static java.util.Objects.isNull;

public class InstructionHelper {
    public Instruction getInstruction(String command){
        if(isNull(command)){
            return null;
        }

        Instruction instruction = new Instruction();
        if( isAInstruction(command) ){
            instruction.setType(InstructionType.A_INSTRUCTION);
            instruction.setValue(StringUtils.getFirtMatch(InstructionConstants.A_INSTRUCTION_REGEX,command));
            return instruction;
        }

        if(isCInstruction(command) ){
            instruction.setType(InstructionType.C_INSTRUCTION);
            instruction.setValue(StringUtils.getFirtMatch(InstructionConstants.C_INSTRUCTION_REGEX,command));
            return instruction;
        }
        System.out.println("No instrction found for command "+command);
        return null;
    }

    public boolean isAInstruction(String command){
        if(isEmpty(command)){
            return false;
        }
        return command.matches(InstructionConstants.A_INSTRUCTION_REGEX);
    }

    public boolean isCInstruction(String command){
        if(isEmpty(command)){
            return false;
        }
        return command.matches(InstructionConstants.C_INSTRUCTION_REGEX);
    }
}
