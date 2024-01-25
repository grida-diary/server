package org.grida.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {S3Properties.class})
class S3PropertiesTest {

    @Autowired
    S3Properties s3Properties;

    @Test
    void yml_값을_불러올_수_있다() {
        // given

        // when

        // then
//        assertThat(s3)
        System.out.println(s3Properties.getAccessKey());
    }
}