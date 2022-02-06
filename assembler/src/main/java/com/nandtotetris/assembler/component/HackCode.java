package com.nandtotetris.assembler.component;

import com.nandtotetris.assembler.constants.CommandConstants;
import com.nandtotetris.assembler.constants.Common;
import com.nandtotetris.assembler.constants.InstructionConstants;
import com.nandtotetris.assembler.constants.InstructionType;
import com.nandtotetris.assembler.dto.Instruction;
import com.nandtotetris.assembler.helper.CommandHelper;
import com.nandtotetris.assembler.helper.InstructionHelper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.nandtotetris.assembler.Utils.StringUtils.*;

public class HackCode {
    private BufferedWriter out;
    private Map<String,Integer> variableMap = new HashMap<>();
    public int currentAvailableRamPosition = InstructionConstants.RAM_START_POSITION_FOR_VARIABLE;
    public Set<String> labelSet = new HashSet<>();

    @Autowired
     CommandHelper commandHelper;

    @Autowired
     InstructionHelper instructionHelper;

    public void assmblyToMachineCode(FileInputStream inputStream, BufferedWriter out) throws Exception {
        this.out = out;
        if(inputStream == null){
            return;
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
        parseAssembly(in, out);
        inputStream.getChannel().position(0);
         in = new BufferedReader(new InputStreamReader(inputStream));
        comileAssembly(in,out);
        in.close();

    }
    private void  parseAssembly(BufferedReader in, BufferedWriter out) throws Exception {
        String command = in.readLine();
        int lineAddressInRom = 0;
        while(isNotNull(command)){
            //
            if(isEmpty(command) || commandHelper.isComment(command)){
                command = in.readLine();
                continue;
            }
            if(commandHelper.isLabel(command)){
                parseLabel(command,lineAddressInRom);
                command = in.readLine();
                continue;
            }
            lineAddressInRom++;
//            Instruction instruction = instructionHelper.getInstruction(command);
//            if(instruction.getType().equals(InstructionType.A_INSTRUCTION) && isNotEmpty(instruction.getValue()) && !instruction.getValue().matches(Common.NUMBER_REGEX)){
//                    parseVariable(instruction.getValue());
//                    command = in.readLine();
//                    continue;
//            }
            command = in.readLine();
        }
    }
    public void parseLabel(String command, int lineAddressInRom) throws  Exception {
        if(isNull(command)){
            return;
        }
        String label = getFirtMatch(CommandConstants.LABEL_REGEX,command);

        if(InstructionConstants.SYMBOL_MAP.containsKey(label)){
            throw new Exception("RESERVED KEYWORD");
        }
        if(labelSet.contains(label)){
            throw new Exception("Duplicate Label");
        }
        labelSet.add(label);
        variableMap.put(label,lineAddressInRom);
    }
    public String parseVariable(String value){
        if(isNull(value)){
            return null;
        }
        if(InstructionConstants.SYMBOL_MAP.containsKey(value)){
            return InstructionConstants.SYMBOL_MAP.get(value);
        }
        if(variableMap.containsKey(value)){
            return String.valueOf(variableMap.get(value));
        }
        variableMap.put(value,currentAvailableRamPosition);
        return String.valueOf(currentAvailableRamPosition++);
    }


    private void  comileAssembly(BufferedReader in, BufferedWriter out) throws IOException {
        String command = in.readLine();
        while(isNotNull(command)){
            //
            if(isEmpty(command) || commandHelper.isComment(command)){
                command = in.readLine();
                continue;
            }
            if(commandHelper.isLabel(command)){
                command = in.readLine();
                continue;
            }

            Instruction instruction = instructionHelper.getInstruction(command);
            if(isNotNull(instruction)){
                compileInstruction(instruction);
            }
            command = in.readLine();
        }
    }

    private void compileInstruction(Instruction instruction) throws IOException {
        String hackBinary;
        if(instruction.getType().equals(InstructionType.A_INSTRUCTION)) {
            hackBinary=  compileAInstruction(instruction.getValue());
        }else{
            hackBinary= compileCInstruction(instruction.getValue());
        }
        out.write(hackBinary);
        out.newLine();
    }
    //@i,@1=0000000000000001
    public String compileAInstruction(String value) throws IOException {

       if(!value.matches(Common.NUMBER_REGEX)){
           value = parseVariable(value);
       }
        if(InstructionConstants.SYMBOL_MAP.containsKey(value)){
            value = String.valueOf(InstructionConstants.SYMBOL_MAP.get(value));
        }
        String binary  =Integer.toBinaryString(Integer.parseInt(value));
        int paddingLen = 16-binary.length();
        StringBuilder zeroPading = new StringBuilder();
       for(int i =0; i < paddingLen ; i++){
           zeroPading.append('0');
       }
       String hackBinary = zeroPading.toString()+binary;
       return hackBinary;
    }

    //111 a cccccc ddd jjj
    public String compileCInstruction(String command){
        String prefix_a = "0";
        String[] tokens = command.split(InstructionConstants.INSTRUCTION_JUMP_SEPARATOR);
        String jump = tokens.length==2?tokens[1]:null;
        command = tokens[0];
         tokens = command.split(InstructionConstants.INSTRUCTION_COMP_SEPARATOR);
         String comp,dest;
         if(tokens.length==2){
             comp=tokens[1];
             dest=tokens[0];
         }else{
             comp= tokens[0];
             dest=null;
         }
        if(isNotNull(dest) && comp.contains("M")){
            prefix_a = "1";
        }

        String destBit = getDestBit(dest);
        String compBit = getCompBit(comp );
        String jumpBit = getJumpBit(jump);
        String hackBinary = String.format("%s%s%s%s%s",InstructionConstants.C_INSTRUCTION_PREFIX,prefix_a,compBit,destBit,jumpBit);
        return hackBinary;
    }
    public String getDestBit(String dest){
        if(isNull(dest)){
            return InstructionConstants.EMPTY_BIT;
        }
        return InstructionConstants.DEST_MAP.get(dest);
    }
    public String getCompBit(String copm){
        return InstructionConstants.COMP_MAP.get(copm);
    }
    public String getJumpBit(String jump){
        if(isNull(jump)){
            return InstructionConstants.EMPTY_BIT;
        }
        return InstructionConstants.JUMP_MAP.get(jump);
    }

}
