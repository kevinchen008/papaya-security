package com.papaya.core.validate.code.image;

import com.papaya.core.validate.code.impl.AbstractValidateCodeProcesser;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import java.io.IOException;

@Component("imageValidateCodeProcesser")
public class ImageCodeProcesser extends AbstractValidateCodeProcesser<ImageCode> {
    @Override
    public void send(ServletWebRequest request, ImageCode imageCode) throws IOException {

        ImageIO.write(imageCode.getImage(),"JPEG",request.getResponse().getOutputStream());
    }
}
