package org.grida.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {StorageProperties.class})
class StoragePropertiesTest {

    @Autowired
    StorageProperties storageProperties;

    @Test
    void yml_값을_불러올_수_있다() {
        // given

        // when

        // then
//        assertThat(s3)
        System.out.println(storageProperties.getAccessKey());
    }
}