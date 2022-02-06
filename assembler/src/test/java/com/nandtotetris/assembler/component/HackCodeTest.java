package com.nandtotetris.assembler.component;

import com.nandtotetris.assembler.constants.InstructionConstants;
import com.nandtotetris.assembler.helper.CommandHelper;
import com.nandtotetris.assembler.helper.InstructionHelper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import static org.assertj.core.api.Assertions.assertThat;

import java.io.*;

public class HackCodeTest {

    @InjectMocks
    CommandHelper commandHelper;
    @InjectMocks
    InstructionHelper instructionHelper;

    @InjectMocks
    HackCode hackCode;

    @InjectMocks
    InstructionConstants instructionConstants;

    @Test
    public void assmblyToMachineCodeTest() throws Exception {
        MockitoAnnotations.openMocks(this);
        Resource resource = new ClassPathResource("assemblyFile/Rect.asm");
        FileInputStream inputStream = new FileInputStream(resource.getFile());

        File outResource = new File("/Users/upstox/projectHome/home/nandToTetris/nand2TetrisCompiler/assembler/src/main/resources/assemblyFile/Rect.hack");
        FileOutputStream outputStream = new FileOutputStream(outResource);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
        hackCode.commandHelper=commandHelper;
        hackCode.instructionHelper=instructionHelper;
        hackCode.assmblyToMachineCode(inputStream,writer);
        writer.close();

    }

    @Test
    public void cCommandCompilerTest(){
        MockitoAnnotations.openMocks(this);
        String bits = hackCode.compileCInstruction("0");
        assertThat(bits).isEqualTo("1110101010000000");
        System.out.println(bits);
    }
}
