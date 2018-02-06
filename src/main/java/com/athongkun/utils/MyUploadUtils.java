package com.athongkun.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class MyUploadUtils {

	public static List<String> upload_imgs(MultipartFile[] files) {

		List<String> images_name = new ArrayList<String>();

		for (int i = 0; i < files.length; i++) {
			if (!files[i].isEmpty()) {
				String img_name = System.currentTimeMillis() + files[i].getOriginalFilename();
				try {
					files[i].transferTo(new File(MyPropertiesUtils.getProperty("imgPath_windows") + "/" + img_name));
					images_name.add(img_name);
				} catch (IllegalStateException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return images_name;
	}

}
