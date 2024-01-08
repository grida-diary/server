package io.wwan13.imagegenerate.config;

import io.wwan13.imagegenerate.generator.DiaryImageGenerator;
import io.wwan13.imagegenerate.generator.ProfileImageGenerator;
import io.wwan13.imagegenerate.processor.image.ImageProcessor;
import io.wwan13.imagegenerate.processor.naturallanguage.NaturalLanguageProcessor;
import io.wwan13.imagegenerate.processor.openai.client.chat.OpenAiChatClient;
import io.wwan13.imagegenerate.processor.openai.client.image.OpenAiImageClient;
import io.wwan13.imagegenerate.processor.openai.processor.OpenAiImageProcessor;
import io.wwan13.imagegenerate.processor.openai.processor.OpenAiNaturalLanguageProcessor;
import io.wwan13.imagegenerate.prompt.diaryimagegenerate.DiaryImageGeneratePrompt;
import io.wwan13.imagegenerate.prompt.naturallanguageprocess.NaturalLanguageProcessPrompt;
import io.wwan13.imagegenerate.prompt.profileimagegenerate.ProfileImageGeneratePrompt;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImageGenerateConfig {

    @Bean
    @ConditionalOnMissingBean
    public ProfileImageGenerator profileImageGenerator(ProfileImageGeneratePrompt profileImageGeneratePrompt,
                                                       ImageProcessor imageProcessor) {
        return new ProfileImageGenerator(profileImageGeneratePrompt, imageProcessor);
    }

    @Bean
    @ConditionalOnMissingBean
    public DiaryImageGenerator diaryImageGenerator(NaturalLanguageProcessPrompt naturalLanguageProcessPrompt,
                                                   DiaryImageGeneratePrompt diaryImageGeneratePrompt,
                                                   NaturalLanguageProcessor naturalLanguageProcessor,
                                                   ImageProcessor imageProcessor) {
        return new DiaryImageGenerator(naturalLanguageProcessPrompt, diaryImageGeneratePrompt,
                naturalLanguageProcessor, imageProcessor);
    }

    @Bean
    public NaturalLanguageProcessor naturalLanguageProcessor(OpenAiProperties openAiProperties,
                                                             OpenAiChatClient chatClient) {
        return new OpenAiNaturalLanguageProcessor(openAiProperties, chatClient);
    }

    @Bean
    public ImageProcessor imageProcessor(OpenAiProperties openAiProperties, OpenAiImageClient imageClient) {
        return new OpenAiImageProcessor(openAiProperties, imageClient);
    }
}
