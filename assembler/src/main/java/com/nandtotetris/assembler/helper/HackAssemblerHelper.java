package com.nandtotetris.assembler.helper;

import com.nandtotetris.assembler.constants.CommandConstants;

import static com.nandtotetris.assembler.Utils.StringUtils.isNotNull;

public class HackAssemblerHelper implements Assembler {

    public void compile(){

    }
    public String getInstructionType(String command){
        return null;
    }

    public boolean isLabel(String command){
        if(isNotNull(command) && command.matches(CommandConstants.LABEL_REGEX)){
            return true;
        }
        return false;
    }

    public boolean isComment(String command){
        if(isNotNull(command) &&  command.matches(CommandConstants.COMMENT_REGEX)){
            return true;
        }
        return false;
    }

    public String[] tokenise( String command){
        return null;
    }

}
