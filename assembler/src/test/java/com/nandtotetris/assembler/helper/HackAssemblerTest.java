package com.nandtotetris.assembler.helper;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;
public class HackAssemblerTest {
    @InjectMocks
    private HackAssemblerHelper hackAssemblerHelper;

    @Test
    public void isCommentTest(){
        MockitoAnnotations.openMocks(this);
        assertThat(hackAssemblerHelper.isComment(null)).isFalse();
        assertThat(hackAssemblerHelper.isComment("//this is a comment")).isTrue();
        assertThat(hackAssemblerHelper.isComment("A=B //not a comment")).isFalse();
    }
}
