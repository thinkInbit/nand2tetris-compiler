package com.nandtotetris.assembler.constants;

import java.util.HashMap;
import java.util.Map;

public class InstructionConstants {

    public static final String A_INSTRUCTION_REGEX = "\\s*@((?:[a-zA-Z0-9_$.:]+|[0-9]+))(\\s*(//.*|\\s*))";
    public static final String C_INSTRUCTION_REGEX = "\\s*((A|M|D|0){1,3}=?[A-Z0-9+!&|-]{0,3};?[A-Z]{0,3})(\\s*(//.*|\\s*))";
    public static final String SYMBOL_REGEX = "\\d*";
    public static final String INSTRUCTION_COMP_SEPARATOR = "=";
    public static final String INSTRUCTION_JUMP_SEPARATOR = ";";
    public static final String EMPTY_BIT = "000";
    public static final String C_INSTRUCTION_PREFIX = "111";
    public static final int RAM_START_POSITION_FOR_VARIABLE = 16;
    public static final Map<String,String> SYMBOL_MAP = Map.ofEntries(
            Map.entry("R0","0"),
            Map.entry("R1","1"),
            Map.entry("R2","2"),
            Map.entry("R3","3"),
            Map.entry("R4","4"),
            Map.entry("R5","5"),
            Map.entry("R6","6"),
            Map.entry("R7","7"),
            Map.entry("R8","8"),
            Map.entry("R9","9"),
            Map.entry("R10","10"),
            Map.entry("R11","11"),
            Map.entry("R12","12"),
            Map.entry("R13","13"),
            Map.entry("R14","14"),
            Map.entry("R15","15"),
            Map.entry("SP","0"),
            Map.entry("LCL","1"),
            Map.entry("ARG","2"),
            Map.entry("THIS","3"),
            Map.entry("THAT","4"),
            Map.entry("SCREEN","16384"),
            Map.entry("KBD","24576")
            ) ;
    public static final Map<String,String> DEST_MAP = Map.ofEntries(
            Map.entry("M","001"),
            Map.entry("D","010"),
            Map.entry("A","100"),
            Map.entry("MD","011"),
            Map.entry("AM","101"),
            Map.entry("AD","110"),
            Map.entry("AMS","111")
            );
    public static final Map<String,String> JUMP_MAP = Map.ofEntries(
            Map.entry("JGT","001"),
            Map.entry("JEQ","010"),
            Map.entry("JLT","100"),
            Map.entry("JGE","011"),
            Map.entry("JNE","101"),
            Map.entry("JLE","110"),
            Map.entry("JMP","111")
    );

    public static final Map<String,String> COMP_MAP = Map.ofEntries(
            Map.entry("0","101010"),
            Map.entry("1","111111"),
            Map.entry("-1","111010"),
            Map.entry("D","001100"),
            Map.entry("!D","001101"),
            Map.entry("-D","001111"),
            Map.entry("D+1","011111"),
            Map.entry("D-1","001110"),
            Map.entry("M","110000"),
            Map.entry("!M","110001"),
            Map.entry("-M","110011"),
            Map.entry("M+1","110111"),
            Map.entry("M-1","110010"),
            Map.entry("D+M","000010"),
            Map.entry("D-M","010011"),
            Map.entry("M-D","000111"),
            Map.entry("D&M","000000"),
            Map.entry("D|M","010101"),
            Map.entry("A","110000"),
            Map.entry("!A","110001"),
            Map.entry("-A","110011"),
            Map.entry("A+1","110111"),
            Map.entry("A-1","110010"),
            Map.entry("D+A","000010"),
            Map.entry("D-A","010011"),
            Map.entry("A-D","000111"),
            Map.entry("D&A","000000"),
            Map.entry("D|A","010101")
    );
}
