package com.ares.gear.activity;

import android.graphics.drawable.Animatable;
import android.net.Uri;

import com.ares.gear.R;
import com.ares.gear.base.activity.BaseActibity;
import com.ares.gear.utils.Logs;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.facebook.imagepipeline.image.QualityInfo;

/**
 * Fresco 图片加载
 * Created by Ares on 2016/9/8.
 */
public class FaceBookFrescoActivity extends BaseActibity {

    private SimpleDraweeView my_image_view;

    @Override
    protected int getLayout() {
        return R.layout.facebook_fresco_activity_layout;
    }

    @Override
    protected void initGui() {
        my_image_view = (SimpleDraweeView) findViewById(R.id.my_image_view);
    }

    @Override
    protected void initData() {
//        Uri uri = Uri.parse("res://包名(实际可以是任何字符串甚至留空)/" + R.drawable.ic_launcher);
        //远程图片
        Uri sdvUrl = Uri.parse("http://img1.imgtn.bdimg.com/it/u=2735633715,2749454924&fm=21&gp=0.jpg");
        //本地图片
//        Uri sdvUrl = Uri.parse("res://" + this.getPackageName() + "/" + R.mipmap.show_iamge);
        ControllerListener mControllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                if (imageInfo == null)
                    return;
                QualityInfo qualityInfo = imageInfo.getQualityInfo();
                Logs.i(imageInfo.getWidth() + "," +
                        imageInfo.getHeight() + "," +
                        qualityInfo.getQuality() + "," +
                        qualityInfo.isOfGoodEnoughQuality() + "," +
                        qualityInfo.isOfFullQuality());
            }

            @Override
            public void onIntermediateImageSet(String id, ImageInfo imageInfo) {
                super.onIntermediateImageSet(id, imageInfo);
                Logs.i("mControllerListener.onIntermediateImageSet");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
                Logs.i("mControllerListener.onFailure");
            }
        };

        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setControllerListener(mControllerListener)
                .setUri(sdvUrl)
                // other setters
                .build();
        my_image_view.setController(controller);
    }

    @Override
    protected void initAction() {

    }


}
